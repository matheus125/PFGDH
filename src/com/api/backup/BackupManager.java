package com.api.backup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import javax.swing.JOptionPane;

public class BackupManager {

    private static final int TENTATIVAS_MAX = 5;
    private static final int DELAY_ENTRE_TENTATIVAS = 5000; // ms

    private static final String ENDPOINT_UPLOAD = "https://8321ffb06436.ngrok-free.app/upload";
    private static final String ENDPOINT_HEALTH = "https://8321ffb06436.ngrok-free.app/health"; // usar /health se existir

    public static void iniciarBackupComVerificacao() {
        new Thread(() -> {
            while (true) {
                if (temConexaoComInternet()) {
                    if (temConexaoComAPI()) {
                        System.out.println("Internet e API disponíveis. Iniciando backup...");
                        String caminho = BackupMySQL.gerarBackup();
                        if (caminho != null) {
                            enviarBackup(caminho);
                        } else {
                            System.out.println("Falha ao gerar backup.");
                        }
                        break;
                    } else {
                        System.out.println("API indisponível. Tentará novamente em 10 segundos...");
                    }
                } else {
                    System.out.println("Sem conexão com a internet. Tentará novamente em 10 segundos...");
                }

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }).start();
    }

    private static boolean temConexaoComInternet() {
        // Primeiro teste: tentativa via HTTP com Google
        try {
            URL url = new URL("https://www.google.com/");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setConnectTimeout(3000);
            conexao.setReadTimeout(3000);
            int status = conexao.getResponseCode();
            if (status >= 200 && status < 400) {
                System.out.println("Conexão com a internet confirmada via HTTP.");
                return true;
            }
        } catch (IOException e) {
            System.out.println("Falha na verificação HTTP da internet: " + e.getMessage());
        }

        // Fallback: teste de resolução DNS (mais leve, não depende de HTTP)
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            boolean reachable = address.isReachable(3000);
            if (reachable) {
                System.out.println("Conexão com a internet confirmada via DNS.");
                return true;
            } else {
                System.out.println("DNS respondeu, mas host inacessível.");
            }
        } catch (IOException e) {
            System.out.println("Falha no teste de DNS: " + e.getMessage());
        }

        // Se nenhum método funcionar
        return false;
    }

    private static boolean temConexaoComAPI() {
        int tentativas = 2;
        for (int i = 1; i <= tentativas; i++) {
            try {
                URL url = new URL(ENDPOINT_HEALTH);
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
                conexao.setRequestMethod("GET");
                conexao.setConnectTimeout(3000);
                conexao.setReadTimeout(3000);
                conexao.setRequestProperty("ngrok-skip-browser-warning", "1");

                int status = conexao.getResponseCode();
                if (status >= 200 && status < 400) {
                    System.out.println("API respondendo (tentativa " + i + ")");
                    return true;
                } else {
                    System.out.println("API respondeu com status " + status + " (tentativa " + i + ")");
                }
            } catch (IOException e) {
                System.out.println("Falha ao verificar API (tentativa " + i + "): " + e.getMessage());
            }

            // Espera antes da próxima tentativa
            if (i < tentativas) {
                try {
                    Thread.sleep(2000); // 2 segundos entre tentativas
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }

        // Se todas as tentativas falharem
        return false;
    }

    private static void enviarBackup(String caminhoArquivo) {
        new Thread(() -> {
            String boundary = "----WebKitFormBoundary" + System.currentTimeMillis();
            String LINE_FEED = "\r\n";

            for (int tentativa = 1; tentativa <= TENTATIVAS_MAX; tentativa++) {
                try {
                    File uploadFile = new File(caminhoArquivo);
                    if (!uploadFile.exists()) {
                        System.out.println("Arquivo não encontrado: " + caminhoArquivo);
                        return;
                    }

                    URL url = new URL(ENDPOINT_UPLOAD);
                    HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

                    conexao.setUseCaches(false);
                    conexao.setDoOutput(true);
                    conexao.setDoInput(true);
                    conexao.setRequestMethod("POST");
                    conexao.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

                    // Corrige o aviso da primeira requisição Ngrok
                    conexao.setRequestProperty("ngrok-skip-browser-warning", "1");

                    OutputStream outputStream = conexao.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                    // Cabeçalho multipart
                    writer.write("--" + boundary);
                    writer.write(LINE_FEED);
                    writer.write("Content-Disposition: form-data; name=\"file\"; filename=\"" + uploadFile.getName() + "\"");
                    writer.write(LINE_FEED);
                    writer.write("Content-Type: application/octet-stream");
                    writer.write(LINE_FEED);
                    writer.write(LINE_FEED);
                    writer.flush();

                    // Conteúdo do arquivo
                    FileInputStream inputStream = new FileInputStream(uploadFile);
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    outputStream.flush();
                    inputStream.close();

                    writer.write(LINE_FEED);
                    writer.write("--" + boundary + "--");
                    writer.write(LINE_FEED);
                    writer.flush();
                    writer.close();

                    int responseCode = conexao.getResponseCode();
                    InputStream is = (responseCode == HttpURLConnection.HTTP_OK)
                            ? conexao.getInputStream()
                            : conexao.getErrorStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line).append(LINE_FEED);
                    }
                    reader.close();

                    System.out.println("Backup enviado com sucesso! Resposta: " + responseCode);
                    return;

                } catch (IOException e) {
                    System.out.println("Tentativa " + tentativa + " falhou: " + e.getMessage());

                    if (tentativa == TENTATIVAS_MAX) {
                        JOptionPane.showMessageDialog(null, "Erro ao enviar backup após várias tentativas.\n" + e.getMessage());
                    } else {
                        try {
                            Thread.sleep(DELAY_ENTRE_TENTATIVAS);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                            break;
                        }
                    }
                }
            }
        }).start();
    }
}

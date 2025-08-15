package com.api.backup;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;

public class EnviarBackupAPI {
    
    public static void enviar(String caminhoArquivo) {
        String boundary = "----WebKitFormBoundary" + System.currentTimeMillis();
        String LINE_FEED = "\r\n";

        try {
            File uploadFile = new File(caminhoArquivo);
            if (!uploadFile.exists()) {
                System.out.println("Arquivo não encontrado: " + caminhoArquivo);
                return;
            }

            URL url = new URL(" https://8321ffb06436.ngrok-free.app/upload");
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            conexao.setUseCaches(false);
            conexao.setDoOutput(true);
            conexao.setDoInput(true);
            conexao.setRequestMethod("POST");
            conexao.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            OutputStream outputStream = conexao.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            // Cabeçalho do arquivo
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

            // Ler resposta
            int responseCode = conexao.getResponseCode();
            InputStream is = (responseCode == HttpURLConnection.HTTP_OK)
                    ? conexao.getInputStream()
                    : conexao.getErrorStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line).append(LINE_FEED);
            }
            reader.close();
            JOptionPane.showMessageDialog(null, "Resposta do servidor: " + responseCode + " - " + response.toString());
//            System.out.println("Resposta do servidor: " + responseCode + " - " + response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
package com.api.backup;

public class BackupAgendado {

    public static void main(String[] args) {
        while (true) {
            if (VerificadorConexao.temConexao()) {
                System.out.println("Internet OK! Iniciando backup...");
                String caminho = BackupMySQL.gerarBackup();
                if (caminho != null) {
                    EnviarBackupAPI.enviar(caminho);
                    BackupLogger.log("Backup realizado e enviado com sucesso: " + caminho);
                } else {
                    BackupLogger.log("Erro ao gerar backup.");
                }
                break; // Executa uma vez e sai
            } else {
                System.out.println("Sem conexão. Tentará novamente em 10 segundos...");
                try {
                    Thread.sleep(10000); // Espera 10 segundos antes de tentar de novo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

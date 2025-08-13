package view.com.raven.main;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author gsan_
 */
class BackupMySQL {

    public static String gerarBackup() {
        // Formatar a data/hora
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String nomeArquivo = "uniao-" + timestamp + ".sql";
        String pasta = "backups";
        String caminho = pasta + File.separator + nomeArquivo;

        // Caminho do mysqldump no Windows
        String mysqldumpPath = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump.exe";
        String comando = mysqldumpPath + " -u root -p#Wiccan13# uniao -r " + caminho;

        // Garante que a pasta 'backups' exista
        File dir = new File(pasta);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            Process processo = Runtime.getRuntime().exec(comando);
            int resultado = processo.waitFor();

            if (resultado == 0) {
                JOptionPane.showMessageDialog(null, "Backup gerado com sucesso: " + caminho);
//                System.out.println("Backup gerado com sucesso: " + caminho);
                return caminho;
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao gerar backup. Código de saída: " + resultado);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
}


<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class ExportarDadosParaPDF {

    // Método para criar uma pasta
    public static void criarPasta(String caminho) {
        File pasta = new File(caminho);

        // Verifica se a pasta já existe
        if (!pasta.exists()) {
            boolean criada = pasta.mkdir(); // Cria a pasta
            if (criada) {
                System.out.println("Pasta criada com sucesso!");
            } else {
                System.out.println("Falha ao criar a pasta.");
            }
        } else {
            System.out.println("A pasta já existe.");
        }
    }

    // Método para ler o conteúdo da pasta
    public static void lerConteudoPasta(String caminho) {
        File pasta = new File(caminho);

        // Verifica se a pasta existe
        if (pasta.exists() && pasta.isDirectory()) {
            String[] conteudo = pasta.list(); // Lista os arquivos e pastas dentro da pasta
            if (conteudo != null && conteudo.length > 0) {
                System.out.println("Conteúdo da pasta:");
                for (String item : conteudo) {
                    System.out.println(item);
                }
            } else {
                System.out.println("A pasta está vazia.");
            }
        } else {
            System.out.println("A pasta não existe ou não é um diretório.");
        }
    }

    public static void gerarBackup(String filename) {
        // Caminho do mysqldump e outros parâmetros
        String mysqldumpPath = "C:/Program Files/MySQL/MySQL Server 8.0/bin/mysqldump.exe";
        String host = "localhost";
        String user = "root";
        String password = "#Wiccan13#";
        String database = "dev05";
        String port = "3306";

        // Comando completo do mysqldump
        String command = String.format("\"%s\" -v -v -v --host=%s --user=%s --password=%s --port=%s --protocol=tcp --force --allow-keywords --compress --add-drop-table --default-character-set=latin1 --hex-blob --result-file=%s --databases %s",
                mysqldumpPath, host, user, password, port, filename, database);

        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));

        // Definir o diretório de trabalho, caso necessário
        processBuilder.directory(new java.io.File("C:/"));

        try {
            // Iniciar o processo
            Process process = processBuilder.start();

            // Captura de erros e saída padrão do processo
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            BufferedReader outputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            StringBuilder output = new StringBuilder();
            StringBuilder errors = new StringBuilder();

            // Captura da saída do processo
            while ((line = outputReader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Captura dos erros do processo
            while ((line = errorReader.readLine()) != null) {
                errors.append(line).append("\n");
            }

            // Aguarda o término do processo
            int processComplete = process.waitFor();

            // Verifica o resultado
            if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "Backup Gerado com sucesso!\n" + output.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao gerar Backup!\nErros: " + errors.toString());
            }
        } catch (IOException | InterruptedException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar o comando: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String caminhoPasta = "C:/teste"; // Caminho da pasta a ser criada e lida

        // Criar a pasta
        criarPasta(caminhoPasta);

        String filename = "C:/teste/uniao_backup.sql"; // Defina o caminho do arquivo de backup
        gerarBackup(filename);

        // Ler o conteúdo da pasta
        lerConteudoPasta(caminhoPasta);
    }
=======
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.Document;
import com.itextpdf.kernel.pdf.PdfWriter;
import java.io.FileNotFoundException;

import java.sql.*;

public class ExportarDadosParaPDF {

    public static void main(String[] args) throws FileNotFoundException {
        // Configurações do banco de dados
        String url = "jdbc:mysql://localhost:3306/teste";
        String user = "root";
        String password = "#Wiccan13#";

        // Consulta SQL
        String sql = "SELECT nome, idade, endereco FROM pessoas";

        // Caminho do arquivo PDF de saída
        String arquivoPdf = "dados_pessoas.pdf";

        // Conectar ao banco de dados e gerar o PDF
        try (Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            // Criando o PDF
            PdfWriter writer = new PdfWriter(arquivoPdf);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Adicionando um título no PDF
            document.add(new Paragraph("Relatório de Pessoas"));

            // Adicionando dados no PDF
            while (rs.next()) {
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                String endereco = rs.getString("endereco");

                // Adiciona cada registro no PDF
                document.add(new Paragraph("Nome: " + nome));
                document.add(new Paragraph("Idade: " + idade));
                document.add(new Paragraph("Endereço: " + endereco));
                document.add(new Paragraph("\n")); // Adiciona uma linha em branco entre os registros
            }

            // Fechar o documento PDF
            document.close();

            System.out.println("PDF gerado com sucesso: " + arquivoPdf);

        } catch (SQLException | com.itextpdf.kernel.PdfException e) {
            e.printStackTrace();
        }
    }
>>>>>>> eb9b89a (PFGDH_2.1)
}

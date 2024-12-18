
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
}

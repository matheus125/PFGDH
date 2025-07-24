//versão original antiga
package com.raven.dao;

import com.raven.banco.ConexaoBD;
import com.raven.model.Relatorios;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.raven.controller.ControllerSenha;

//BIBLIOTECAS PARA PDF
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.raven.model.Senha;
import java.awt.Desktop;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RelatoriosDao extends ConexaoBD {

    String nome = "";
    String refeicoes_ofertadas = "400";

    ControllerSenha controllerSenha = new ControllerSenha();
    RefeicoesDao refeicoesDao = new RefeicoesDao();
    FuncionarioDao funcionarioDao = new FuncionarioDao();
    ClientesDao clientesDao = new ClientesDao();


    public Relatorios lerParaOBl(Relatorios relatorio) {
        Map<ChaveContador, Integer> contadores = new HashMap<>();

        int totalAtendidos = 0;
        int totalGenericos = 0;
        int totalSituacaoRuaMasculino = 0;
        int totalSituacaoRuaFeminino = 0;

        this.getConectar();
        try {
            this.executarSql("SELECT * FROM tb_senhas");
            while (this.getResultSet().next()) {
                totalAtendidos++;

                try {
                    int idade = Integer.parseInt(this.getResultSet().getString(4));
                    String generoStr = this.getResultSet().getString(5);
                    String respostaSim = this.getResultSet().getString(6);
                    String situacaoRuaStr = this.getResultSet().getString(8); // Coluna de situação de rua

                    FaixaEtaria faixa = classificarIdade(idade);
                    Genero genero = "Masculino".equalsIgnoreCase(generoStr) ? Genero.MASCULINO
                            : "Feminino".equalsIgnoreCase(generoStr) ? Genero.FEMININO : Genero.OUTRO;
                    boolean deficiente = "SIM".equalsIgnoreCase(respostaSim);
                    boolean situacaoRua = "PESSOA EM SITUAÇÃO DE RUA".equalsIgnoreCase(situacaoRuaStr);

                    // Se a pessoa está em situação de rua, conta apenas por sexo
                    if (situacaoRua) {
                        if (genero == Genero.MASCULINO) {
                            totalSituacaoRuaMasculino++;
                        } else if (genero == Genero.FEMININO) {
                            totalSituacaoRuaFeminino++;
                        }
                        continue; // Não conta essa pessoa nos contadores gerais
                    }

                    // Se idade ou gênero forem inválidos, ignora
                    if (faixa == FaixaEtaria.DESCONHECIDA || genero == Genero.OUTRO) {
                        totalGenericos++;
                        continue;
                    }

                    // Contador normal
                    ChaveContador chave = new ChaveContador(faixa, genero, deficiente);
                    contadores.put(chave, contadores.getOrDefault(chave, 0) + 1);

                } catch (NumberFormatException e) {
                    totalGenericos++;
                }
            }

            // Total de deficientes (exceto os em situação de rua)
            int totalDeficientes = contadores.entrySet().stream()
                    .filter(entry -> entry.getKey().deficiente)
                    .mapToInt(Map.Entry::getValue)
                    .sum();

            // Mapeia os grupos normais
            relatorio.set3_17_ANOS_MASCULINO(contadores.getOrDefault(new ChaveContador(FaixaEtaria.INFANTIL, Genero.MASCULINO, false), 0));
            relatorio.set3_17_ANOS_MASCULINO_CPD(contadores.getOrDefault(new ChaveContador(FaixaEtaria.INFANTIL, Genero.MASCULINO, true), 0));
            relatorio.set3_17_ANOS_FEMININO(contadores.getOrDefault(new ChaveContador(FaixaEtaria.INFANTIL, Genero.FEMININO, false), 0));
            relatorio.set3_17_ANOS_FEMININO_CPD(contadores.getOrDefault(new ChaveContador(FaixaEtaria.INFANTIL, Genero.FEMININO, true), 0));

            relatorio.set18_59_ANOS_MASCULINO(contadores.getOrDefault(new ChaveContador(FaixaEtaria.ADULTO, Genero.MASCULINO, false), 0));
            relatorio.set18_59_ANOS_MASCULINO_CPD(contadores.getOrDefault(new ChaveContador(FaixaEtaria.ADULTO, Genero.MASCULINO, true), 0));
            relatorio.set18_59_ANOS_FEMININO(contadores.getOrDefault(new ChaveContador(FaixaEtaria.ADULTO, Genero.FEMININO, false), 0));
            relatorio.set18_59_ANOS_FEMININO_CPD(contadores.getOrDefault(new ChaveContador(FaixaEtaria.ADULTO, Genero.FEMININO, true), 0));

            relatorio.setA_PARTIR_DE_60_ANOS_MASCULINO(contadores.getOrDefault(new ChaveContador(FaixaEtaria.IDOSO, Genero.MASCULINO, false), 0));
            relatorio.setA_PARTIR_DE_60_ANOS_MASCULINO_CPD(contadores.getOrDefault(new ChaveContador(FaixaEtaria.IDOSO, Genero.MASCULINO, true), 0));
            relatorio.setA_PARTIR_DE_60_ANOS_FEMININO(contadores.getOrDefault(new ChaveContador(FaixaEtaria.IDOSO, Genero.FEMININO, false), 0));
            relatorio.setA_PARTIR_DE_60_ANOS_FEMININO_CPD(contadores.getOrDefault(new ChaveContador(FaixaEtaria.IDOSO, Genero.FEMININO, true), 0));

            // Totais
            relatorio.setGenericos(totalGenericos);
            relatorio.setTotalDePessoasAtendidas(totalAtendidos);
            relatorio.setDeficientes(totalDeficientes);

            // Situação de rua por sexo
            relatorio.setPESSOAS_SITUACAO_RUA_MASCULINO(totalSituacaoRuaMasculino);
            relatorio.setPESSOAS_SITUACAO_RUA_FEMININO(totalSituacaoRuaFeminino);

            return relatorio;

        } catch (SQLException e) {
            e.printStackTrace();
            return relatorio;
        }
    }

    public boolean inserirNosRelatorios(Relatorios relatorio) {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        try {
            Statement st = con.createStatement();
            st.executeUpdate("insert into tb_relatorios values("
                    + "" + relatorio.get3_17_ANOS_MASCULINO() + ", "
                    + "" + relatorio.get3_17_ANOS_MASCULINO_CPD() + ", "
                    + "" + relatorio.get3_17_ANOS_FEMININO() + ", "
                    + "" + relatorio.get3_17_ANOS_FEMININO_CPD() + ", "
                    + "" + relatorio.get18_59_ANOS_MASCULINO() + ", "
                    + "" + relatorio.get18_59_ANOS_MASCULINO_CPD() + ", "
                    + "" + relatorio.get18_59_ANOS_FEMININO() + ", "
                    + "" + relatorio.get18_59_ANOS_FEMININO_CPD() + ", "
                    + "" + relatorio.getA_PARTIR_DE_60_ANOS_MASCULINO() + ","
                    + "" + relatorio.getA_PARTIR_DE_60_ANOS_MASCULINO_CPD() + ", "
                    + "" + relatorio.getA_PARTIR_DE_60_ANOS_FEMININO() + ", "
                    + "" + relatorio.getA_PARTIR_DE_60_ANOS_FEMININO_CPD() + ", "
                    + "" + relatorio.getPESSOAS_SITUACAO_RUA_MASCULINO() + ", "
                    + "" + relatorio.getPESSOAS_SITUACAO_RUA_FEMININO() + ", "
                    + "" + relatorio.getDeficientes() + ", "
                    + "" + relatorio.getGenericos() + ", "
                    + "" + relatorio.getTotalDePessoasAtendidas() + ", "
                    + "'" + dateFormat.format(date) + "')");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public String gerarTextoRelatorio(String data, String ocorrencias) {
        StringBuilder dados = new StringBuilder();

        this.getConectar();
        try {
            this.executar("SELECT * FROM tb_relatorios WHERE data ='" + data + "'");

            Relatorios relatorios = new Relatorios();
            relatorios = lerParaOBl(relatorios);

            while (rs.next()) {
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                Date date = new Date();

                dados.append("RELATÓRIO DIÁRIO\n\n")
                        .append("NOVOS CADASTROS: ").append(clientesDao.checarClientesCadastrados()).append("\n\n")
                        .append("CLIENTES DE 3 ATÉ 17 ANOS MASCULINO: ").append(rs.getInt(1)).append("\n")
                        .append("CLIENTES DE 3 ATÉ 17 ANOS MASCULINO (PCD): ").append(rs.getInt(2)).append("\n")
                        .append("CLIENTES DE 3 ATÉ 17 ANOS FEMININO: ").append(rs.getInt(3)).append("\n")
                        .append("CLIENTES DE 3 ATÉ 17 ANOS FEMININO (PCD): ").append(rs.getInt(4)).append("\n")
                        .append("CLIENTES DE 18 ATÉ 59 ANOS MASCULINO: ").append(rs.getInt(5)).append("\n")
                        .append("CLIENTES DE 18 ATÉ 59 ANOS MASCULINO (PCD): ").append(rs.getInt(6)).append("\n")
                        .append("CLIENTES DE 18 ATÉ 59 ANOS FEMININO: ").append(rs.getInt(7)).append("\n")
                        .append("CLIENTES DE 18 ATÉ 59 ANOS FEMININO (PCD): ").append(rs.getInt(8)).append("\n")
                        .append("A PARTIR DE 60 ANOS MASCULINO: ").append(rs.getInt(9)).append("\n")
                        .append("A PARTIR DE 60 ANOS MASCULINO (PCD): ").append(rs.getInt(10)).append("\n")
                        .append("A PARTIR DE 60 ANOS FEMININO: ").append(rs.getInt(11)).append("\n")
                        .append("A PARTIR DE 60 ANOS FEMININO (PCD): ").append(rs.getInt(12)).append("\n")
                        .append("PESSOAS EM SITUAÇÃO DE RUA (MASCULINO): ").append(rs.getInt(13)).append("\n")
                        .append("PESSOAS EM SITUAÇÃO DE RUA (FEMININO): ").append(rs.getInt(14)).append("\n")
                        .append("SEM CADASTRO (GENÉRICAS): ").append(relatorios.getGenericos()).append("\n\n")
                        .append("REFEIÇÕES OFERTADAS: ").append(refeicoes_ofertadas).append("\n")
                        .append("REFEIÇÕES VENDIDAS: ").append(controllerSenha.controlRetornarUltimaSenha()).append("\n")
                        .append("REFEIÇÕES SERVIDAS: ").append(refeicoesDao.retornarTotalServido()).append("\n")
                        .append("SOBRA DE REFEIÇÕES: ").append(refeicoesDao.retornarTotalSOBRA()).append("\n")
                        .append("TOTAL PCD: ").append(rs.getInt(15)).append("\n\n")
                        .append("OCORRÊNCIAS:\n").append(ocorrencias).append("\n\n")
                        .append("EXPEDIENTE FECHADO POR: ").append(funcionarioDao.retornarUltimoLogin()).append("\n")
                        .append("DATA: ").append(dateFormat.format(date)).append("\n\n")
                        .append("PRATO CHEIO TEFÉ\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dados.toString();
    }

    public void escreverNoRELATORIOPDF(String texto) {
        try {
            Document document = new Document(PageSize.A4);
            String fileName = "RELATORIO_" + new SimpleDateFormat("dd_MM_yyyy").format(new Date()) + "_TEFÉ.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(fileName));

            document.open();
            document.add(new Paragraph(texto));
            document.close();

            Desktop.getDesktop().open(new File(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerarRelatorio(String data, String ocorrencias) {
        String texto = gerarTextoRelatorio(data, ocorrencias);
        escreverNoRELATORIOPDF(texto);
    }

    //LISTAR SENHAS. 
    public ArrayList<Senha> daoListSenhas() {
        ArrayList<Senha> listSenha = new ArrayList<>();

        try {
            // Conecta ao banco de dados
            this.getConectar();

            // Executa a consulta
            this.executarSql("SELECT * FROM tb_senhas");

            // Processa o resultado
            while (this.getResultSet().next()) {
                Senha senha = new Senha();
                senha.setId(this.getResultSet().getInt(1));
                senha.setCliente(this.getResultSet().getString(2));
                senha.setIdade(this.getResultSet().getString(3));
                senha.setGenero(this.getResultSet().getString(4));
                senha.setDeficiencia(this.getResultSet().getString(5));
                senha.setTipoSenha(this.getResultSet().getString(6));
                senha.setData_refeicao(this.getResultSet().getString(7));
                listSenha.add(senha);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar senhas: " + e.getMessage());
        } finally {
            // Fecha a conexão ao banco de dados
            this.getfecharConexao();
        }

        return listSenha;
    }

    public void gerarRelatorioPDF(String data, String ocorrencias) {
        this.getConectar();

        try {
            this.executar("SELECT * FROM tb_relatorios WHERE data ='" + data + "'");
            Relatorios relatorios = new Relatorios();
            relatorios = lerParaOBl(relatorios);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            Date date = new Date();

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("RelatorioDiario.pdf"));
            document.open();

            Font titulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Font cabecalho = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            Font normal = FontFactory.getFont(FontFactory.HELVETICA, 10);

            Paragraph title = new Paragraph("RELATÓRIO DIÁRIO\n\n", titulo);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Adicionando tabela
            PdfPTable table = new PdfPTable(2); // 2 colunas
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            while (rs.next()) {
                addRow(table, "NOVOS CADASTROS", String.valueOf(clientesDao.checarClientesCadastrados()));

                addRow(table, "CLIENTES DE 3 ATÉ 17 ANOS MASCULINO", rs.getInt(1) + "");
                addRow(table, "CLIENTES DE 3 ATÉ 17 ANOS MASCULINO (PCD)", rs.getInt(2) + "");
                addRow(table, "CLIENTES DE 3 ATÉ 17 ANOS FEMININO", rs.getInt(3) + "");
                addRow(table, "CLIENTES DE 3 ATÉ 17 ANOS FEMININO (PCD)", rs.getInt(4) + "");
                addRow(table, "CLIENTES DE 18 ATÉ 59 ANOS MASCULINO", rs.getInt(5) + "");
                addRow(table, "CLIENTES DE 18 ATÉ 59 ANOS MASCULINO (PCD)", rs.getInt(6) + "");
                addRow(table, "CLIENTES DE 18 ATÉ 59 ANOS FEMININO", rs.getInt(7) + "");
                addRow(table, "CLIENTES DE 18 ATÉ 59 ANOS FEMININO (PCD)", rs.getInt(8) + "");
                addRow(table, "A PARTIR DE 60 ANOS MASCULINO", rs.getInt(9) + "");
                addRow(table, "A PARTIR DE 60 ANOS MASCULINO (PCD)", rs.getInt(10) + "");
                addRow(table, "A PARTIR DE 60 ANOS FEMININO", rs.getInt(11) + "");
                addRow(table, "A PARTIR DE 60 ANOS FEMININO (PCD)", rs.getInt(12) + "");
                addRow(table, "PESSOAS EM SITUAÇÃO DE RUA (MASCULINO)", rs.getInt(13) + "");
                addRow(table, "PESSOAS EM SITUAÇÃO DE RUA (FEMININO)", rs.getInt(14) + "");
                addRow(table, "SEM CADASTRO (GENÉRICAS)", String.valueOf(relatorios.getGenericos()));
                addRow(table, "REFEIÇÕES OFERTADAS", String.valueOf(refeicoes_ofertadas));
                addRow(table, "REFEIÇÕES VENDIDAS", controllerSenha.controlRetornarUltimaSenha() + "");
                addRow(table, "REFEIÇÕES SERVIDAS", refeicoesDao.retornarTotalServido() + "");
                addRow(table, "SOBRA DE REFEIÇÕES", refeicoesDao.retornarTotalSOBRA() + "");
                addRow(table, "TOTAL PCD", rs.getInt(15) + "");
            }

            document.add(table);

            // Ocorrências
            Paragraph ocorr = new Paragraph("Ocorrências:\n" + ocorrencias + "\n\n", normal);
            document.add(ocorr);

            // Rodapé
            Paragraph footer = new Paragraph("EXPEDIENTE FECHADO POR: " + funcionarioDao.retornarUltimoLogin()
                    + "\nDATA: " + dateFormat.format(date)
                    + "\n\nPRATO CHEIO TEFÉ", normal);
            footer.setAlignment(Element.ALIGN_LEFT);
            document.add(footer);

            document.close();
            System.out.println("Relatório gerado com sucesso.");
        } catch (DocumentException | FileNotFoundException | SQLException e) {
        }
    }

    private void addRow(PdfPTable table, String label, String value) {
        table.addCell(new PdfPCell(new Phrase(label)));
        table.addCell(new PdfPCell(new Phrase(value)));
    }

    public FaixaEtaria classificarIdade(int idade) {
        if (idade >= 3 && idade < 17) {
            return FaixaEtaria.INFANTIL;
        } else if (idade >= 18 && idade < 59) {
            return FaixaEtaria.ADULTO;
        } else if (idade >= 60) {
            return FaixaEtaria.IDOSO;
        } else {
            return FaixaEtaria.DESCONHECIDA;
        }
    }
}
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
import java.io.IOException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class RelatoriosDao extends ConexaoBD {

    String PDF = "_PRATO_CHEIO_JESUS_ME_DEU.pdf";
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

        this.getConectar();
        try {
            this.executarSql("SELECT * FROM tb_senhas");
            while (this.getResultSet().next()) {
                totalAtendidos++;

                try {
                    int idade = Integer.parseInt(this.getResultSet().getString(3));
                    String generoStr = this.getResultSet().getString(4);
                    String respostaSim = this.getResultSet().getString(5);

                    FaixaEtaria faixa = classificarIdade(idade);
                    Genero genero = "Masculino".equalsIgnoreCase(generoStr) ? Genero.MASCULINO
                            : "Feminino".equalsIgnoreCase(generoStr) ? Genero.FEMININO : Genero.OUTRO;
                    boolean deficiente = "SIM".equalsIgnoreCase(respostaSim);

                    if (faixa == FaixaEtaria.DESCONHECIDA || genero == Genero.OUTRO) {
                        totalGenericos++;
                        continue;
                    }

                    ChaveContador chave = new ChaveContador(faixa, genero, deficiente);
                    contadores.put(chave, contadores.getOrDefault(chave, 0) + 1);

                } catch (NumberFormatException e) {
                    totalGenericos++;
                }
            }

            // Total de deficientes
            int totalDeficientes = contadores.entrySet().stream()
                    .filter(entry -> entry.getKey().deficiente)
                    .mapToInt(Map.Entry::getValue)
                    .sum();

            // Mapeia cada grupo no objeto Relatorios
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

            relatorio.setGenericos(totalGenericos);
            relatorio.setTotalDePessoasAtendidas(totalAtendidos);
            relatorio.setDeficientes(totalDeficientes);

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

    public void escreverNoRELATORIOPDF(String texto) throws FileNotFoundException {
        // Constantes para reutilização
        final String DATE_FORMAT = "dd_MM_yyyy";
        final String PDF_EXTENSION = "_PRATO_CHEIO_JESUS_ME_DEU.pdf"; //NOME DO DOCUMENTO

        // Formata a data e define o nome do arquivo
        String fileName = "RELATORIO_" + new SimpleDateFormat(DATE_FORMAT).format(new Date()) + PDF_EXTENSION;

        Document document = new Document(PageSize.A4);

        try {
            // Criação do escritor para o PDF
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            // Adiciona o texto ao PDF
            document.add(new Paragraph(texto));
        } catch (FileNotFoundException e) {
            System.err.println("Erro: Arquivo não encontrado ou não pôde ser criado. Detalhes: " + e.getMessage());
        } catch (DocumentException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar o documento PDF: " + e.getMessage());
        } finally {
            // Fecha o documento
            if (document.isOpen()) {
                document.close();
            }

            // Tenta abrir o arquivo gerado no sistema
            try {
                Desktop.getDesktop().open(new File(fileName));
            } catch (IOException e) {
                System.err.println("Erro ao tentar abrir o arquivo PDF: " + e.getMessage());
            }
        }
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

    public String lerRelatorios(String data, String ocorrencias) {
        String dados = "";

        this.getConectar();
        try {

            this.executar("select * from tb_relatorios where data ='" + data + "'");

            Relatorios relatorios = new Relatorios();
            relatorios = lerParaOBl(relatorios);

            while (rs.next()) {

                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                Date date = new Date();

                dados = "                                            RELATÓRIO DIÁRIO \n"
                        + //"---------Data: "+dateFormat.format(date)+"---------\n\n"+
                        " NOVOS CADASTROS: " + clientesDao.checarClientesCadastrados() + "\n\n"
                        + "                                          USUÁRIOS POR FAIXA ETÁRIA \n"
                        + "CLIENTES DE 3 ATÉ 17 ANOS MASCULINO: " + rs.getInt(1) + "\n" //3-17 ANOS MASCULINOS
                        + "CLIENTES DE 3 ATÉ 17 ANOS MASCULINO(PCD): " + rs.getInt(2) + "\n" //3-17 ANOS MASCULINOS (PCD)
                        + "CLIENTES DE 3 ATÉ 17 ANOS FEMININO: " + rs.getInt(3) + "\n" //3-17 ANOS FEMININO
                        + "CLIENTES DE 3 ATÉ 17 ANOS FEMININO(PCD): " + rs.getInt(4) + "\n" //3-17 ANOS FEMININO (PCD)
                        + "CLIENTES DE 18 ATÉ 59 ANOS MASCULINO: " + rs.getInt(5) + "\n" //18-59 ANOS MASCULINO
                        + "CLIENTES DE 18 ATÉ 59 ANOS MASCULINO(PCD): " + rs.getInt(6) + "\n" //18-59 ANOS MASCULINO (PCD)
                        + "CLIENTES DE 18 ATÉ 59 ANOS FEMININO: " + rs.getInt(7) + "\n" //18-59 ANOS FEMININO
                        + "CLIENTES DE 18 ATÉ 59 ANOS FEMININO(PCD): " + rs.getInt(8) + "\n" //18-59 ANOS FEMININO (PCD)
                        + "A PARTIR DE 60 ANOS MASCULINO: " + rs.getInt(9) + "\n" //A PARTIR DE 60 ANOS MASCULINO
                        + "A PARTIR DE 60 ANOS MASCULINO(PCD): " + rs.getInt(10) + "\n" //A PARTIR DE 60 ANOS MASCULINO (PCD)
                        + "A PARTIR DE 60 ANOS Feminino: " + rs.getInt(11) + "\n" //A PARTIR DE 60 ANOS FEMININO
                        + "A PARTIR DE 60 ANOS Feminino(PCD): " + rs.getInt(12) + "\n" //A PARTIR DE 60 ANOS FEMININO (PCD)
                        + "PESSOAS EM SITUAÇÃO DE RUA (MASCULINO): " + rs.getInt(13) + "\n" //PESSOAS EM SITUAÇÃO DE RUA MASCULINO
                        + "PESSOAS EM SITUAÇÃO DE RUA (FEMININO): " + rs.getInt(14) + "\n" //PESSOAS EM SITUAÇÃO DE RUA FEMININO
                        + "SEM CADASTRO (GENÉRICAS): " + relatorios.getGenericos() + "\n\n" //PESSOAS SEM CADASTROS
                        + "REFEIÇÕES OFERTADAS: " + refeicoes_ofertadas + "\n"//REFEIÇÕES OFERTADAS (QTD. REFEIÇÕES DE UNIDADES)
                        + "REFEIÇÕES VENDIDAS: " + controllerSenha.controlRetornarUltimaSenha() + "\n"//REFEIÇÕES VENDIDAS
                        + "REFEIÇÕES SERVIDAS " + refeicoesDao.retornarTotalServido() + "\n"//REFEIÇÕES SERVIDAS
                        + "SOBRA DE REFEIÇÕES " + refeicoesDao.retornarTotalSOBRA() + "\n"//SOBRA DE REFEIÇÕES
                        + "Total de atendimento à Pessoas com Deficiência (PCD) " + rs.getInt(15) + "\n"// TOTAL DE PESSOAS COM DEFICIÊNCIA
                        + ocorrencias + "\n\n"
                        + "EXPEDIENTE FECHADO POR: " + funcionarioDao.retornarUltimoLogin() + "\n DATA: " + dateFormat.format(date) + "\n\nPRATO CHEIO JESUS ME DEU";
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na consulta" + e.getMessage());
        }

        return dados;
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

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

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Statement;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class RelatoriosDao extends ConexaoBD {

    String PDF = "_PRATO_VIVER_MELHOR.pdf";

    ControllerSenha controllerSenha = new ControllerSenha();
    RefeicoesDao refeicoesDao = new RefeicoesDao();
    FuncionarioDao funcionarioDao = new FuncionarioDao();
    ClientesDao clientesDao = new ClientesDao();

    public Relatorios lerParaOBl(Relatorios relatorio) {
        int contador1 = 0;
        int contador2 = 0;
        int contador3 = 0;
        int contador4 = 0;
        int contador5 = 0;
        int contador6 = 0;
        int contador8 = 0;
        int contador9 = 0;
        int contador10 = 0;

        this.getConectar();
        try {
            this.executarSql("select * from tb_senhas");

            while (this.getResultSet().next()) {

                contador10++;

                if (Integer.parseInt(this.getResultSet().getString(3)) >= 0 && Integer.parseInt(this.getResultSet().getString(3)) < 16 && this.getResultSet().getString(4).equalsIgnoreCase("Masculino")) {
                    contador1++;
                    if (this.getResultSet().getString(5).equalsIgnoreCase("SIM")) {
                        contador8++;
                    }
                } else if (Integer.parseInt(this.getResultSet().getString(3)) >= 0 && Integer.parseInt(this.getResultSet().getString(3)) < 16 && this.getResultSet().getString(4).equalsIgnoreCase("Feminino")) {
                    contador2++;
                    if (this.getResultSet().getString(5).equalsIgnoreCase("SIM")) {
                        contador8++;
                    }
                } else if (Integer.parseInt(this.getResultSet().getString(3)) >= 16 && Integer.parseInt(this.getResultSet().getString(3)) < 60 && this.getResultSet().getString(4).equalsIgnoreCase("Masculino")) {
                    contador3++;
                    if (this.getResultSet().getString(5).equalsIgnoreCase("SIM")) {
                        contador8++;
                    }
                } else if (Integer.parseInt(this.getResultSet().getString(3)) >= 16 && Integer.parseInt(this.getResultSet().getString(3)) < 60 && this.getResultSet().getString(4).equalsIgnoreCase("Feminino")) {
                    contador4++;
                    if (this.getResultSet().getString(5).equalsIgnoreCase("SIM")) {
                        contador8++;
                    }
                } else if (Integer.parseInt(this.getResultSet().getString(3)) >= 60 && this.getResultSet().getString(4).equalsIgnoreCase("Masculino")) {
                    contador5++;
                    if (this.getResultSet().getString(5).equalsIgnoreCase("SIM")) {
                        contador8++;
                    }
                } else if (Integer.parseInt(this.getResultSet().getString(3)) >= 60 && this.getResultSet().getString(4).equalsIgnoreCase("Feminino")) {
                    contador6++;
                    if (this.getResultSet().getString(5).equalsIgnoreCase("SIM")) {
                        contador8++;
                    }
                } else if (this.getResultSet().getString(6).equalsIgnoreCase("1")) {
                    contador9++;
                } else {
                    contador3++;
                }
            }
            relatorio.set0a16Masculino(contador1);
            relatorio.set0a16Feminino(contador2);
            relatorio.set16a60Masculino(contador3);
            relatorio.set16a60Feminino(contador4);
            relatorio.set60Masculino(contador5);
            relatorio.set60Feminino(contador6);
            relatorio.setDeficientes(contador8);
            relatorio.setGenericos(contador9);
            relatorio.setTotalDePessoasAtendidas(contador10);

            return relatorio;

        } catch (SQLException e) {
            return relatorio;
        }
    }

    public boolean inserirNosRelatorios(Relatorios relatorio) {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        try {
            Statement st = con.createStatement();
            st.executeUpdate("insert into tb_relatorios values(" + relatorio.get0a16Masculino() + ", " + relatorio.get0a16Feminino() + ", " + relatorio.get16a60Masculino() + ", " + relatorio.get16a60Feminino() + ", " + relatorio.get60Masculino() + ", " + relatorio.get60Feminino() + ", " + relatorio.getDeficientes() + ", " + relatorio.getGenericos() + ", " + relatorio.getTotalDePessoasAtendidas() + ", '" + dateFormat.format(date) + "')");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void escreverNoRELATORIOPDF(String texto) throws FileNotFoundException {

        Document document = new Document(PageSize.A4);

        try {
            DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
            Date date = new Date();

            PdfWriter.getInstance(document, new FileOutputStream("RELATORIO_" + dateFormat.format(date) + PDF));

            document.open();

            document.add(new Paragraph(texto));

        } catch (FileNotFoundException ex) {
            System.out.print(ex);
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Erro na consulta" + ex.getMessage());
        } finally {
            document.close();

            try {
                DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy");
                Date date = new Date();

//                Runtime.getRuntime().exec(new String[]{"cmd.exe", "/C", "start", "RELATORIO_" + dateFormat.format(date) + "_Restaurante_Cidadão.pdf"});
                java.awt.Desktop.getDesktop().open(new File("RELATORIO_" + dateFormat.format(date) + PDF));

            } catch (IOException e) {

            }
        }

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

                dados = "------------RELATÓRIO DIÁRIO-------------\n"
                        + //"---------Data: "+dateFormat.format(date)+"---------\n\n"+
                        "1 - REFEIÇÕES VENDIDAS: " + controllerSenha.controlRetornarUltimaSenha() + "\n"
                        + "2 - CLIENTES CADASTRADOS: " + clientesDao.checarClientesCadastrados() + "\n\n"
                        + "3 - CLIENTES DE 0 ATÉ 16 ANOS MASCULINO: " + rs.getInt(1) + "\n"
                        + "4 - CLIENTES DE 0 ATÉ 16 ANOS FEMININO: " + rs.getInt(2) + "\n"
                        + "5 - CLIENTES DE 16 ATÉ 60 anos Masculino: " + rs.getInt(3) + "\n"
                        + "6 - CLIENTES DE 16 ATÉ 60 anos Feminino: " + rs.getInt(4) + "\n"
                        + "7 - CLIENTES ACIMA de 60 Masculino: " + rs.getInt(5) + "\n"
                        + "8 - CLIENTES acima de 60 anos Feminino: " + rs.getInt(6) + "\n"
                        + "9 - CLIENTES com deficiência: " + rs.getInt(7) + "\n"
                        + "10 - Senhas Genéricas: " + relatorios.getGenericos() + "\n\n"
                        + "11 - Refeições Servidas: " + refeicoesDao.retornarTotalServido() + "\n"
                        + ocorrencias + "\n\n"
                        + "EXPEDIENTE FECHADO POR: " + funcionarioDao.retornarUltimoLogin() + "\n DATA: " + dateFormat.format(date) + "\n\nPRATO CHEIO VIVER MELHOR";
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na consulta" + e.getMessage());
        }

        return dados;
    }

}

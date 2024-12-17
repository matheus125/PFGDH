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
import static com.sun.tools.javac.util.Constants.format;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class RelatoriosDao extends ConexaoBD {

    String PDF = "_PRATO_BAIRRO_UNIÃO.pdf";
    String nome = "";
    String refeicoes_ofertadas = "400";

    ControllerSenha controllerSenha = new ControllerSenha();
    RefeicoesDao refeicoesDao = new RefeicoesDao();
    FuncionarioDao funcionarioDao = new FuncionarioDao();
    ClientesDao clientesDao = new ClientesDao();

    public Relatorios lerParaOBl(Relatorios relatorio) {
        int contador1 = 0, contador2 = 0, contador3 = 0, contador4 = 0, contador5 = 0, contador6 = 0, contador7 = 0;

        int contador8 = 0;
        int contador9 = 0;
        int contador10 = 0;
        int contador11 = 0;
        int contador12 = 0;
        int contador13 = 0;
        int contador14 = 0;
        int contador15 = 0;
        int contador16 = 0;
        int contador17 = 0;
        int contador18 = 0;
        int contador19 = 0;
        int contador20 = 0;
        int contador21 = 0;
        int contador22 = 0;
        int contador23 = 0;
        int contador24 = 0;
        int contador25 = 0;

        this.getConectar();
        try {
            this.executarSql("select * from tb_senhas");

            while (this.getResultSet().next()) {

                contador18++;

                // 3-17 anos Masculinos
                if (Integer.parseInt(this.getResultSet().getString(3)) >= 3 && Integer.parseInt(this.getResultSet().getString(3)) < 17 && this.getResultSet().getString(4).equalsIgnoreCase("Masculino")) {
                    contador1++;
                    if (this.getResultSet().getString(5).equalsIgnoreCase("SIM")) {
                        contador16++;
                    }
                    // 3-17 anos Masculinos (PCD)    
                } else if (Integer.parseInt(this.getResultSet().getString(3)) >= 3 && Integer.parseInt(this.getResultSet().getString(3)) < 17 && this.getResultSet().getString(4).equalsIgnoreCase("Masculino")) {
                    contador2++;
                    if (this.getResultSet().getString(5).equalsIgnoreCase("SIM")) {
                        contador16++;
                    }

                    // 3-17 anos Femininos 
                } else if (Integer.parseInt(this.getResultSet().getString(3)) >= 3 && Integer.parseInt(this.getResultSet().getString(3)) < 17 && this.getResultSet().getString(4).equalsIgnoreCase("Feminino")) {
                    contador3++;
                    if (this.getResultSet().getString(5).equalsIgnoreCase("SIM")) {
                        contador19++;
                    }
                    // 3-17 ANOS Femininos (PCD)
                } else if (Integer.parseInt(this.getResultSet().getString(3)) >= 3 && Integer.parseInt(this.getResultSet().getString(3)) < 17 && this.getResultSet().getString(4).equalsIgnoreCase("Feminino")) {
                    contador4++;
                    if (this.getResultSet().getString(5).equalsIgnoreCase("SIM")) {
                        contador19++;
                    }

                    // 18-59 anos Masculinos 
                } else if (Integer.parseInt(this.getResultSet().getString(3)) >= 18 && Integer.parseInt(this.getResultSet().getString(3)) < 59 && this.getResultSet().getString(4).equalsIgnoreCase("Masculino")) {
                    contador5++;
                    if (this.getResultSet().getString(5).equalsIgnoreCase("SIM")) {
                        contador20++;
                    }
                    // 18-59 anos Masculinos (PCD)
                } else if (Integer.parseInt(this.getResultSet().getString(3)) >= 18 && Integer.parseInt(this.getResultSet().getString(3)) < 59 && this.getResultSet().getString(4).equalsIgnoreCase("Masculino")) {
                    contador6++;
                    if (this.getResultSet().getString(5).equalsIgnoreCase("SIM")) {
                        contador20++;
                    }

                    // 18-59 anos Feminino 
                } else if (Integer.parseInt(this.getResultSet().getString(3)) >= 18 && Integer.parseInt(this.getResultSet().getString(3)) < 59 && this.getResultSet().getString(4).equalsIgnoreCase("Feminino")) {
                    contador7++;
                    if (this.getResultSet().getString(5).equalsIgnoreCase("SIM")) {
                        contador21++;
                    }

                    // 18-59 anos Feminino (PCD)
                } else if (Integer.parseInt(this.getResultSet().getString(3)) >= 18 && Integer.parseInt(this.getResultSet().getString(3)) < 59 && this.getResultSet().getString(4).equalsIgnoreCase("Feminino")) {
                    contador8++;
                    if (this.getResultSet().getString(5).equalsIgnoreCase("SIM")) {
                        contador21++;
                    }

                    // A partir de 60 anos Masculino 
                } else if (Integer.parseInt(this.getResultSet().getString(3)) >= 60 && this.getResultSet().getString(4).equalsIgnoreCase("Masculino")) {
                    contador9++;
                    if (this.getResultSet().getString(5).equalsIgnoreCase("SIM")) {
                        contador22++;
                    }
                    // A partir de 60 anos Masculino (PCD)
                } else if (Integer.parseInt(this.getResultSet().getString(3)) >= 60 && this.getResultSet().getString(4).equalsIgnoreCase("Feminino")) {
                    contador10++;
                    if (this.getResultSet().getString(5).equalsIgnoreCase("SIM")) {
                        contador22++;
                    }

                    // A partir de 60 anos Feminino
                } else if (Integer.parseInt(this.getResultSet().getString(3)) >= 60 && this.getResultSet().getString(4).equalsIgnoreCase("Masculino")) {
                    contador11++;
                    if (this.getResultSet().getString(5).equalsIgnoreCase("SIM")) {
                        contador23++;
                    }

                    // A partir de 60 anos Feminino (PCD)
                } else if (Integer.parseInt(this.getResultSet().getString(3)) >= 60 && this.getResultSet().getString(4).equalsIgnoreCase("Feminino")) {
                    contador12++;
                    if (this.getResultSet().getString(5).equalsIgnoreCase("SIM")) {
                        contador23++;
                    }

                    // Pessoas em situação de rua Masculino
                } else if (Integer.parseInt(this.getResultSet().getString(3)) >= 60 && this.getResultSet().getString(4).equalsIgnoreCase("Masculino")) {
                    contador24++;

                    // Pessoas em situação de rua Feminino
                } else if (Integer.parseInt(this.getResultSet().getString(3)) >= 60 && this.getResultSet().getString(4).equalsIgnoreCase("Feminino")) {
                    contador14++;
                    if (this.getResultSet().getString(5).equalsIgnoreCase("SIM")) {
                        contador25++;
                    }

                } else if (this.getResultSet().getString(3).equalsIgnoreCase("1")) {
                    contador12++;
                } else {
                    contador3++;
                }
            }

            contador17 = contador16 + contador19 + contador20 + contador21 + contador22 + contador23;

            relatorio.set3_17_ANOS_MASCULINO(contador1);
            relatorio.set3_17_ANOS_MASCULINO_CPD(contador16);
            relatorio.set3_17_ANOS_FEMININO(contador3);
            relatorio.set3_17_ANOS_FEMININO_CPD(contador19);
            relatorio.set18_59_ANOS_MASCULINO(contador5);
            relatorio.set18_59_ANOS_MASCULINO_CPD(contador20);
            relatorio.set18_59_ANOS_FEMININO(contador7);
            relatorio.set18_59_ANOS_FEMININO_CPD(contador21);
            relatorio.setA_PARTIR_DE_60_ANOS_MASCULINO(contador9);
            relatorio.setA_PARTIR_DE_60_ANOS_MASCULINO_CPD(contador22);
            relatorio.setA_PARTIR_DE_60_ANOS_FEMININO(contador11);
            relatorio.setA_PARTIR_DE_60_ANOS_FEMININO_CPD(contador23);
            relatorio.setPESSOAS_SITUACAO_RUA_MASCULINO(contador24);
            relatorio.setPESSOAS_SITUACAO_RUA_FEMININO(contador25);
            relatorio.setGenericos(contador12);
            relatorio.setTotalDePessoasAtendidas(contador18);
            relatorio.setDeficientes(contador17);

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

    //LISTAR SENHAS. 
    public ArrayList<Senha> daoListSenhas() {
        ArrayList<Senha> listSenha = new ArrayList<>();
        Senha senha = new Senha();

        try {
            this.getConectar();
            this.executarSql("SELECT * FROM tb_senhas");
            while (this.getResultSet().next()) {
                senha = new Senha();
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
            System.out.println("Erro: " + e.getMessage());
        } finally {
            this.getfecharConexao();
        }
//        relatorio.set3_17_ANOS_MASCULINO(contador1);

        senha.setCliente(nome);

        return listSenha;
    }//FIM.

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
                        + "EXPEDIENTE FECHADO POR: " + funcionarioDao.retornarUltimoLogin() + "\n DATA: " + dateFormat.format(date) + "\n\nPRATO CHEIO BAIRRO DA UNIÃO";
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na consulta" + e.getMessage());
        }

        return dados;
    }
}

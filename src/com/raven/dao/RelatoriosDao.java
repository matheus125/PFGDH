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
<<<<<<< HEAD
import java.awt.Desktop;
=======
>>>>>>> eb9b89a (PFGDH_2.1)

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Statement;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;
=======
>>>>>>> eb9b89a (PFGDH_2.1)
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class RelatoriosDao extends ConexaoBD {

<<<<<<< HEAD
    String PDF = "_PRATO_ALEIXO.pdf";
=======
    String PDF = "_PRATO_BAIRRO_UNIÃO.pdf";
>>>>>>> eb9b89a (PFGDH_2.1)
    String nome = "";
    String refeicoes_ofertadas = "400";

    ControllerSenha controllerSenha = new ControllerSenha();
    RefeicoesDao refeicoesDao = new RefeicoesDao();
    FuncionarioDao funcionarioDao = new FuncionarioDao();
    ClientesDao clientesDao = new ClientesDao();

<<<<<<< HEAD
//    public Relatorios lerParaOBl(Relatorios relatorio) {
//        // Uso de um mapa para armazenar contadores dinamicamente
//        Map<String, Integer> contadores = new HashMap<>();
//        String[] chaves = {
//            "contador1", "contador2", "contador3", "contador4", "contador5",
//            "contador6", "contador7", "contador8", "contador9", "contador10",
//            "contador11", "contador12", "contador13", "contador14", "contador15",
//            "contador16", "contador17", "contador18", "contador19", "contador20",
//            "contador21", "contador22", "contador23", "contador24", "contador25"
//        };
//
//        // Inicializa os contadores no mapa
//        for (String chave : chaves) {
//            contadores.put(chave, 0);
//        }
//
//        this.getConectar();
//        try {
//            this.executarSql("select * from tb_senhas");
//
//            while (this.getResultSet().next()) {
//                contadores.put("contador18", contadores.get("contador18") + 1); // Incrementa o total de pessoas atendidas
//
//                String idadeString = this.getResultSet().getString(3);
//                String genero = this.getResultSet().getString(4);
//                String respostaSim = this.getResultSet().getString(5);
//                String valorColuna = this.getResultSet().getString(3);
//
//                try {
//                    int idade = Integer.parseInt(idadeString);
//
//                    // Identifica a faixa etária e o gênero, atualizando os contadores
//                    if (idade >= 3 && idade < 17) {
//                        if ("Masculino".equalsIgnoreCase(genero)) {
//                            contadores.put("contador1", contadores.get("contador1") + 1);
//                            if ("SIM".equalsIgnoreCase(respostaSim)) {
//                                contadores.put("contador16", contadores.get("contador16") + 1);
//                            }
//                        } else if ("Feminino".equalsIgnoreCase(genero)) {
//                            contadores.put("contador3", contadores.get("contador3") + 1);
//                            if ("SIM".equalsIgnoreCase(respostaSim)) {
//                                contadores.put("contador19", contadores.get("contador19") + 1);
//                            }
//                        }
//                    } else if (idade >= 18 && idade < 59) {
//                        if ("Masculino".equalsIgnoreCase(genero)) {
//                            contadores.put("contador5", contadores.get("contador5") + 1);
//                            if ("SIM".equalsIgnoreCase(respostaSim)) {
//                                contadores.put("contador20", contadores.get("contador20") + 1);
//                            }
//                        } else if ("Feminino".equalsIgnoreCase(genero)) {
//                            contadores.put("contador7", contadores.get("contador7") + 1);
//                            if ("SIM".equalsIgnoreCase(respostaSim)) {
//                                contadores.put("contador21", contadores.get("contador21") + 1);
//                            }
//                        }
//                    } else if (idade >= 60) {
//                        if ("Masculino".equalsIgnoreCase(genero)) {
//                            contadores.put("contador9", contadores.get("contador9") + 1);
//                            if ("SIM".equalsIgnoreCase(respostaSim)) {
//                                contadores.put("contador22", contadores.get("contador22") + 1);
//                            }
//                            contadores.put("contador24", contadores.get("contador24") + 1);
//                        } else if ("Feminino".equalsIgnoreCase(genero)) {
//                            contadores.put("contador11", contadores.get("contador11") + 1);
//                            if ("SIM".equalsIgnoreCase(respostaSim)) {
//                                contadores.put("contador23", contadores.get("contador23") + 1);
//                            }
//                            contadores.put("contador25", contadores.get("contador25") + 1);
//                        }
//                    } else if ("1".equalsIgnoreCase(valorColuna)) {
//                        contadores.put("contador12", contadores.get("contador12") + 1);
//                    }
//                } catch (NumberFormatException e) {
//                    System.err.println("Erro ao converter idade para inteiro: " + idadeString);
//                    contadores.put("contador12", contadores.get("contador12") + 1); // Incrementa o genérico em caso de erro
//                }
//            }
//
//            // Calcula o total de deficientes
//            contadores.put("contador17", contadores.get("contador16") + contadores.get("contador19")
//                    + contadores.get("contador20") + contadores.get("contador21")
//                    + contadores.get("contador22") + contadores.get("contador23"));
//
//            // Atualiza o objeto Relatorios
//            relatorio.set3_17_ANOS_MASCULINO(contadores.get("contador1"));
//            relatorio.set3_17_ANOS_MASCULINO_CPD(contadores.get("contador16"));
//            relatorio.set3_17_ANOS_FEMININO(contadores.get("contador3"));
//            relatorio.set3_17_ANOS_FEMININO_CPD(contadores.get("contador19"));
//            relatorio.set18_59_ANOS_MASCULINO(contadores.get("contador5"));
//            relatorio.set18_59_ANOS_MASCULINO_CPD(contadores.get("contador20"));
//            relatorio.set18_59_ANOS_FEMININO(contadores.get("contador7"));
//            relatorio.set18_59_ANOS_FEMININO_CPD(contadores.get("contador21"));
//            relatorio.setA_PARTIR_DE_60_ANOS_MASCULINO(contadores.get("contador9"));
//            relatorio.setA_PARTIR_DE_60_ANOS_MASCULINO_CPD(contadores.get("contador22"));
//            relatorio.setA_PARTIR_DE_60_ANOS_FEMININO(contadores.get("contador11"));
//            relatorio.setA_PARTIR_DE_60_ANOS_FEMININO_CPD(contadores.get("contador23"));
//            relatorio.setPESSOAS_SITUACAO_RUA_MASCULINO(contadores.get("contador24"));
//            relatorio.setPESSOAS_SITUACAO_RUA_FEMININO(contadores.get("contador25"));
//            relatorio.setGenericos(contadores.get("contador12"));
//            relatorio.setTotalDePessoasAtendidas(contadores.get("contador18"));
//            relatorio.setDeficientes(contadores.get("contador17"));
//
//            return relatorio;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return relatorio;
//        }
//    }
    public Relatorios lerParaOBl(Relatorios relatorio) {
        // Uso de um mapa para armazenar contadores dinamicamente
        Map<String, Integer> contadores = new HashMap<>();
        String[] chaves = {
            "contador1", "contador2", "contador3", "contador4", "contador5",
            "contador6", "contador7", "contador8", "contador9", "contador10",
            "contador11", "contador12", "contador13", "contador14", "contador15",
            "contador16", "contador17", "contador18", "contador19", "contador20",
            "contador21", "contador22", "contador23", "contador24", "contador25"
        };

        // Inicializa os contadores no mapa
        for (String chave : chaves) {
            contadores.put(chave, 0);
        }
=======
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
>>>>>>> eb9b89a (PFGDH_2.1)

        this.getConectar();
        try {
            this.executarSql("select * from tb_senhas");

            while (this.getResultSet().next()) {
<<<<<<< HEAD
                contadores.put("contador18", contadores.get("contador18") + 1); // Incrementa o total de pessoas atendidas

                String idadeString = this.getResultSet().getString(3);
                String genero = this.getResultSet().getString(4);
                String respostaSim = this.getResultSet().getString(5);
                String valorColuna = this.getResultSet().getString(3);

                try {
                    int idade = Integer.parseInt(idadeString);
                    boolean isDeficiente = "SIM".equalsIgnoreCase(respostaSim); // Verifica se a pessoa é deficiente

                    // Identifica a faixa etária e o gênero, atualizando os contadores
                    if (idade >= 3 && idade < 17) {
                        if ("Masculino".equalsIgnoreCase(genero)) {
                            // Se for deficiente, incrementa no contador de deficientes
                            if (isDeficiente) {
                                contadores.put("contador16", contadores.get("contador16") + 1);
                            } else {
                                contadores.put("contador1", contadores.get("contador1") + 1);
                            }
                        } else if ("Feminino".equalsIgnoreCase(genero)) {
                            if (isDeficiente) {
                                contadores.put("contador19", contadores.get("contador19") + 1);
                            } else {
                                contadores.put("contador3", contadores.get("contador3") + 1);
                            }
                        }
                    } else if (idade >= 18 && idade < 59) {
                        if ("Masculino".equalsIgnoreCase(genero)) {
                            if (isDeficiente) {
                                contadores.put("contador20", contadores.get("contador20") + 1);
                            } else {
                                contadores.put("contador5", contadores.get("contador5") + 1);
                            }
                        } else if ("Feminino".equalsIgnoreCase(genero)) {
                            if (isDeficiente) {
                                contadores.put("contador21", contadores.get("contador21") + 1);
                            } else {
                                contadores.put("contador7", contadores.get("contador7") + 1);
                            }
                        }
                    } else if (idade >= 60) {
                        if ("Masculino".equalsIgnoreCase(genero)) {
                            if (isDeficiente) {
                                contadores.put("contador22", contadores.get("contador22") + 1);
                            } else {
                                contadores.put("contador9", contadores.get("contador9") + 1);
                            }
                            contadores.put("contador24", contadores.get("contador24") + 1);
                        } else if ("Feminino".equalsIgnoreCase(genero)) {
                            if (isDeficiente) {
                                contadores.put("contador23", contadores.get("contador23") + 1);
                            } else {
                                contadores.put("contador11", contadores.get("contador11") + 1);
                            }
                            contadores.put("contador25", contadores.get("contador25") + 1);
                        }
                    } else if ("1".equalsIgnoreCase(valorColuna)) {
                        contadores.put("contador12", contadores.get("contador12") + 1);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao converter idade para inteiro: " + idadeString);
                    contadores.put("contador12", contadores.get("contador12") + 1); // Incrementa o genérico em caso de erro
                }
            }

            // Calcula o total de deficientes
            contadores.put("contador17", contadores.get("contador16") + contadores.get("contador19")
                    + contadores.get("contador20") + contadores.get("contador21")
                    + contadores.get("contador22") + contadores.get("contador23"));

            // Atualiza o objeto Relatorios
            relatorio.set3_17_ANOS_MASCULINO(contadores.get("contador1"));
            relatorio.set3_17_ANOS_MASCULINO_CPD(contadores.get("contador16"));
            relatorio.set3_17_ANOS_FEMININO(contadores.get("contador3"));
            relatorio.set3_17_ANOS_FEMININO_CPD(contadores.get("contador19"));
            relatorio.set18_59_ANOS_MASCULINO(contadores.get("contador5"));
            relatorio.set18_59_ANOS_MASCULINO_CPD(contadores.get("contador20"));
            relatorio.set18_59_ANOS_FEMININO(contadores.get("contador7"));
            relatorio.set18_59_ANOS_FEMININO_CPD(contadores.get("contador21"));
            relatorio.setA_PARTIR_DE_60_ANOS_MASCULINO(contadores.get("contador9"));
            relatorio.setA_PARTIR_DE_60_ANOS_MASCULINO_CPD(contadores.get("contador22"));
            relatorio.setA_PARTIR_DE_60_ANOS_FEMININO(contadores.get("contador11"));
            relatorio.setA_PARTIR_DE_60_ANOS_FEMININO_CPD(contadores.get("contador23"));
            relatorio.setPESSOAS_SITUACAO_RUA_MASCULINO(contadores.get("contador24"));
            relatorio.setPESSOAS_SITUACAO_RUA_FEMININO(contadores.get("contador25"));
            relatorio.setGenericos(contadores.get("contador12"));
            relatorio.setTotalDePessoasAtendidas(contadores.get("contador18"));
            relatorio.setDeficientes(contadores.get("contador17"));
=======

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
>>>>>>> eb9b89a (PFGDH_2.1)

            return relatorio;

        } catch (SQLException e) {
<<<<<<< HEAD
            e.printStackTrace();
=======
>>>>>>> eb9b89a (PFGDH_2.1)
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
<<<<<<< HEAD
        // Constantes para reutilização
        final String DATE_FORMAT = "dd_MM_yyyy";
        final String PDF_EXTENSION = "_ALEIXO.pdf"; //NOME DO DOCUMENTO

        // Formata a data e define o nome do arquivo
        String fileName = "RELATORIO_" + new SimpleDateFormat(DATE_FORMAT).format(new Date()) + PDF_EXTENSION;
=======
>>>>>>> eb9b89a (PFGDH_2.1)

        Document document = new Document(PageSize.A4);

        try {
<<<<<<< HEAD
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
=======
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

>>>>>>> eb9b89a (PFGDH_2.1)
            }
        }
    }

    //LISTAR SENHAS. 
    public ArrayList<Senha> daoListSenhas() {
        ArrayList<Senha> listSenha = new ArrayList<>();
<<<<<<< HEAD

        try {
            // Conecta ao banco de dados
            this.getConectar();

            // Executa a consulta
            this.executarSql("SELECT * FROM tb_senhas");

            // Processa o resultado
            while (this.getResultSet().next()) {
                Senha senha = new Senha();
=======
        Senha senha = new Senha();

        try {
            this.getConectar();
            this.executarSql("SELECT * FROM tb_senhas");
            while (this.getResultSet().next()) {
                senha = new Senha();
>>>>>>> eb9b89a (PFGDH_2.1)
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
<<<<<<< HEAD
            System.err.println("Erro ao listar senhas: " + e.getMessage());
        } finally {
            // Fecha a conexão ao banco de dados
            this.getfecharConexao();
        }

        return listSenha;
    }
=======
            System.out.println("Erro: " + e.getMessage());
        } finally {
            this.getfecharConexao();
        }
//        relatorio.set3_17_ANOS_MASCULINO(contador1);

        senha.setCliente(nome);

        return listSenha;
    }//FIM.
>>>>>>> eb9b89a (PFGDH_2.1)

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
<<<<<<< HEAD
                        + "EXPEDIENTE FECHADO POR: " + funcionarioDao.retornarUltimoLogin() + "\n DATA: " + dateFormat.format(date) + "\n\nPRATO CHEIO ALEIXO";
=======
                        + "EXPEDIENTE FECHADO POR: " + funcionarioDao.retornarUltimoLogin() + "\n DATA: " + dateFormat.format(date) + "\n\nPRATO CHEIO BAIRRO DA UNIÃO";
>>>>>>> eb9b89a (PFGDH_2.1)
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na consulta" + e.getMessage());
        }

        return dados;
    }
}

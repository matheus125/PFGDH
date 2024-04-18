package com.raven.dao;

import com.raven.banco.ConexaoBD;
import com.raven.model.Senha;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class SenhaDao extends ConexaoBD {
    
    
    String texto = "PRATO CHEIO VIVER MELHOR";

    //SALVAR SENHAS TITULADAS AOS CLIENTES OU DEPENDENTES.
    public boolean daoSalvarSenha(Senha senha) {

        String SalvarSenha = "call sp_salvar_senhas ("
                + "'" + senha.getCliente() + "',"
                + "'" + senha.getGenero() + "',"
                + "'" + senha.getIdade() + "',"
                + "'" + senha.getDeficiencia()+ "',"
                + "'" + senha.getData_refeicao() + "'"
                + ")";
        try {
            this.getConectar();
            this.executarSql(SalvarSenha);
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            return true;
        } catch (Exception erro) {
            return false;
        } finally {
            this.getfecharConexao();
        }
    }//FIM.

    //SALVAR SENHAS GENERICAS NO BANCO DE DADOS.
    public boolean daoSalvarSenhaGenerico(Senha senha) {
        String SalvarSenha = "call sp_salvar_senhas_genericas ("
                + "'" + senha.getData_refeicao() + "'"
                + ")";
        try {
            this.getConectar();
            this.executarSql(SalvarSenha);
            return true;
        } catch (Exception erro) {
            return false;
        } finally {
            this.getfecharConexao();
        }
    }//FIM.

    //RETORNA A ULTIMA SENHA PARA CONTAR NO SISTEMA.
    public int retornarUltimaSenha() {
        int contador = 0;
        this.getConectar();
        try {
            this.executarSql("select * from tb_senhas order by id desc limit 1");

            while (this.getResultSet().next()) {
                contador = this.getResultSet().getInt(1);
            }

            return contador;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro" + e);
            return contador;
        }
    }//FIM.

    //VERIFICAR SE CLIENTE JÁ REALIZOU A COMPRAR DE SENHA.
    public boolean checarSenhaCliente(String nome) {
        this.getConectar();
        try {
            this.executarSql("select * from tb_senhas where cliente='" + nome + "'");
            return this.getResultSet().next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro" + e);
            return false;
        }
    }//FIM.

    //CRIA O ARQUIVO PARA LEITURA EM TXT
    public void escreverNoTXT(String texto) {
        File file = new File("SENHA.txt");
        Path caminho = Paths.get("SENHA.txt");
        byte[] textpEmByte = texto.getBytes();

        try {
            Files.write(caminho, textpEmByte);
            //java.awt.Desktop.getDesktop().open(file);
            java.awt.Desktop.getDesktop().print(file);
        } catch (IOException e) {

        }
    }//FIM.

    //RECUPERA A SENHAS E IMPRIME NA IMPRESSORA.
    public String recuperarSenha() {
        this.getConectar();
        String dadosSenha = "";

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        
        String txt = "GOVERNO DO ESTADO DO AMAZONAS\n\n"
                + "           SENHA: ";
        try {
            this.executarSql("select id from tb_senhas order by id desc limit 1");
            while (this.getResultSet().next()) {
                dadosSenha = ("" + dadosSenha + "" + (this.getResultSet().getInt(1)) + "  ");
            }
            return txt + dadosSenha + "\n\n" + dateFormat.format(date) + "\n\n" + texto;
        } catch (SQLException e) {
            return txt + dadosSenha + "\n\n"
                    + "\n\n-----------------------------------\n\n";
        }
    }//FIM.

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
        return listSenha;
    }//FIM.

    //VERIFICAÇÃO DE SENHA DO USUARIO PARA FECHAR O SISTEMA.
    public int chegarSenha(String senha) {
        int resultado = 0;

        this.getConectar();

        try {
            this.executarSql("SELECT * FROM tb_user where password = '" + senha + "'");
            while (getResultSet().next()) {
                resultado = 1;
            }
            return resultado;
        } catch (SQLException e) {
            return resultado;
        }
    }//FIM.

    //METODO PARA LIMPAR TABELA "tb_senhas", AO FECHAR O DIA.
    public boolean limparTabelaSenhas() {

        try {
            this.getConectar();
            this.executarSql("call sp_limpar_senhas");
            return true;
        } catch (Exception e) {
            return false;
        }
    }//FIM.
}

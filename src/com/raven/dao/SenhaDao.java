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

    //SALVAR SENHAS TITULADAS AOS CLIENTES OU DEPENDENTES.
    public boolean daoSalvarSenha(Senha senha) {

        String SalvarSenha = "call sp_salvar_senhas ("
                + "'" + senha.getCliente() + "',"
                + "'" + senha.getCpf() + "',"
                + "'" + senha.getGenero() + "',"
                + "'" + senha.getIdade() + "',"
                + "'" + senha.getDeficiencia() + "',"
                + "'" + senha.getStatus_cliente() + "',"
                + "'" + senha.getData_refeicao() + "'"
                + ")";
        try {
            this.getConectar();
            this.executarSql(SalvarSenha);
//            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
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
    public boolean checarSenhaCliente(String cpf) {
        this.getConectar();
        try {
            this.executarSql("select * from tb_senhas where cpf ='" + cpf + "'");
            return this.getResultSet().next();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro" + e);
            return false;
        }
    }//FIM.

    //VERIFICAR SE CLIENTE JÁ REALIZOU A COMPRAR DE SENHA.
    public boolean checarSenhaDependente(String nome) {
        this.getConectar();
        try {
            this.executarSql("select * from tb_senhas where cliente ='" + nome + "'");
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
//            java.awt.Desktop.getDesktop().open(file);
            java.awt.Desktop.getDesktop().print(file);
        } catch (IOException e) {

        }
    }//FIM.

    public String recuperarSenha() {
    this.getConectar();
    String senha = "";
    String nomeCliente = "";
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date dataAtual = new Date();
    
    String separador = "-----------------------------------\n";

    StringBuilder texto = new StringBuilder();
    texto.append("GOVERNO DO ESTADO DO AMAZONAS\n\n");
    texto.append("           SENHA: ");

    try {
        this.executarSql("SELECT id, cliente FROM tb_senhas ORDER BY id DESC LIMIT 1");

        if (this.getResultSet().next()) {
            senha = String.valueOf(this.getResultSet().getInt("id"));
            nomeCliente = this.getResultSet().getString("cliente");

            texto.append(senha).append("\n");
            texto.append("NOME: ").append(nomeCliente).append("\n");
            texto.append(dateFormat.format(dataAtual)).append("\n");
            texto.append("PRATO CHEIO TABATINGA").append("\n");
//            texto.append(separador);
        } else {
            texto.append("NÃO ENCONTRADO\n\n");
        }

        return texto.toString();

    } catch (SQLException e) {
        return texto.append("\n\nERRO AO CONSULTAR DADOS.\n").toString();
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
                senha.setIdade(this.getResultSet().getString(4));
                senha.setGenero(this.getResultSet().getString(5));
                senha.setDeficiencia(this.getResultSet().getString(6));
                senha.setTipoSenha(this.getResultSet().getString(7));
                senha.setData_refeicao(this.getResultSet().getString(9));
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

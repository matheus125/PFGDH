package com.raven.dao;

import com.raven.banco.ConexaoBD;
import com.raven.model.Funcionarios;
import com.raven.model.Usuarios;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class FuncionarioDao extends ConexaoBD {

    //METODO DE SALVAR FUNCIONARIOS E USUARIOS
    public void daoFuncionarios(Funcionarios funcionarios, Usuarios usuarios) {
        this.getConectar();
        try {

            this.executarSql("call sp_salvar_funcionarios ("
                    + "'" + funcionarios.getNome() + "',"
                    + "'" + funcionarios.getFuncao() + "',"
                    + "'" + funcionarios.getTelefone() + "',"
                    + "'" + usuarios.getLogin() + "',"
                    + "'" + usuarios.getPassword() + "',"
                    + "'" + usuarios.getPerfil() + "'"
                    + ")");
//            JOptionPane.showMessageDialog(null, "Cadastro Realizado!");
        } catch (HeadlessException erro) {
            JOptionPane.showMessageDialog(null, "Erro no Cadastro!/nErro!" + erro.getMessage());
        } finally {
            this.getfecharConexao();
        }
    }//FIM.

    //METODO UPDATE_FUNCIONARIOS E USUARIOS
    public void daoUpdateFuncionarios(Funcionarios funcionarios, Usuarios usuarios) {
        this.getConectar();

        try {

            this.executarSql("call sp_update_funcionarios ("
                    + "'" + funcionarios.getId() + "',"
                    + "'" + funcionarios.getNome() + "',"
                    + "'" + funcionarios.getFuncao() + "',"
                    + "'" + funcionarios.getTelefone() + "',"
                    + "'" + usuarios.getLogin() + "',"
                    + "'" + usuarios.getPassword() + "',"
                    + "'" + usuarios.getPerfil() + "'"
                    + ")");
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
        } catch (HeadlessException erro) {
            JOptionPane.showMessageDialog(null, "Erro em Alterar!/nErro!" + erro.getMessage());
        } finally {
            this.getfecharConexao();
        }
    }//FIM.

    // METODO DELETE_FUNCIONARIOS
    public boolean daoDeleteFuncionario(int codigo) {
        String comandoDelete = "call sp_delete_funcionario(" + codigo + ");";
        try {
            this.getConectar();
            this.executarSql(comandoDelete);
            return true;
        } catch (Exception erro) {
            System.out.println("Erro: " + erro.getMessage());
            return false;
        } finally {
            this.getfecharConexao();
        }
    }//FIM.

    //METODO PARA VERIFICAR SE JÁ EXISTE CPF CADASTRADO
    public boolean daoVerificarCPFexistente(String Login) {

        int contador = 0;
        this.getConectar();
        try {
            this.executarSql("select * from tb_user where login='" + Login + "'");
            while (this.getResultSet().next()) {
                contador = 1;
            }
            return contador == 1;
        } catch (SQLException e) {
            return false;
        }
    }//FIM.

    //METODO PARA PESQUISAR FUNCIONARIOS POR NOME
    public Funcionarios buscarFuncionarios(Funcionarios funcionarios, Usuarios usuarios) {
        this.getConectar();
        this.executarSql("SELECT * FROM tb_user u INNER JOIN tb_funcionario f on u.id_funcionario = f.id WHERE nome like '%" + funcionarios.getPesquisar() + "%'");
        try {
            this.getResultSet().first();
            funcionarios.setId(this.getResultSet().getInt("id"));
            funcionarios.setNome(this.getResultSet().getString("nome"));
            funcionarios.setFuncao(this.getResultSet().getString("funcao"));
            funcionarios.setTelefone(this.getResultSet().getString("telefone"));

            usuarios.setLogin(this.getResultSet().getString("login"));
            usuarios.setPassword(this.getResultSet().getString("password"));
            usuarios.setPerfil(this.getResultSet().getString("perfil"));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Funcionário não cadastrado!");
        }
        this.getfecharConexao();
        return funcionarios;
    }

    public String retornarUltimoLogin() {
        String nome = "";
        this.getConectar();
        try {
            this.executarSql("select * from tb_userlogs");

            while (this.getResultSet().next()) {
                nome = this.getResultSet().getString(4)
                        + " CPF: " + this.getResultSet().getString(3);
            }
            return nome;

        } catch (SQLException e) {
            return nome;
        }
    }
}

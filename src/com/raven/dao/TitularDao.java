package com.raven.dao;

import com.raven.banco.ConexaoBD;
import com.raven.model.Titular;
import com.raven.model.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class TitularDao extends ConexaoBD {

    public Statement stm;
    public ResultSet rs;
    public Connection con = null;
    private Statement statement;
    private ResultSet resultSet;

    //METODO PARA SALVAR TITULAR
    public boolean daoSalvarTitular(Titular titular, Endereco endereco) {

        String SaveTitular = "call sp_salvar_titular("
                + "'" + titular.getNome_Completo() + "',"
                + "'" + titular.getNome_Social() + "',"
                + "'" + titular.getCor_cliente() + "',"
                + "'" + titular.getNome_Mae() + "',"
                + "'" + titular.getTelefone() + "',"
                + "'" + titular.getData_Nascimento() + "',"
                + "'" + titular.getIdade_cliente() + "',"
                + "'" + titular.getGenero_cliente() + "',"
                + "'" + titular.getEstado_Civil() + "',"
                + "'" + titular.getRg() + "',"
                + "'" + titular.getCpf() + "',"
                + "'" + titular.getNis() + "',"
                + "'" + titular.getStatus_Cliente() + "',"
                + "'" + endereco.getCep() + "',"
                + "'" + endereco.getBairro() + "',"
                + "'" + endereco.getRua() + "',"
                + "'" + endereco.getNumero() + "',"
                + "'" + endereco.getReferencia() + "',"
                + "'" + endereco.getNacionalidade() + "',"
                + "'" + endereco.getNaturalidade() + "',"
                + "'" + endereco.getCidade() + "',"
                + "'" + endereco.getTempoDeMoradia_cliente() + "'"
                + ")";
        try {
            this.getConectar();
            this.executar(SaveTitular);
            return true;
        } catch (Exception erro) {
            return false;
        } finally {
            this.getfecharConexao();
        }
    }
    //FIM.

    //METODO UPDATE_FUNCIONARIOS E USUARIOS
    public boolean daoUpdateTitular(Titular titular, Endereco endereco) {

        String UpdateTitular = "call sp_update_titular ("
                + "'" + titular.getId() + "',"
                + "'" + titular.getNome_Completo() + "',"
                + "'" + titular.getNome_Social() + "',"
                + "'" + titular.getCor_cliente() + "',"
                + "'" + titular.getNome_Mae() + "',"
                + "'" + titular.getTelefone() + "',"
                + "'" + titular.getData_Nascimento() + "',"
                + "'" + titular.getIdade_cliente() + "',"
                + "'" + titular.getGenero_cliente() + "',"
                + "'" + titular.getEstado_Civil() + "',"
                + "'" + titular.getRg() + "',"
                + "'" + titular.getCpf() + "',"
                + "'" + titular.getNis() + "',"
                + "'" + titular.getStatus_Cliente() + "',"
                + "'" + endereco.getCep() + "',"
                + "'" + endereco.getBairro() + "',"
                + "'" + endereco.getRua() + "',"
                + "'" + endereco.getNumero() + "',"
                + "'" + endereco.getReferencia() + "',"
                + "'" + endereco.getNacionalidade() + "',"
                + "'" + endereco.getNaturalidade() + "',"
                + "'" + endereco.getCidade() + "',"
                + "'" + endereco.getTempoDeMoradia_cliente() + "'"
                + ")";
        try {
            this.getConectar();
            this.executarSql(UpdateTitular);
            return true;
        } catch (Exception erro) {
            return false;
        } finally {
            this.getfecharConexao();
        }
    }
    //FIM.

    //METODO PARA VERIFICAR SE CPF JÁ EXISTE CADASTRO    
    public boolean verificarClienteExistenteCPF(String cpf) {

        int contador = 0;
        this.getConectar();
        try {
            this.executarSql("select * from tb_titular where cpf='" + cpf + "'");
            while (this.getResultSet().next()) {
                contador = 1;
            }
            return contador == 1;
        } catch (SQLException e) {
            return false;
        }
    }
    //FIM.

    //METODO PARA VERIFICAR SE RG JÁ EXISTE CADASTRO
    public boolean verificarClienteExistenteRG(String rg) {

        int contador = 0;
        this.getConectar();
        try {
            this.executarSql("select * from tb_titular where rg='" + rg + "'");
            while (this.getResultSet().next()) {
                contador = 1;
            }
            return contador == 1;
        } catch (SQLException e) {
            return false;
        }
    }
    //FIM.

    //METODO PARA PESQUISAR TITULAR POR NOME OU CPF or cpf like '%
    public Titular buscarTitular(Titular titular, Endereco endereco) {
        this.getConectar();
        this.executarSql("SELECT * FROM tb_titular t INNER JOIN tb_endereco e on e.id = t.id_endereco WHERE nome_Completo like '%" + titular.getPesquisar() + "%'");
        try {

            this.getResultSet().first();
            titular.setId(this.getResultSet().getInt("id"));
            titular.setNome_Completo(this.getResultSet().getString("nome_Completo"));
            titular.setNome_Social(this.getResultSet().getString("nome_Social"));
            titular.setCor_cliente(this.getResultSet().getString("cor_cliente"));
            titular.setNome_Mae(this.getResultSet().getString("nome_Mae"));
            titular.setTelefone(this.getResultSet().getString("telefone"));
            titular.setData_Nascimento(this.getResultSet().getString("data_Nascimento"));
            titular.setIdade_cliente(this.getResultSet().getInt("idade_cliente"));
            titular.setGenero_cliente(this.getResultSet().getString("genero_cliente"));
            titular.setEstado_Civil(this.getResultSet().getString("estado_Civil"));
            titular.setRg(this.getResultSet().getString("rg"));
            titular.setCpf(this.getResultSet().getString("cpf"));
            titular.setNis(this.getResultSet().getString("nis"));
            titular.setStatus_Cliente(this.getResultSet().getString("status_Cliente"));
            endereco.setCep(this.getResultSet().getString("cep"));
            endereco.setBairro(this.getResultSet().getString("bairro"));
            endereco.setRua(this.getResultSet().getString("rua"));
            endereco.setNumero(this.getResultSet().getString("numero"));
            endereco.setReferencia(this.getResultSet().getString("referencia"));
            endereco.setNacionalidade(this.getResultSet().getString("nacionalidade"));
            endereco.setNaturalidade(this.getResultSet().getString("naturalidade"));
            endereco.setCidade(this.getResultSet().getString("cidade"));
            endereco.setTempoDeMoradia_cliente(this.getResultSet().getString("tempoDeMoradia_cliente"));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cliente não cadastrado!");
        } finally {
            this.getfecharConexao();
        }
        return titular;
    }//FIM.

    //METODO PARA PESQUISAR CLIENTES POR NOME OU CPF
    public Titular buscarClientes(Titular titular) {
        this.getConectar();
        this.executarSql("select t.id, t.nome_Completo, t.cpf, t.idade_cliente, t.genero_cliente, d.nome_dependente from tb_titular t left join tb_dependentes d on d.id_titular = t.id\n"
                + "where t.nome_Completo like '%" + titular.getPesquisar() + "%'");
        try {
            this.getResultSet().first();
            titular.setId(this.getResultSet().getInt("t.id"));
            titular.setNome_Completo(this.getResultSet().getString("t.nome_Completo"));
            titular.setCpf(this.getResultSet().getString("t.cpf"));
            titular.setRg(this.getResultSet().getString("t.rg"));
            titular.setIdade_cliente(this.getResultSet().getInt("t.idade_cliente"));
            titular.setGenero_cliente(this.getResultSet().getString("t.genero_cliente"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cliente não cadastrado!");
        } finally {
            this.getfecharConexao();
        }
        return titular;
    }//FIM.
    
}

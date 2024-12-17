package com.raven.dao;

import com.raven.banco.ConexaoBD;
import com.raven.model.Dependentes;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DependenteDao extends ConexaoBD {

    //METODO DE SALVAR DEPENDENTES
    public boolean daoDependentes(Dependentes dependentes) {

        String SalveDependentes = "call sp_salvar_dependentes ("
                + "'" + dependentes.getId_titular() + "',"
                + "'" + dependentes.getNome_Completo() + "',"
                + "'" + dependentes.getRg() + "',"
                + "'" + dependentes.getCpf() + "',"
                + "'" + dependentes.getIdade_cliente() + "',"
                + "'" + dependentes.getGenero_cliente() + "',"
                + "'" + dependentes.getDependencia_cliente() + "'"
                + ")";
        try {
            this.getConectar();
            this.executarSql(SalveDependentes);
            return true;
        } catch (Exception erro) {
            return false;
        } finally {
            this.getfecharConexao();
        }
    }
    //FIM.

    //METODO DE LISTAR_DEPENDENTES
    public ArrayList<Dependentes> daoListDependentes() {
        ArrayList<Dependentes> listDependentes = new ArrayList<>();
        Dependentes dependentes = new Dependentes();

        try {
            this.getConectar();
            this.executarSql("call sp_listar_dependentes");
            while (this.getResultSet().next()) {
                dependentes = new Dependentes();

                dependentes.setId(this.getResultSet().getInt(1));
                dependentes.setNome_Completo(this.getResultSet().getString(2));
                dependentes.setRg(this.getResultSet().getString(3));
                dependentes.setCpf(this.getResultSet().getString(4));
                dependentes.setIdade_cliente(this.getResultSet().getInt(5));
                dependentes.setGenero_cliente(this.getResultSet().getString(6));
                dependentes.setDependencia_cliente(this.getResultSet().getString(7));
                dependentes.setDt_criacao(this.getResultSet().getString(8));
                listDependentes.add(dependentes);
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            this.getfecharConexao();
        }
        return listDependentes;
    }
    //FIM.

    //METODO DE SALVAR DEPENDENTES
    public void daoDependentesUpdate(Dependentes dependentes) {
        this.getConectar();

        try {
            this.executarSql("call sp_update_dependente ("
                    + "'" + dependentes.getId() + "',"
                    + "'" + dependentes.getId_titular() + "',"
                    + "'" + dependentes.getNome_Completo() + "',"
                    + "'" + dependentes.getRg() + "',"
                    + "'" + dependentes.getCpf() + "',"
                    + "'" + dependentes.getIdade_cliente() + "',"
                    + "'" + dependentes.getGenero_cliente() + "',"
                    + "'" + dependentes.getDependencia_cliente() + "'"
                    + ")");
            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");

        } catch (HeadlessException erro) {
            JOptionPane.showMessageDialog(null, "Erro em Alterar!/nErro!" + erro.getMessage());
        } finally {
            this.getfecharConexao();
        }
    }
    //FIM.

    //METODO PARA VERIFICAR SE CPF JÁ EXISTE CADASTRO    
    public boolean verificarDependenteExistenteCPF(String cpf) {

        int contador = 0;
        this.getConectar();
        try {
            this.executarSql("select * from tb_dependentes where cpf ='" + cpf + "'");
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
    public boolean verificarDependenteExistenteRG(String rg) {

        int contador = 0;
        this.getConectar();
        try {
            this.executarSql("select * from tb_dependentes where rg ='" + rg + "'");
            while (this.getResultSet().next()) {
                contador = 1;
            }
            return contador == 1;
        } catch (SQLException e) {
            return false;
        }
    }
    //FIM.

    //METODO PARA VERIFICAR SE DEPENDENTE JÁ EXISTE DEPENDENTE CADASTRADO    
    public boolean verificarDependenteExistenteTitular(String cpf) {

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

    //select count(id_titular) from tb_dependentes where id_titular = 31;
    public boolean chegarDependentesCadastrados(int id_titular) {

        int contador = 0;
        this.getConectar();

        try {
            this.executarSql("select count(id_titular) from tb_dependentes where id_titular ='" + id_titular + "'");
            while (this.getResultSet().next()) {
                contador = 1;
            }
            return contador == 1;
        } catch (SQLException e) {
            return false;
        }
    }
}

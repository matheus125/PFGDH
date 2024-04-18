package com.raven.dao;

import com.raven.banco.ConexaoBD;
import com.raven.model.Refeicoes;
import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class RefeicoesDao extends ConexaoBD {

    public boolean daoSalvarSenha(Refeicoes refeicoes) {

        String SalvarSenha = "call sp_salvar_refeicoes_vendidas ("
                + "'" + refeicoes.getTotal_servido() + "',"
                + "'" + refeicoes.getData() + "'"
                + ")";
        try {
            this.getConectar();
            this.executarSql(SalvarSenha);

            return true;
        } catch (HeadlessException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar Refeição!" + erro.getMessage());
            return false;
        } finally {
            this.getfecharConexao();
        }
    }

    public String retornarTotalServido() {

        String total = "";
        this.getConectar();
        try {

            this.executarSql("Select * from tb_refeicoes_vendidas");

            while (this.getResultSet().next()) {
                total = this.getResultSet().getInt(2) + "";
            }

            return total;

        } catch (SQLException e) {
            return total;
        }
    }
}

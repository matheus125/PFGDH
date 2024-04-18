package com.raven.dao;

import com.raven.banco.ConexaoBD;
import java.sql.SQLException;

public class ClientesDao extends ConexaoBD {

    public boolean inserirClientesCadastrados() {

        this.getConectar();
        try {
            this.executarUpdateSQL("insert into tb_resumodia values(NULL)");
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    //VERICAR TOTAL DE CLIENTES CADASTRADOS NO DIA.
    public int checarClientesCadastrados() {
        int contador = 0;

        this.getConectar();
        try {

            this.executarSql("select * from tb_resumodia order by id desc limit 1");

            while (this.getResultSet().next()) {
                contador = this.getResultSet().getInt(1);
            }

            return contador;

        } catch (SQLException e) {
            return contador;
        }
    }//FIM.

    //METODO PARA LIMPAR TABELA "tb_resumodia", AO FECHAR O DIA.
    public boolean limparTabelaResumiDia() {

        try {
            this.getConectar();
            this.executarSql("call sp_limpar_resumo_dia");
            return true;
        } catch (Exception e) {
            return false;
        }
    }//FIM.
}

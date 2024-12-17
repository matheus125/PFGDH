package com.raven.dao;

import com.raven.banco.ConexaoBD;
import com.raven.model.Count_Senhas;

public class Count_SenhasDao extends ConexaoBD {

    public boolean Salvar_senhas(Count_Senhas count_Senhas) {
        String Update = "call sp_salvar ("
                + "'" + count_Senhas.getId() + "',"
                + "'" + count_Senhas.getNum_senhas() + "'"
                + ")";
        try {
            this.getConectar();
            this.executarSql(Update);
            return true;
        } catch (Exception erro) {
            return false;
        } finally {
            this.getfecharConexao();
        }
    }
}

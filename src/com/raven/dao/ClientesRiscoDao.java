package com.raven.dao;

import com.raven.banco.ConexaoBD;
import com.raven.model.ClientesRisco;
import java.sql.SQLException;

public class ClientesRiscoDao extends ConexaoBD {

    public boolean daoSalvarClientesRisco(ClientesRisco clientesRisco) {
        String SavarClienteRisco = "call sp_salvar_cliente_situacao_rua("
                + "'" + clientesRisco.getNome_Completo() + "',"
                + "'" + clientesRisco.getIdade_cliente() + "',"
                + "'" + clientesRisco.getGenero_cliente()+ "',"
                + "'" + clientesRisco.getStatus_Cliente()+ "'"
                + ")";
        try {
            this.getConectar();
            this.executarSql(SavarClienteRisco);
            return true;
        } catch (Exception erro) {
            return false;
        } finally {
            this.getfecharConexao();
        }
    }

}

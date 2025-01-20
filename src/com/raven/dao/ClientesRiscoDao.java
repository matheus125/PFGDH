package com.raven.dao;

import com.raven.banco.ConexaoBD;
import com.raven.model.ClientesRisco;
import java.sql.PreparedStatement;

public class ClientesRiscoDao extends ConexaoBD {

    public boolean daoSalvarClientesRisco(ClientesRisco clientesRisco) {
        String query = "CALL sp_salvar_cliente_situacao_rua(?, ?, ?, ?)";
        try {
            this.getConectar();
            PreparedStatement statement = this.con.prepareStatement(query);
            statement.setString(1, clientesRisco.getNome_Completo());
            statement.setInt(2, clientesRisco.getIdade_cliente());
            statement.setString(3, clientesRisco.getGenero_cliente());
            statement.setString(4, clientesRisco.getStatus_Cliente());

            statement.execute();
            return true;
        } catch (Exception erro) {
            // Registro do erro para facilitar o debug
            erro.printStackTrace();
            return false;
        } finally {
            this.getfecharConexao();
        }
    }

}

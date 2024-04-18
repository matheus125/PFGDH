package com.raven.dao;

import com.raven.banco.ConexaoBD;
import com.raven.model.SocioEconomicoSaude;

public class SocioEconomicoSaudeDao extends ConexaoBD {

    //METODO DE SALVAR SocioEconomico
    public boolean daoSocioEconomicoSaude(SocioEconomicoSaude socioEconomicoSaude) {

        String SalvesocioEconomicoSaude = "call sp_salvar_socio_economico_saude ("
                + "'" + socioEconomicoSaude.getId_titular()+ "',"
                + "'" + socioEconomicoSaude.getDoenca() + "',"
                + "'" + socioEconomicoSaude.getOutras_Doenças() + "',"
                + "'" + socioEconomicoSaude.getDeficiencia() + "',"
                + "'" + socioEconomicoSaude.getJustificar_deficiencia() + "',"
                + "'" + socioEconomicoSaude.getLaudo() + "',"
                + "'" + socioEconomicoSaude.getObservacao() + "'"
                + ")";
        try {
            this.getConectar();
            this.executarSql(SalvesocioEconomicoSaude);
            return true;
        } catch (Exception erro) {
            return false;
        } finally {
            this.getfecharConexao();
        }
    }
    //FIM.
}

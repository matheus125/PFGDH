package com.raven.dao;

import com.raven.model.SocioEconomico;
import com.raven.banco.ConexaoBD;

public class SocioEconomicoDao extends ConexaoBD {
    
    //METODO DE SALVAR SocioEconomico
    public boolean daoSocioEconomico(SocioEconomico socioEconomico) {

        String SalveSocioEconomico = "call sp_salvar_socio_economico ("
                + "'" + socioEconomico.getId_titular()+ "',"
                + "'" + socioEconomico.getEscolariedade()+ "',"
                + "'" + socioEconomico.getRenda_mensal_familia() + "',"
                + "'" + socioEconomico.getProgramas_sociais() + "',"
                + "'" + socioEconomico.getComposicao_familiar() + "',"
                + "'" + socioEconomico.getMoradia() + "',"
                + "'" + socioEconomico.getEstrutura_Moradia()+ "',"
                + "'" + socioEconomico.getQtdPessoas_Trabalhando() + "',"
                + "'" + socioEconomico.getEmprego() + "',"
                + "'" + socioEconomico.getProfissao_Responsavel() + "',"
                + "'" + socioEconomico.getAB_Agua() + "',"
                + "'" + socioEconomico.getSN_basico()+ "',"
                + "'" + socioEconomico.getEnergia_eletrica() + "',"
                + "'" + socioEconomico.getLixo_Domiciliar() + "',"
                + "'" + socioEconomico.getFrequenta_Centro() + "'"
                + ")";
        try {
            this.getConectar();
            this.executarSql(SalveSocioEconomico);
            return true;
        } catch (Exception erro) {
            return false;
        } finally {
            this.getfecharConexao();
        }
    }
    //FIM.
}

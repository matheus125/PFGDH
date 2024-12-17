package com.raven.controller;

import com.raven.dao.SocioEconomicoSaudeDao;
import com.raven.model.SocioEconomicoSaude;

public class ControllerSocioEconomicoSaude {

    SocioEconomicoSaudeDao socioEconomicoSaudeDao = new SocioEconomicoSaudeDao();

    // SALVAR FICHA SOCIO_ECONOMICO_SAUDE
    public boolean controlSaveSocioEconomico(SocioEconomicoSaude socioEconomicoSaude) {
        return this.socioEconomicoSaudeDao.daoSocioEconomicoSaude(socioEconomicoSaude);
    }
}

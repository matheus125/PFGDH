package com.raven.controller;

import com.raven.dao.SocioEconomicoDao;
import com.raven.model.SocioEconomico;

public class ControllerSocioEconomico {
    
    SocioEconomicoDao socioEconomicoDao = new SocioEconomicoDao();
    
    // SALVAR FICHA SOCIO ECONOMICO
    public boolean controlSaveSocioEconomico (SocioEconomico socioEconomico){
        return this.socioEconomicoDao.daoSocioEconomico(socioEconomico);
    }
}

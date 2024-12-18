package com.raven.controller;

import com.raven.dao.ClientesDao;
import com.raven.dao.ClientesRiscoDao;
import com.raven.model.ClientesRisco;


public class ControllerClienteRisco {
    
    ClientesRiscoDao clientesRiscoDao = new ClientesRiscoDao();
    
    //Salvar
    public boolean controlSaveClienteRisco(ClientesRisco clientesRisco) {
     return this.clientesRiscoDao.daoSalvarClientesRisco(clientesRisco);
    }
}

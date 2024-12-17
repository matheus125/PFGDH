package com.raven.controller;

import com.raven.dao.ClientesDao;

public class ControllerClientes {

    ClientesDao clientesDao = new ClientesDao();

    public boolean controlLimparResumodia() {
        return this.clientesDao.limparTabelaResumiDia();
    }

}

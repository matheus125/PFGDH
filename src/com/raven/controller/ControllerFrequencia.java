package com.raven.controller;

import com.raven.dao.FrequenciaDAO;

public class ControllerFrequencia {

    FrequenciaDAO frequenciaDAO = new FrequenciaDAO();

    public boolean controllimparFrequencia() {
        return this.frequenciaDAO.limparTabelaFrequenciaDiaria();
    }
}

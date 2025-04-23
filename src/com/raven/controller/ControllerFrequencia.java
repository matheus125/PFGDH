package com.raven.controller;

import com.raven.dao.FrequenciaDAO;

public class ControllerFrequencia {

    FrequenciaDAO frequenciaDAO = new FrequenciaDAO();

    public boolean controllimparFrequencia() {
<<<<<<< HEAD
        return this.frequenciaDAO.limparTabelaFrequenciaDiaria();
=======
        return this.frequenciaDAO.limparTabelaFrequencia_diaria();
>>>>>>> eb9b89a (PFGDH_2.1)
    }
}

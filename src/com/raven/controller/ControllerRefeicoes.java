package com.raven.controller;

import com.raven.dao.RefeicoesDao;
import com.raven.model.Refeicoes;

public class ControllerRefeicoes {

    RefeicoesDao refeiçõesDao = new RefeicoesDao();

    public boolean controlSaveRefeicoes(Refeicoes refeicoes) {
        return this.refeiçõesDao.daoSalvarSenha(refeicoes);
    }
}

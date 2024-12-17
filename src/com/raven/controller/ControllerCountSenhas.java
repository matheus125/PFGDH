package com.raven.controller;

import com.raven.dao.Count_SenhasDao;
import com.raven.model.Count_Senhas;

public class ControllerCountSenhas {

    Count_SenhasDao count_SenhasDao = new Count_SenhasDao();

    public boolean controlSalvar(Count_Senhas count_Senhas) {
        return this.count_SenhasDao.Salvar_senhas(count_Senhas);
    }
}

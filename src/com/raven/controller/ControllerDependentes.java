package com.raven.controller;

import com.raven.dao.DependenteDao;
import com.raven.model.Dependentes;
import java.util.ArrayList;

public class ControllerDependentes {

    DependenteDao dependentesDao = new DependenteDao();

    //Salvar DEPENDENTES
    public boolean controlSaveDependentes(Dependentes dependentes) {
        return this.dependentesDao.daoDependentes(dependentes);
    }

    //UPDATE DEPENDENTES
    public void controlUpdateDependente(Dependentes dependentes) {
        this.dependentesDao.daoDependentesUpdate(dependentes);
    }

    //listar Dependentes
    public ArrayList<Dependentes> returnListDependentesController() {
        return this.dependentesDao.daoListDependentes();
    }

    //Verificação de CPF cadastrado 
    public boolean controlVerificarCPFCadastrado(String cpf) {
        return this.dependentesDao.verificarDependenteExistenteCPF(cpf);
    }

    //Verificação de RG cadastrado
    public boolean controlVerificarRGCadastrado(String rg) {
        return this.dependentesDao.verificarDependenteExistenteRG(rg);
    }

    //Verificação de DependentesCadastrados
    public boolean controlVerificarDependenteCadastrado(int id_titular) {
        return this.dependentesDao.chegarDependentesCadastrados(id_titular);
    }
}

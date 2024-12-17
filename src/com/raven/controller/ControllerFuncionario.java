package com.raven.controller;

import com.raven.dao.FuncionarioDao;
import com.raven.model.Funcionarios;
import com.raven.model.Usuarios;
import java.util.ArrayList;

public class ControllerFuncionario {

    FuncionarioDao funcionariosDao = new FuncionarioDao();

    //salvar funcionarios
    public void controlSaveFuncionarios(Funcionarios funcionarios, Usuarios usuarios) {
        this.funcionariosDao.daoFuncionarios(funcionarios, usuarios);
    }

    //update funcionarios
    public void controlUpdateFuncionarios(Funcionarios funcionarios, Usuarios usuarios) {
        this.funcionariosDao.daoUpdateFuncionarios(funcionarios, usuarios);
    }
    
    //delete funcionarios
    public boolean controlDeleteFuncionarios(int codigo) {
        return this.funcionariosDao.daoDeleteFuncionario(codigo);
    }

    //verifica login existente cadastrador no banco
    public boolean controlGetFuncionario(String Login) {
        return this.funcionariosDao.daoVerificarCPFexistente(Login);
    }
}

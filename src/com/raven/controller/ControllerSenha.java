package com.raven.controller;

import com.raven.dao.SenhaDao;
import com.raven.model.Senha;
import java.util.ArrayList;

public class ControllerSenha {

    SenhaDao senhaDao = new SenhaDao();

    //Salvar Senhas
    public boolean controlSaveSenhas(Senha senha) {
        return this.senhaDao.daoSalvarSenha(senha);
    }

    //Salvar Senhas genericas
    public boolean controlSaveSenhasGenericas(Senha senha) {
        return this.senhaDao.daoSalvarSenhaGenerico(senha);
    }

    //Verificar Cliente com senha
    public boolean controlVerificarSenhaCliente(String nome) {
        return this.senhaDao.checarSenhaCliente(nome);
    }

    //Retorna a ultima senha inserida no banco
    public int controlRetornarUltimaSenha() {
        return this.senhaDao.retornarUltimaSenha();
    }

    //listar senhas na tela expediente
    public ArrayList<Senha> returnListSenhaController() {
        return this.senhaDao.daoListSenhas();
    }

    //VERIFICAÇÃO DE SENHA DE USUARIO PARA FECHAR O SISTEMA
    public int controlChecarSenha(String senha) {
        return this.senhaDao.chegarSenha(senha);
    }

    public boolean controlLimparSenhas() {
        return this.senhaDao.limparTabelaSenhas();
    }
}

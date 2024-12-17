package com.raven.controller;

import com.raven.dao.TitularDao;
import com.raven.model.Titular;
import com.raven.model.Endereco;
import java.util.ArrayList;

public class ControllerTitular {

    TitularDao titularDao = new TitularDao();

    //Salvar Clientes
    public boolean controlSaveClientes(Titular titular, Endereco endereco) {
        return this.titularDao.daoSalvarTitular(titular, endereco);
    }

    //update Clientes
    public boolean controlUpdateClientes(Titular titular, Endereco endereco) {
        return this.titularDao.daoUpdateTitular(titular, endereco);
    }

    //Verificação de CPF cadastrado 
    public boolean controlVerificarCPFCadastrado(String cpf) {
        return this.titularDao.verificarClienteExistenteCPF(cpf);
    }
    
    //Verificação de RG cadastrado
    public boolean controlVerificarRGCadastrado(String rg) {
        return this.titularDao.verificarClienteExistenteRG(rg);
    }
    
    //METODO PARA PESQUISAR TITULAR POR NOME OU CPF
//    public Titular controlPesquisarTitular(String pesquisa){
//        return this.titularDao.buscarTitular(pesquisa);
//    }
}

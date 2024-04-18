package com.raven.controller;

import com.raven.dao.RelatoriosDao;
import com.raven.model.Relatorios;
import java.io.FileNotFoundException;

public class ControllerRelatorios {

    RelatoriosDao relatoriosDao = new RelatoriosDao();

    public Relatorios controlLerOBL(Relatorios relatorio) {
        return this.relatoriosDao.lerParaOBl(relatorio);
    }

    public boolean controlSaveRelatorios(Relatorios relatorio) {
        return this.relatoriosDao.inserirNosRelatorios(relatorio);
    }

    public void controlGravarRelatorio(String texto) throws FileNotFoundException {
        this.relatoriosDao.escreverNoRELATORIOPDF(texto);
    }

    public String controlLerRelatorios(String data, String ocorrencias) {
        return this.relatoriosDao.lerRelatorios(data, ocorrencias);
    }

}

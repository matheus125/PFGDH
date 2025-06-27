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

//    public void controlLerRelatorios(String data, String ocorrencias) {
//        this.relatoriosDao.gerarRelatorioPDF(data, ocorrencias);
//    }
    public void gerarRelatorio(String data, String ocorrencias) {
        String texto = relatoriosDao.gerarTextoRelatorio(data, ocorrencias);
        relatoriosDao.escreverNoRELATORIOPDF(texto);
    }

}

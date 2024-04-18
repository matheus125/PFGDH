package com.raven.model;

public class SocioEconomicoSaude {
    private int id;
    private int id_titular;
    private String doenca;
    private String outras_Doenças;
    private String deficiencia;
    private String justificar_deficiencia;
    private String laudo;
    private String observacao;

    public SocioEconomicoSaude() {
    }

    public SocioEconomicoSaude(int id, int id_titular, String doenca, String outras_Doenças, String deficiencia, String justificar_deficiencia, String laudo, String observacao) {
        this.id = id;
        this.id_titular = id_titular;
        this.doenca = doenca;
        this.outras_Doenças = outras_Doenças;
        this.deficiencia = deficiencia;
        this.justificar_deficiencia = justificar_deficiencia;
        this.laudo = laudo;
        this.observacao = observacao;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the id_cliente
     */
    public int getId_titular() {
        return id_titular;
    }

    /**
     * @param id_titular the id_titular to set
     */
    public void setId_titular(int id_titular) {
        this.id_titular = id_titular;
    }

    /**
     * @return the doenca
     */
    public String getDoenca() {
        return doenca;
    }

    /**
     * @param doenca the doenca to set
     */
    public void setDoenca(String doenca) {
        this.doenca = doenca;
    }

    /**
     * @return the outras_Doenças
     */
    public String getOutras_Doenças() {
        return outras_Doenças;
    }

    /**
     * @param outras_Doenças the outras_Doenças to set
     */
    public void setOutras_Doenças(String outras_Doenças) {
        this.outras_Doenças = outras_Doenças;
    }

    /**
     * @return the deficiencia
     */
    public String getDeficiencia() {
        return deficiencia;
    }

    /**
     * @param deficiencia the deficiencia to set
     */
    public void setDeficiencia(String deficiencia) {
        this.deficiencia = deficiencia;
    }

    /**
     * @return the justificar_deficiencia
     */
    public String getJustificar_deficiencia() {
        return justificar_deficiencia;
    }

    /**
     * @param justificar_deficiencia the justificar_deficiencia to set
     */
    public void setJustificar_deficiencia(String justificar_deficiencia) {
        this.justificar_deficiencia = justificar_deficiencia;
    }

    /**
     * @return the laudo
     */
    public String getLaudo() {
        return laudo;
    }

    /**
     * @param laudo the laudo to set
     */
    public void setLaudo(String laudo) {
        this.laudo = laudo;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
}

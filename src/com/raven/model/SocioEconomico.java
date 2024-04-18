package com.raven.model;

public class SocioEconomico {

    private int id;
    private int id_titular;
    private String escolariedade;
    private String renda_mensal_familia;
    private String programas_sociais;
    private String composicao_familiar;
    private String moradia;
    private String estrutura_Moradia;
    private int qtdPessoas_Trabalhando;
    private String emprego;
    private String profissao_Responsavel;
    private String AB_Agua;
    private String SN_basico;
    private String Energia_eletrica;
    private String Lixo_Domiciliar;
    private String frequenta_Centro;

    public SocioEconomico() {
    }

    public SocioEconomico(int id, int id_titular, String escolariedade, String renda_mensal_familia, String programas_sociais, String composicao_familiar, String moradia, String estrutura_Moradia, int qtdPessoas_Trabalhando, String emprego, String profissao_Responsavel, String AB_Agua, String SN_basico, String Energia_eletrica, String Lixo_Domiciliar, String frequenta_Centro) {
        this.id = id;
        this.id_titular = id_titular;
        this.escolariedade = escolariedade;
        this.renda_mensal_familia = renda_mensal_familia;
        this.programas_sociais = programas_sociais;
        this.composicao_familiar = composicao_familiar;
        this.moradia = moradia;
        this.estrutura_Moradia = estrutura_Moradia;
        this.qtdPessoas_Trabalhando = qtdPessoas_Trabalhando;
        this.emprego = emprego;
        this.profissao_Responsavel = profissao_Responsavel;
        this.AB_Agua = AB_Agua;
        this.SN_basico = SN_basico;
        this.Energia_eletrica = Energia_eletrica;
        this.Lixo_Domiciliar = Lixo_Domiciliar;
        this.frequenta_Centro = frequenta_Centro;
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
     * @return the id_titular
     */
    public int getId_titular() {
        return id_titular;
    }

    /**
     * @param id_titular the id_titular to set
     */
    public void setIId_titular(int id_titular) {
        this.id_titular = id_titular;
    }

    /**
     * @return the escolariedade
     */
    public String getEscolariedade() {
        return escolariedade;
    }

    /**
     * @param escolariedade the escolariedade to set
     */
    public void setEscolariedade(String escolariedade) {
        this.escolariedade = escolariedade;
    }

    /**
     * @return the renda_mensal_familia
     */
    public String getRenda_mensal_familia() {
        return renda_mensal_familia;
    }

    /**
     * @param renda_mensal_familia the renda_mensal_familia to set
     */
    public void setRenda_mensal_familia(String renda_mensal_familia) {
        this.renda_mensal_familia = renda_mensal_familia;
    }

    /**
     * @return the programas_sociais
     */
    public String getProgramas_sociais() {
        return programas_sociais;
    }

    /**
     * @param programas_sociais the programas_sociais to set
     */
    public void setProgramas_sociais(String programas_sociais) {
        this.programas_sociais = programas_sociais;
    }

    /**
     * @return the composicao_familiar
     */
    public String getComposicao_familiar() {
        return composicao_familiar;
    }

    /**
     * @param composicao_familiar the composicao_familiar to set
     */
    public void setComposicao_familiar(String composicao_familiar) {
        this.composicao_familiar = composicao_familiar;
    }

    /**
     * @return the moradia
     */
    public String getMoradia() {
        return moradia;
    }

    /**
     * @param moradia the moradia to set
     */
    public void setMoradia(String moradia) {
        this.moradia = moradia;
    }

    /**
     * @return the estrutura_Moradia
     */
    public String getEstrutura_Moradia() {
        return estrutura_Moradia;
    }

    /**
     * @param estrutura_Moradia the estrutura_Moradia to set
     */
    public void setEstrutura_Moradia(String estrutura_Moradia) {
        this.estrutura_Moradia = estrutura_Moradia;
    }

    /**
     * @return the qtdPessoas_Trabalhando
     */
    public int getQtdPessoas_Trabalhando() {
        return qtdPessoas_Trabalhando;
    }

    /**
     * @param qtdPessoas_Trabalhando the qtdPessoas_Trabalhando to set
     */
    public void setQtdPessoas_Trabalhando(int qtdPessoas_Trabalhando) {
        this.qtdPessoas_Trabalhando = qtdPessoas_Trabalhando;
    }

    /**
     * @return the emprego
     */
    public String getEmprego() {
        return emprego;
    }

    /**
     * @param emprego the emprego to set
     */
    public void setEmprego(String emprego) {
        this.emprego = emprego;
    }

    /**
     * @return the profissao_Responsavel
     */
    public String getProfissao_Responsavel() {
        return profissao_Responsavel;
    }

    /**
     * @param profissao_Responsavel the profissao_Responsavel to set
     */
    public void setProfissao_Responsavel(String profissao_Responsavel) {
        this.profissao_Responsavel = profissao_Responsavel;
    }

    /**
     * @return the AB_Agua
     */
    public String getAB_Agua() {
        return AB_Agua;
    }

    /**
     * @param AB_Agua the AB_Agua to set
     */
    public void setAB_Agua(String AB_Agua) {
        this.AB_Agua = AB_Agua;
    }

    /**
     * @return the SN_basico
     */
    public String getSN_basico() {
        return SN_basico;
    }

    /**
     * @param SN_basico the SN_basico to set
     */
    public void setSN_basico(String SN_basico) {
        this.SN_basico = SN_basico;
    }

    /**
     * @return the Energia_eletrica
     */
    public String getEnergia_eletrica() {
        return Energia_eletrica;
    }

    /**
     * @param Energia_eletrica the Energia_eletrica to set
     */
    public void setEnergia_eletrica(String Energia_eletrica) {
        this.Energia_eletrica = Energia_eletrica;
    }

    /**
     * @return the Lixo_Domiciliar
     */
    public String getLixo_Domiciliar() {
        return Lixo_Domiciliar;
    }

    /**
     * @param Lixo_Domiciliar the Lixo_Domiciliar to set
     */
    public void setLixo_Domiciliar(String Lixo_Domiciliar) {
        this.Lixo_Domiciliar = Lixo_Domiciliar;
    }

    /**
     * @return the frequenta_Centro
     */
    public String getFrequenta_Centro() {
        return frequenta_Centro;
    }

    /**
     * @param frequenta_Centro the frequenta_Centro to set
     */
    public void setFrequenta_Centro(String frequenta_Centro) {
        this.frequenta_Centro = frequenta_Centro;
    }

}

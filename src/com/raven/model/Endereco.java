package com.raven.model;

public class Endereco {

    private int id;
    private String cep;
    private String bairro;
    private String rua;
    private String numero;
    private String referencia;
    private String nacionalidade;
    private String naturalidade;
    private String cidade;
    private String tempoDeMoradia_cliente;

    public Endereco() {
    }

    public Endereco(int id, String cep, String bairro, String rua, String numero, String referencia, String nacionalidade, String naturalidade, String cidade, String tempoDeMoradia_cliente) {
        this.id = id;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.referencia = referencia;
        this.nacionalidade = nacionalidade;
        this.naturalidade = naturalidade;
        this.cidade = cidade;
        this.tempoDeMoradia_cliente = tempoDeMoradia_cliente;
    }

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
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the rua
     */
    public String getRua() {
        return rua;
    }

    /**
     * @param rua the rua to set
     */
    public void setRua(String rua) {
        this.rua = rua;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * @return the nacionalidade
     */
    public String getNacionalidade() {
        return nacionalidade;
    }

    /**
     * @param nacionalidade the nacionalidade to set
     */
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    /**
     * @return the naturalidade
     */
    public String getNaturalidade() {
        return naturalidade;
    }

    /**
     * @param naturalidade the naturalidade to set
     */
    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    /**
     * @return the naturalidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the tempoDeMoradia_cliente
     */
    public String getTempoDeMoradia_cliente() {
        return tempoDeMoradia_cliente;
    }

    /**
     * @param tempoDeMoradia_cliente the tempoDeMoradia_cliente to set
     */
    public void setTempoDeMoradia_cliente(String tempoDeMoradia_cliente) {
        this.tempoDeMoradia_cliente = tempoDeMoradia_cliente;
    }

}

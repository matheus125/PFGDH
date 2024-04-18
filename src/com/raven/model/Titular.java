package com.raven.model;

public class Titular {

    private int id;
    private Endereco endereco;
    private String nome_Completo;
    private String nome_Social;
    private String cor_cliente;
    private String nome_Mae;
    private String telefone;
    private String data_Nascimento;
    private int idade_cliente;
    private String genero_cliente;
    private String estado_Civil;
    private String rg;
    private String cpf;
    private String nis;
    private String status_Cliente;
    private String pesquisar;

    public Titular() {
    }

    public Titular(int id, Endereco endereco, String nome_Completo, String nome_Social, String cor_cliente, String nome_Mae, String telefone, String data_Nascimento, int idade_cliente, String genero_cliente, String estado_Civil, String rg, String cpf, String nis, String status_Cliente, String pesquisar) {
        this.id = id;
        this.endereco = endereco;
        this.nome_Completo = nome_Completo;
        this.nome_Social = nome_Social;
        this.cor_cliente = cor_cliente;
        this.nome_Mae = nome_Mae;
        this.telefone = telefone;
        this.data_Nascimento = data_Nascimento;
        this.idade_cliente = idade_cliente;
        this.genero_cliente = genero_cliente;
        this.estado_Civil = estado_Civil;
        this.rg = rg;
        this.cpf = cpf;
        this.nis = nis;
        this.status_Cliente = status_Cliente;
        this.pesquisar = pesquisar;
    }

   

    /**
     * @return the idade_cliente
     */
    public int getIdade_cliente() {
        return idade_cliente;
    }

    /**
     * @param idade_cliente the idade_cliente to set
     */
    public void setIdade_cliente(int idade_cliente) {
        this.idade_cliente = idade_cliente;
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
     * @return the endereco
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the nome_Completo
     */
    public String getNome_Completo() {
        return nome_Completo;
    }

    /**
     * @param nome_Completo the nome_Completo to set
     */
    public void setNome_Completo(String nome_Completo) {
        this.nome_Completo = nome_Completo;
    }

    /**
     * @return the nome_Social
     */
    public String getNome_Social() {
        return nome_Social;
    }

    /**
     * @param nome_Social the nome_Social to set
     */
    public void setNome_Social(String nome_Social) {
        this.nome_Social = nome_Social;
    }

    /**
     * @return the cor_cliente
     */
    public String getCor_cliente() {
        return cor_cliente;
    }

    /**
     * @param cor_cliente the cor_cliente to set
     */
    public void setCor_cliente(String cor_cliente) {
        this.cor_cliente = cor_cliente;
    }

    /**
     * @return the nome_Mae
     */
    public String getNome_Mae() {
        return nome_Mae;
    }

    /**
     * @param nome_Mae the nome_Mae to set
     */
    public void setNome_Mae(String nome_Mae) {
        this.nome_Mae = nome_Mae;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the data_Nascimento
     */
    public String getData_Nascimento() {
        return data_Nascimento;
    }

    /**
     * @param data_Nascimento the data_Nascimento to set
     */
    public void setData_Nascimento(String data_Nascimento) {
        this.data_Nascimento = data_Nascimento;
    }

    /**
     * @return the genero_cliente
     */
    public String getGenero_cliente() {
        return genero_cliente;
    }

    /**
     * @param genero_cliente the genero_cliente to set
     */
    public void setGenero_cliente(String genero_cliente) {
        this.genero_cliente = genero_cliente;
    }

    /**
     * @return the estado_Civil
     */
    public String getEstado_Civil() {
        return estado_Civil;
    }

    /**
     * @param estado_Civil the estado_Civil to set
     */
    public void setEstado_Civil(String estado_Civil) {
        this.estado_Civil = estado_Civil;
    }

    /**
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * @param rg the rg to set
     */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the nis
     */
    public String getNis() {
        return nis;
    }

    /**
     * @param nis the cpf to set
     */
    public void setNis(String nis) {
        this.nis = nis;
    }
    
    /**
     * @return the status_Cliente
     */
    public String getStatus_Cliente() {
        return status_Cliente;
    }

    /**
     * @param status_Cliente the status_Cliente to set
     */
    public void setStatus_Cliente(String status_Cliente) {
        this.status_Cliente = status_Cliente;
    }
    
     /**
     * @return the pesquisar
     */
    public String getPesquisar() {
        return pesquisar;
    }

    /**
     * @param pesquisar the pesquisar to set
     */
    public void setPesquisar(String pesquisar) {
        this.pesquisar = pesquisar;
    }
}

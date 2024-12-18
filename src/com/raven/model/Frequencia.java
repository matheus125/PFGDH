package com.raven.model;

import java.util.Date;

public class Frequencia {

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    private int clienteId;
    private String nome;
    private Date data;
    private String status;  // Pode ser "presente", "ausente" ou outro status

    public Frequencia() {
    }

    public Frequencia(int clienteId, String nome, Date data, String status) {
        this.clienteId = clienteId;
        this.nome = nome;
        this.data = data;
        this.status = status;
    }
    
    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Frequencia [clienteId=" + clienteId + ", data=" + data + ", status=" + status + "]";
    }
}

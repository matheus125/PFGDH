package com.raven.model;

public class Refeicoes {

    private int id;
    private int total_servido;
    private String data;

    public Refeicoes() {
    }

    public Refeicoes(int id, int total_servido, String data) {
        this.id = id;
        this.total_servido = total_servido;
        this.data = data;
    }

    /**
     * @return the total_servido
     */
    public int getTotal_servido() {
        return total_servido;
    }

    /**
     * @param total_servido the total_servido to set
     */
    public void setTotal_servido(int total_servido) {
        this.total_servido = total_servido;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
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

}

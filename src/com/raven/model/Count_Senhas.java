package com.raven.model;

public class Count_Senhas {

    private int id;
    private int Num_senhas;

    public Count_Senhas() {
    }

    public Count_Senhas(int id, int Num_senhas) {
        this.id = id;
        this.Num_senhas = Num_senhas;
    }

    /**
     * @return the Num_senhas
     */
    public int getNum_senhas() {
        return Num_senhas;
    }

    /**
     * @param Num_senhas the Num_senhas to set
     */
    public void setNum_senhas(int Num_senhas) {
        this.Num_senhas = Num_senhas;
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

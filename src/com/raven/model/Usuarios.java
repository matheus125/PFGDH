package com.raven.model;

public class Usuarios {
    
    private int id;
    private Funcionarios funcionarios;
    private String login;
    private String password;
    private String perfil;

    public Usuarios() {
    }

    public Usuarios(int id, Funcionarios funcionarios, String login, String password, String perfil) {
        this.id = id;
        this.funcionarios = funcionarios;
        this.login = login;
        this.password = password;
        this.perfil = perfil;
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
     * @return the funcionarios
     */
    public Funcionarios getFuncionarios() {
        return funcionarios;
    }

    /**
     * @param funcionarios the funcionarios to set
     */
    public void setFuncionarios(Funcionarios funcionarios) {
        this.funcionarios = funcionarios;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the perfil
     */
    public String getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    
    
}

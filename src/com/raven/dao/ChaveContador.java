/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.dao;

/**
 *
 * @author gsan_
 */
import java.util.Objects;

public class ChaveContador {
    private FaixaEtaria faixa;
    private Genero genero;
    boolean deficiente;

    public ChaveContador(FaixaEtaria faixa, Genero genero, boolean deficiente) {
        this.faixa = faixa;
        this.genero = genero;
        this.deficiente = deficiente;
    }

    // Getters (se precisar)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChaveContador)) return false;
        ChaveContador that = (ChaveContador) o;
        return deficiente == that.deficiente &&
                faixa == that.faixa &&
                genero == that.genero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(faixa, genero, deficiente);
    }
}
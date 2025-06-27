/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.dao;

/**
 *
 * @author gsan_
 */
public class ArmazenarContagem {

    public class Contador {

        int total = 0;
        int deficientes = 0;
        int situacaoRua = 0;

        public void incrementaTotal() {
            total++;
        }

        public void incrementaDeficiente() {
            deficientes++;
        }

        public void incrementaRua() {
            situacaoRua++;
        }

        // Getters opcionais
    }
}

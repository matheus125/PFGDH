/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.dao;

/**
 *
 * @author gsan_
 */
class PessoaEstatistica {

    int total = 0, deficientes = 0, situacaoRua = 0;

    void contar(boolean deficiente, boolean rua) {
        total++;
        if (deficiente) {
            deficientes++;
        }
        if (rua) {
            situacaoRua++;
        }
    }
}

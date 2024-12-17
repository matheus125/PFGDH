package com.main;

import com.raven.banco.ConexaoBD;
import javax.swing.UnsupportedLookAndFeelException;
import view.com.login.TelaLogin;

public class Principal {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     * @throws javax.swing.UnsupportedLookAndFeelException
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        // TODO code application logic here
        ConexaoBD con = new ConexaoBD();
        con.getConectar();

        ConexaoBD conex = con;

        if (conex == null) {
           
        } else {
            TelaLogin telalogin = new TelaLogin();
            telalogin.setVisible(true);
        }

        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }

    }
}

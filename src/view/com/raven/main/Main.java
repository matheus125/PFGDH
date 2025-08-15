package view.com.raven.main;

import com.raven.banco.ConexaoBD;
import com.raven.swing.icon.GoogleMaterialDesignIcons;
import com.raven.swing.icon.IconFontSwing;
import view.com.raven.form.Form_Home;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import view.com.raven.event.EventMenuSelected;
import java.sql.Timestamp;
import view.com.raven.form.Form_Funcionarios;
import view.com.raven.form.Form_Clientes;
import view.com.raven.form.Form_Vendas;
import view.com.raven.form.Form_ExpeSenhas;
import view.com.login.TelaLogin;
import com.api.backup.BackupMySQL;
import view.com.raven.component.CarSistema;
import view.com.raven.component.CardBackupSistema;
import view.com.raven.component.CardRelatorio;
import com.api.backup.EnviarBackupAPI;
import com.api.backup.VerificadorConexao;

public class Main extends javax.swing.JFrame {

    private Form_Home home;
    private Form_Funcionarios formFuncionarios;
    private Form_Clientes formClientes;
    private Form_Vendas formVendas;
    private Form_ExpeSenhas form_ExpeSenhas;
    private TelaLogin telaLogin;
    private CarSistema sistema;
    private CardBackupSistema sistemBackup;
    private CardRelatorio relatorio;

    int codUser;

    public String usuário;

    ConexaoBD con = new ConexaoBD();

    public Main() {
        initComponents();
    
        //  Init google icon font
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        lbusuarios.setVisible(false);
        con.getConectar();
        setBackground(new Color(0, 0, 0, 0));
        home = new Form_Home();
        formFuncionarios = new Form_Funcionarios();
        formClientes = new Form_Clientes();
        formVendas = new Form_Vendas();
        form_ExpeSenhas = new Form_ExpeSenhas();
        telaLogin = new TelaLogin();
        sistema = new CarSistema();
        sistemBackup = new CardBackupSistema();
        relatorio = new CardRelatorio();

        menu1.initMoving(Main.this);
        menu1.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                switch (index) {
                    case 0:
                        setForm(home);
                        break;
                    case 2:
                        CheckLogin();
                        break;
                    case 3:
                        setForm(formClientes);
                        break;
                    case 5:
                        setForm(formVendas);
                        break;
                    case 6:
                        setForm(form_ExpeSenhas);
                        break;
                    case 8:
                        setForm(sistemBackup);
                        break;
                    case 9:
                        int res = 0;
                        res = JOptionPane.showConfirmDialog(rootPane, "Deseja Trocar de Usuário?");
                        if (res == JOptionPane.YES_OPTION) {
                            trocarUsuario();
                            con.getfecharConexao();
                        }
                        break;
                    case 10:
                        int resposta = 0;
                        resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente sair do sistema?");
                        if (resposta == JOptionPane.YES_OPTION) {
                            salvarUserLogsExit();

                            System.exit(0);
                            con.getfecharConexao();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        //  set when system open start with home form
        setForm(new Form_Home());
    }

    
    private void CheckLogin() {
        try {
            Form_Funcionarios funcionarios = new Form_Funcionarios();
            con.executarSql("select*from tb_user where login='" + lbusuarios.getText() + "'");
            con.getResultSet().first();
            if (con.getResultSet().getString("perfil").equals("Administrador")) {
                if (funcionarios == funcionarios) {
                    setForm(funcionarios);
                    funcionarios.setVisible(true);
                } else {
                    funcionarios.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Você não tem Acesso a essa Funcionalidade!\n Contate o Administrador do Sistema!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Você não tem Acesso a essa Funcionalidade!\n Contate o Administrador do Sistema!" + ex.getMessage());
        }
    }

    private void salvarUserLogsExit() {

        con.getConectar();
        try {
            con.executarSql("select*from tb_userlogs where login='" + lbusuarios.getText() + "'");
            con.getResultSet().last();
            codUser = con.getResultSet().getInt("id");

            PreparedStatement pst = con.con.prepareStatement("UPDATE tb_userlogs SET registration_date_update = CURRENT_TIMESTAMP WHERE id=?");

            pst.setInt(1, codUser);
            pst.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no update" + e);

        }

    }

    private void trocarUsuario() {
        telaLogin.setVisible(true);
        salvarUserLogsExit();
        dispose();
    }

    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new view.com.raven.swing.PanelBorder();
        mainPanel = new javax.swing.JPanel();
        lbusuarios = new javax.swing.JLabel();
        menu1 = new view.com.raven.component.Menu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        panelBorder1.setBackground(new java.awt.Color(242, 238, 238));

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());

        lbusuarios.setEnabled(false);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1095, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbusuarios))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addComponent(lbusuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(menu1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        lbusuarios.setText(usuário);
    }//GEN-LAST:event_formWindowActivated

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbusuarios;
    private javax.swing.JPanel mainPanel;
    private view.com.raven.component.Menu menu1;
    private view.com.raven.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}

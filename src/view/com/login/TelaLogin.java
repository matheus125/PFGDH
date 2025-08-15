/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.com.login;

import com.api.backup.BackupManager;
import com.api.backup.BackupMySQL;
import com.api.backup.EnviarBackupAPI;
import com.api.backup.VerificadorConexao;
import com.mensagem.Campo_Senha;
import com.mensagem.Campo_login;
import com.mensagem.Message;
import com.mensagem.Senha_invalida;
import com.raven.banco.ConexaoBD;
import view.com.raven.main.Main;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import raven.glasspanepopup.GlassPanePopup;

public class TelaLogin extends javax.swing.JFrame {

    private final Point point = new Point();
    private final char senha;
    ConexaoBD con = new ConexaoBD();

    //Variaveis globais
    int codUser;
    String nomeUser_login;

    /**
     * Creates new form TelaLogin
     */
    public TelaLogin() {

        initComponents();

        GlassPanePopup.install(this);
        con.getConectar();
        senha = txtsenha.getEchoChar();

    }

    public void salvarUserLogs() {
        con.getConectar();
        try {
            con.executarSql("select u.id ,u.login, u.password, u.perfil, f.id ,f.nome, f.funcao, f.telefone \n"
                    + "from tb_user u \n"
                    + "inner join tb_funcionario f \n"
                    + "on u.id_funcionario = f.id where u.login ='" + txtLogin.getText() + "'");
            con.getResultSet().first();
            codUser = con.getResultSet().getInt("u.id");
            nomeUser_login = con.getResultSet().getString("f.nome");
            PreparedStatement pst = con.con.prepareStatement("insert into tb_userlogs (id_user, login, nome) values(?,?,?)");
            pst.setInt(1, codUser);
            pst.setString(2, txtLogin.getText());
            pst.setString(3, nomeUser_login);
            pst.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro em salvar" + e);

        }
    }

    private void erroSenha() {
        Point p = this.getLocation();
        TelaLogin login = this;
        new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 3; i++) {
                        login.setLocation(p.x - 10, p.y);
                        sleep(20);
                        login.setLocation(p.x - 10, p.y);
                        sleep(20);
                    }
                    login.setLocation(p.x, p.y);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }

    //MENSAGEM PARA USUARIO INEXISTENTE.
    private void Mensagem_erro_user_inexistente() {
        Message obj = new Message();
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GlassPanePopup.closePopupLast();
            }
        });
        GlassPanePopup.showPopup(obj);
    }//FIM

    //MENSAGEM PARA SENHA INVALIDA.
    private void Mensagem_senha_invalida() {
        Senha_invalida obj = new Senha_invalida();
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GlassPanePopup.closePopupLast();
            }
        });
        GlassPanePopup.showPopup(obj);
    }//FIM.

    //MENSAGEM PARA PREENCHER O CAMPO SENHA.
    private void Mensagem_preencha_senha() {
        Campo_Senha obj = new Campo_Senha();
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GlassPanePopup.closePopupLast();
            }
        });
        GlassPanePopup.showPopup(obj);
    }//FIM.

    //MENSAGEM PARA PREENCHER O CAMPO LOGIN.
    private void Mensagem_preencha_login() {
        Campo_login obj = new Campo_login();
        obj.eventOK(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                GlassPanePopup.closePopupLast();
            }
        });
        GlassPanePopup.showPopup(obj);
    }//FIM.

    private void Verificar_Conexao() {
        while (true) {
            if (VerificadorConexao.temConexao()) {
                System.out.println("Internet OK! Iniciando backup...");
                String caminhoBackup = BackupMySQL.gerarBackup();
                if (caminhoBackup != null) {
                    EnviarBackupAPI.enviar(caminhoBackup);
                }
                break;
            } else {
                System.out.println("Sem conexão. Tentará novamente em 10 segundos...");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnAcessar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        txtsenha = new javax.swing.JPasswordField();
        lb = new javax.swing.JLabel();
        lbusuarios = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(400, 410));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnAcessar.setBackground(new java.awt.Color(58, 65, 84));
        btnAcessar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAcessar.setForeground(new java.awt.Color(255, 255, 255));
        btnAcessar.setText("Entrar");
        btnAcessar.setBorderPainted(false);
        btnAcessar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAcessar.setFocusPainted(false);
        btnAcessar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAcessarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAcessarMouseExited(evt);
            }
        });
        btnAcessar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcessarActionPerformed(evt);
            }
        });

        btnSair.setBackground(new java.awt.Color(217, 81, 51));
        btnSair.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSair.setForeground(new java.awt.Color(255, 255, 255));
        btnSair.setText("Sair");
        btnSair.setBorderPainted(false);
        btnSair.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSair.setFocusPainted(false);
        btnSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSairMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSairMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSairMousePressed(evt);
            }
        });
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("Exibir Senha");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        txtsenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsenhaActionPerformed(evt);
            }
        });

        lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exibirSenha.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/identificador.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/senha.png"))); // NOI18N

        try {
            txtLogin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbusuarios)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnAcessar, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtsenha, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLogin)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbusuarios)
                .addGap(84, 84, 84)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtLogin)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtsenha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAcessar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/img.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(84, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1037, 415));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        //con.getfecharConexao();
        System.exit(0);
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnAcessarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAcessarMouseEntered
        btnAcessar.setBackground(new Color(235, 235, 235));
        btnAcessar.setForeground(new Color(58, 65, 84));
    }//GEN-LAST:event_btnAcessarMouseEntered

    private void btnSairMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSairMousePressed

    private void btnSairMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseEntered
        btnSair.setBackground(new Color(235, 235, 235));
        btnSair.setForeground(new Color(217, 81, 51));
    }//GEN-LAST:event_btnSairMouseEntered

    private void btnAcessarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAcessarMouseExited
        btnAcessar.setBackground(new Color(58, 65, 84));
        btnAcessar.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnAcessarMouseExited

    private void btnSairMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSairMouseExited
        btnSair.setBackground(new Color(217, 81, 51));
        btnSair.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnSairMouseExited

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        point.x = evt.getX();
        point.y = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Point p = this.getLocation();
        this.setLocation(p.x + evt.getX() - point.x, p.y + evt.getY() - point.y);
    }//GEN-LAST:event_formMouseDragged

    private void btnAcessarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcessarActionPerformed
        try {
            con.executarSql("select u.login, u.password, u.perfil, f.nome, f.funcao, f.telefone \n"
                    + "from tb_user u \n"
                    + "inner join tb_funcionario f \n"
                    + "on u.id_funcionario = f.id where u.login = '" + txtLogin.getText() + "'");
            con.getResultSet().first();
            if (txtLogin.getText().equals("   .   .   -  ")) {
                erroSenha();
                Mensagem_preencha_login();
                txtLogin.requestFocus();
            } else if (txtsenha.getText().isEmpty()) {
                erroSenha();
                Mensagem_preencha_senha();
                txtsenha.requestFocus();
            } else if (con.getResultSet().getString("password").equals(txtsenha.getText())) {
                salvarUserLogs();
                // Inicia verificação de conexão em segundo plano
                new Thread(() -> BackupManager.iniciarBackupComVerificacao()).start();
                Main telamenu = new Main();
                telamenu.usuário = con.getResultSet().getString("login");
                telamenu.setVisible(true);
                dispose();
            } else {
                erroSenha();
                Mensagem_senha_invalida();
            }
        } catch (SQLException ex) {
            erroSenha();
            Mensagem_erro_user_inexistente();
        }
    }//GEN-LAST:event_btnAcessarActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {
            txtsenha.setEchoChar('\u0000');
            lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/exibirSenha.png")));
            jCheckBox1.setText("Ocultar senha");
        } else {
            txtsenha.setEchoChar(senha);
            lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ocultarSenha.png")));
            jCheckBox1.setText("Exibir senha");
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginActionPerformed

    }//GEN-LAST:event_txtLoginActionPerformed

    private void txtsenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsenhaActionPerformed
        btnAcessarActionPerformed(evt);
    }//GEN-LAST:event_txtsenhaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcessar;
    private javax.swing.JButton btnSair;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbusuarios;
    private javax.swing.JFormattedTextField txtLogin;
    private javax.swing.JPasswordField txtsenha;
    // End of variables declaration//GEN-END:variables

}

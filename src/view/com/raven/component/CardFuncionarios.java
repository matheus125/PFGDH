package view.com.raven.component;

import view.com.raven.model.CPF;
import raven.glasspanepopup.GlassPanePopup;
import com.raven.banco.ConexaoBD;
import com.raven.controller.ControllerFuncionario;
import com.raven.dao.FuncionarioDao;
import com.raven.model.Funcionarios;
import com.raven.model.Usuarios;
import com.raven.tabelas.TabelaUniversal;
import view.com.raven.model.Model_Card;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import view.com.raven.swing.ScrollBar;
import view.com.raven.swing.Table;

public final class CardFuncionarios extends javax.swing.JPanel {

    int id, flag = 0;

    ControllerFuncionario controllerFuncionarios = new ControllerFuncionario();
    Funcionarios funcionarios = new Funcionarios();
    FuncionarioDao funcionarioDao = new FuncionarioDao();
    Usuarios usuarios = new Usuarios();

    ConexaoBD con = new ConexaoBD();

    public CardFuncionarios() {
        initComponents();

        desabilitarCampos();
        desabilitarBotao();
        loadFuncionariosTable("select e.id,e.nome, e.funcao, e.telefone, u.login, u.password, u.perfil \n"
                + "	from tb_funcionario e inner join tb_user u on e.id = u.id_funcionario order by nome");

    }

    public void limparCampos() {
        txtnome.setText("");
        txtlogin.setText("");
        txtFone.setText("");
        txtsenha.setText("");

    }

    public final void desabilitarCampos() {
        txtnome.setEnabled(false);
        txtlogin.setEnabled(false);
        txtFone.setEnabled(false);
        txtsenha.setEnabled(false);
        comboFunção.setEnabled(false);
        ComboPerfil.setEnabled(false);
    }

    public void habilitarCampos() {
        txtnome.setEnabled(true);
        txtlogin.setEnabled(true);
        txtsenha.setEnabled(true);
        txtFone.setEnabled(true);
        comboFunção.setEnabled(true);
        ComboPerfil.setEnabled(true);
    }

    public final void desabilitarBotao() {
        btnNovo.setEnabled(true);
        btnAlterar.setEnabled(false);
        btnSalvar.setEnabled(false);
        btnRemover.setEnabled(false);
        btnCancelar.setEnabled(false);
    }

    public void habilitarBotao() {
        btnNovo.setEnabled(false);
        btnSalvar.setEnabled(true);
        btnAlterar.setEnabled(true);
        btnRemover.setEnabled(true);
        btnCancelar.setEnabled(true);
    }

    public void saveFuncionarios() {

        //DECLARAÇÃO DE VARIÁVEIS PARA VALIDAÇÃO DE CAMPOS
        String Nome, Fone, Login, Password;

        Nome = txtnome.getText();
        Fone = txtFone.getText();
        Login = txtlogin.getText();
        Password = txtsenha.getText();

        CPF pf = new CPF(Login);
        //FIM DAS VARIÁVEIS.
        if (controllerFuncionarios.controlGetFuncionario(txtlogin.getText()) == false) {//PRIMEIRO IF DE VERIFICAÇÃO DE CPF CADASTRADO
            if (!Nome.isEmpty()) {//PRIMEIRO IF "!Nome"
                if (txtlogin.getText().equals("   .   .   -  ")) {
                    JOptionPane.showMessageDialog(this, "Campo LOGIN é Obrigatório!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                    txtlogin.requestFocus();
                    return;
                }
                if (!Fone.isEmpty()) {//SEGUNDO IF "!Fone"
                    if (!Login.isEmpty()) {//TERCEIRO IF "!Login"
                        if (!Password.isEmpty()) {//QUARTO IF "!Password"
                            if (pf.isCPF()) {
                                funcionarios.setNome(Nome);
                                funcionarios.setFuncao((String) this.comboFunção.getSelectedItem());
                                funcionarios.setTelefone(Fone);
                                usuarios.setLogin(Login);
                                usuarios.setPassword(Password);
                                usuarios.setPerfil((String) this.ComboPerfil.getSelectedItem());
                                controllerFuncionarios.controlSaveFuncionarios(funcionarios, usuarios);

                                loadFuncionariosTable("select e.id,e.nome, e.funcao, e.telefone, u.login, u.password, u.perfil \n"
                                        + "	from tb_funcionario e inner join tb_user u on e.id = u.id_funcionario order by nome");
                                limparCampos();
                                desabilitarCampos();
                                desabilitarBotao();
                            } else {
                                JOptionPane.showMessageDialog(null, "Login inválido!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Preencha o campo Password!");
                            txtsenha.requestFocus();
                        }//FIM DO QUARTO IF "!Password".
                    } else {
                        JOptionPane.showMessageDialog(this, "Preencha o campo Login!");
                        txtlogin.requestFocus();
                    }//FIM DO TERCEIRO IF "!Login".
                } else {
                    JOptionPane.showMessageDialog(this, "Preencha o campo Telefone!");
                    txtFone.requestFocus();
                }//FIM DO SEGUNDO IF "!Fone".
            } else {
                JOptionPane.showMessageDialog(this, "Preencha o campo Nome!");
                txtnome.requestFocus();
            } //FIM DO PRIMEIRO IF "!Nome".
        } else {
            JOptionPane.showMessageDialog(null, "Login já está sendo usado em outra conta!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
            txtlogin.requestFocus();
        }//FIM IF "VERIFICAÇÃO DE CPF"
    }

    public void updateFuncionarios() {
        //DECLARAÇÃO DE VARIÁVEIS PARA VALIDAÇÃO DE CAMPOS
        String Nome, Fone, Login, Password;

        Nome = txtnome.getText();
        Fone = txtFone.getText();
        Login = txtlogin.getText();
        Password = txtsenha.getText();
        //FIM DAS VARIÁVEIS.

        if (!Nome.isEmpty()) {//PRIMEIRO IF "!Nome"
            if (!Fone.isEmpty()) {//SEGUNDO IF "!Fone"
                if (!Login.isEmpty()) {//TERCEIRO IF "!Login"
                    if (!Password.isEmpty()) {//QUARTO IF "!Password"
                        funcionarios.setId(id);
                        funcionarios.setNome(Nome);
                        funcionarios.setFuncao((String) this.comboFunção.getSelectedItem());
                        funcionarios.setTelefone(Fone);
                        usuarios.setLogin(Login);
                        usuarios.setPassword(Password);
                        usuarios.setPerfil((String) this.ComboPerfil.getSelectedItem());
                        controllerFuncionarios.controlUpdateFuncionarios(funcionarios, usuarios);
                        loadFuncionariosTable("select e.id,e.nome, e.funcao, e.telefone, u.login, u.password, u.perfil \n"
                                + "	from tb_funcionario e inner join tb_user u on e.id = u.id_funcionario order by nome");
                        limparCampos();
                        desabilitarCampos();
                        desabilitarBotao();
                    } else {
                        JOptionPane.showMessageDialog(this, "Preencha o campo Password!");
                        txtsenha.requestFocus();
                    }//FIM DO QUARTO IF "!Password".
                } else {
                    JOptionPane.showMessageDialog(this, "Preencha o campo Login!");
                    txtlogin.requestFocus();
                }//FIM DO TERCEIRO IF "!Login".
            } else {
                JOptionPane.showMessageDialog(this, "Preencha o campo Telefone!");
                txtFone.requestFocus();
            }//FIM DO SEGUNDO IF "!Fone".
        } else {
            JOptionPane.showMessageDialog(this, "Preencha o campo Nome!");
            txtnome.requestFocus();
        } //FIM DO PRIMEIRO IF "!Nome".
    }

    public void excluirFuncionarios() {
        desabilitarCampos();
        int linha = Tabela_funcionarios_user.getSelectedRow();
        String tNome = (String) Tabela_funcionarios_user.getValueAt(linha, 1);
        int codigo = (int) Tabela_funcionarios_user.getValueAt(linha, 0);
        int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir esse funcionario: \n"
                + tNome + "?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.OK_OPTION) {
            boolean resultado = controllerFuncionarios.controlDeleteFuncionarios(codigo);
            if (resultado == true) {
                JOptionPane.showMessageDialog(this, "Funcionário Excluido com sucesso!");
                loadFuncionariosTable("select e.id,e.nome, e.funcao, e.telefone, u.login, u.password, u.perfil \n"
                        + "	from tb_funcionario e inner join tb_user u on e.id = u.id_funcionario order by nome");
                limparCampos();
                desabilitarCampos();
                desabilitarBotao();
                btnNovo.setEnabled(true);
            }
        }
    }

    public void pesquisarFuncionarios() {
        funcionarios.setPesquisar(txtpesquisarfuncionarios.getText());
        funcionarios = funcionarioDao.buscarFuncionarios(funcionarios, usuarios);
        id = Integer.parseInt((String.valueOf(funcionarios.getId())));
        txtnome.setText((String.valueOf(funcionarios.getNome())));
        comboFunção.setSelectedItem(funcionarios.getFuncao());
        txtFone.setText((String.valueOf(funcionarios.getTelefone())));
        txtlogin.setText((String.valueOf(usuarios.getLogin())));
        txtsenha.setText((String.valueOf(usuarios.getPassword())));
        ComboPerfil.setSelectedItem(usuarios.getPerfil());
        loadFuncionariosTable("select e.id,e.nome, e.funcao, e.telefone, u.login, u.password, u.perfil from tb_funcionario e inner join tb_user u on e.id = u.id_funcionario where nome like '%" + funcionarios.getPesquisar() + "%'");
    }

    public void loadFuncionariosTable(String Sql) {
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"ID", "Nome", "Função", "Telefone", "Login", "Perfil"};

        con.getConectar();
        con.executarSql(Sql);

        try {
            con.getResultSet().first();
            do {
                dados.add(new Object[]{con.getResultSet().getInt("id"), con.getResultSet().getString("nome"),
                    con.getResultSet().getString("funcao"), con.getResultSet().getString("telefone"),
                    con.getResultSet().getString("login"), con.getResultSet().getString("perfil")});
            } while (con.getResultSet().next());
        } catch (SQLException e) {

        }

        TabelaUniversal tabela = new TabelaUniversal(dados, colunas);

        Tabela_funcionarios_user.setModel(tabela);
        Tabela_funcionarios_user.getColumnModel().getColumn(0).setPreferredWidth(30);
        Tabela_funcionarios_user.getColumnModel().getColumn(0).setResizable(false);
        Tabela_funcionarios_user.getColumnModel().getColumn(1).setPreferredWidth(190);
        Tabela_funcionarios_user.getColumnModel().getColumn(1).setResizable(false);
        Tabela_funcionarios_user.getColumnModel().getColumn(2).setPreferredWidth(120);
        Tabela_funcionarios_user.getColumnModel().getColumn(2).setResizable(false);
        Tabela_funcionarios_user.getColumnModel().getColumn(3).setPreferredWidth(115);
        Tabela_funcionarios_user.getColumnModel().getColumn(3).setResizable(false);
        Tabela_funcionarios_user.getColumnModel().getColumn(4).setPreferredWidth(140);
        Tabela_funcionarios_user.getColumnModel().getColumn(4).setResizable(false);
        Tabela_funcionarios_user.getColumnModel().getColumn(5).setPreferredWidth(168);
        Tabela_funcionarios_user.getColumnModel().getColumn(5).setResizable(false);
        Tabela_funcionarios_user.getTableHeader().setReorderingAllowed(false);
        Tabela_funcionarios_user.setAutoResizeMode(Table.AUTO_RESIZE_OFF);
        Tabela_funcionarios_user.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void setData(Model_Card data) {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_name = new javax.swing.JLabel();
        lb_login = new javax.swing.JLabel();
        txtnome = new javax.swing.JTextField();
        lb_function = new javax.swing.JLabel();
        lb_password = new javax.swing.JLabel();
        txtsenha = new javax.swing.JPasswordField();
        comboFunção = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        ComboPerfil = new javax.swing.JComboBox<>();
        txtpesquisarfuncionarios = new javax.swing.JTextField();
        lb_name1 = new javax.swing.JLabel();
        txtFone = new javax.swing.JFormattedTextField();
        txtlogin = new javax.swing.JFormattedTextField();
        spTable = new javax.swing.JScrollPane();
        Tabela_funcionarios_user = new view.com.raven.swing.Table();
        btnBuscar = new button.Button();
        btnCancelar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1095, 697));

        lb_name.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lb_name.setForeground(new java.awt.Color(255, 255, 255));
        lb_name.setText("Nome:");

        lb_login.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lb_login.setForeground(new java.awt.Color(255, 255, 255));
        lb_login.setText("Login:");

        txtnome.setEnabled(false);

        lb_function.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lb_function.setForeground(new java.awt.Color(255, 255, 255));
        lb_function.setText("Cargo:");

        lb_password.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lb_password.setForeground(new java.awt.Color(255, 255, 255));
        lb_password.setText("Password:");

        txtsenha.setEnabled(false);

        comboFunção.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gerente", "Supervisor", "Assessor" }));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Perfil:");

        ComboPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Simples" }));

        txtpesquisarfuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpesquisarfuncionariosActionPerformed(evt);
            }
        });

        lb_name1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lb_name1.setForeground(new java.awt.Color(255, 255, 255));
        lb_name1.setText("Telefone:");

        try {
            txtFone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFone.setEnabled(false);

        try {
            txtlogin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtlogin.setEnabled(false);

        spTable.setBorder(null);

        Tabela_funcionarios_user.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Tabela_funcionarios_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabela_funcionarios_userMouseClicked(evt);
            }
        });
        spTable.setViewportView(Tabela_funcionarios_user);

        btnBuscar.setText("Pesquisar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnRemover.setText("Excluir");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lb_name1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lb_name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lb_login, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtnome)
                                            .addComponent(txtlogin, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lb_function, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(comboFunção, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lb_password, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ComboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtsenha, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtpesquisarfuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(btnCancelar)
                                        .addGap(14, 14, 14)
                                        .addComponent(btnRemover)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnAlterar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSalvar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnNovo)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(166, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtnome)
                        .addComponent(lb_name, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lb_function, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboFunção, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_password, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsenha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_login, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtlogin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ComboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_name1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnRemover)
                    .addComponent(btnAlterar)
                    .addComponent(btnSalvar)
                    .addComponent(btnNovo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpesquisarfuncionarios, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Tabela_funcionarios_userMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabela_funcionarios_userMouseClicked
        btnNovo.setEnabled(false);
        btnAlterar.setEnabled(true);
        btnRemover.setEnabled(true);
        btnCancelar.setEnabled(true);
        String nome = "" + Tabela_funcionarios_user.getValueAt(Tabela_funcionarios_user.getSelectedRow(), 1);
        con.getConectar();
        con.executarSql("select e.id, e.nome, e.funcao, e.telefone, u.login, u.password, u.perfil\n"
                + "		from tb_funcionario e inner join tb_user u on e.id = u.id_funcionario where nome ='" + nome + "'");
        try {
            con.getResultSet().first();
            id = Integer.parseInt(con.getResultSet().getString("id"));
            txtnome.setText(con.getResultSet().getString("nome"));
            comboFunção.setSelectedItem(con.getResultSet().getString("funcao"));
            txtFone.setText(con.getResultSet().getString("telefone"));
            txtlogin.setText(con.getResultSet().getString("login"));
            txtsenha.setText(con.getResultSet().getString("password"));
            ComboPerfil.setSelectedItem(con.getResultSet().getString("perfil"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no ao selecionar os dados" + ex);
        }
    }//GEN-LAST:event_Tabela_funcionarios_userMouseClicked

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        pesquisarFuncionarios();
        btnNovo.setEnabled(false);
        btnAlterar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtpesquisarfuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpesquisarfuncionariosActionPerformed
        btnBuscarActionPerformed(evt);
    }//GEN-LAST:event_txtpesquisarfuncionariosActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (flag == 1) {
            saveFuncionarios();
        } else {
            updateFuncionarios();
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        flag = 2;
        habilitarCampos();
        habilitarBotao();
        btnNovo.setEnabled(false);
        btnAlterar.setEnabled(false);
        btnRemover.setEnabled(false);
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        excluirFuncionarios();
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limparCampos();
        desabilitarBotao();
        desabilitarCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        flag = 1;
        habilitarCampos();
        habilitarBotao();
        btnNovo.setEnabled(false);
        btnAlterar.setEnabled(false);
        btnRemover.setEnabled(false);
    }//GEN-LAST:event_btnNovoActionPerformed

    @Override
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#1CB5E0"), 0, getHeight(), Color.decode("#000046"));
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
        super.paintChildren(grphcs);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboPerfil;
    private view.com.raven.swing.Table Tabela_funcionarios_user;
    private javax.swing.JButton btnAlterar;
    private button.Button btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> comboFunção;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lb_function;
    private javax.swing.JLabel lb_login;
    private javax.swing.JLabel lb_name;
    private javax.swing.JLabel lb_name1;
    private javax.swing.JLabel lb_password;
    private javax.swing.JScrollPane spTable;
    private javax.swing.JFormattedTextField txtFone;
    private javax.swing.JFormattedTextField txtlogin;
    private javax.swing.JTextField txtnome;
    private javax.swing.JTextField txtpesquisarfuncionarios;
    private javax.swing.JPasswordField txtsenha;
    // End of variables declaration//GEN-END:variables

}

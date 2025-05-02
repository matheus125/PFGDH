package view.com.raven.component;

import java.sql.*;
import com.raven.banco.ConexaoBD;
import com.raven.controller.ControllerDependentes;
import com.raven.controller.ControllerRelatorios;
import com.raven.controller.ControllerSenha;
import com.raven.dao.FrequenciaDAO;
import com.raven.dao.SenhaDao;
import com.raven.dao.TitularDao;
import com.raven.model.Frequencia;
import com.raven.model.Relatorios;
import com.raven.model.Senha;
import com.raven.model.Titular;
import com.raven.tabelas.TabelaUniversal;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import view.com.raven.swing.ScrollBar;

public final class CardVendas extends javax.swing.JPanel {

    int id_clientes, id_frequencia, contador = 400;

    String idade_dependente, genero_dependente, data2, genero, deficiencia, idade, nome_dependente, dependencia, status_cliente;

    //CHAMANDO MINHA CLASSE DE CONEXÃO.
    ConexaoBD con = new ConexaoBD();

    //CHAMANDO MEUS METODOS CONTROLADORES.
    ControllerSenha controllerSenha = new ControllerSenha();
    //CHAMANDO CLASSES.
    Senha senha = new Senha();
    Titular titular = new Titular();

    //CHAMANDO METODOS DÃO PARA BACK-END
    SenhaDao senhaDao = new SenhaDao();
    TitularDao titularDao = new TitularDao();

    //CONSTRUTOR PRINCIPAL
    public CardVendas() {
        initComponents();
        txtidade1.setVisible(false);
        txtgenero.setVisible(false);
        txtData_refeicao.setVisible(false);
        txtgenero.setVisible(false);
        txtdeficiencia.setVisible(false);
        setOpaque(false);
        tabela_cliente_titular("select t.id, t.nome_Completo, t.cpf, t.rg, t.idade_cliente, t.genero_cliente, t.status_Cliente, d.nome_dependente, d.Idade, d.genero, d.dependencia_cliente from tb_titular t left join tb_dependentes d USING (id) order by t.nome_Completo;");

        // A  linha Abaixo exibe a Data Atual do Sistema.
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        txtData_refeicao.setText(df.format(data));
        data2 = txtData_refeicao.getText();
    }

    //TABELA CARREGANDO DADOS DOS CLIENTES
    //TABELA CARREGANDO DADOS DOS CLIENTES
    public final void tabela_cliente_titular(String Sql) {

        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);

        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"TITULAR", "CPF", "IDADE", "GÊNERO", "STATUS"};
        con.getConectar();
        con.executarSql(Sql);
        //Inserir dados na tabela  "ID", "NOME", "CPF", "RG", 
        try {
            con.getResultSet().first();
            do {
                dados.add(new Object[]{
                    con.getResultSet().getString("t.nome_Completo"),
                    con.getResultSet().getString("t.cpf"),
                    con.getResultSet().getInt("t.idade_cliente"),
                    con.getResultSet().getString("t.genero_cliente"),
                    con.getResultSet().getString("t.status_Cliente")
                });
            } while (con.getResultSet().next());
        } catch (SQLException e) {

        }

        TabelaUniversal tabela = new TabelaUniversal(dados, colunas);

        table_cliente_Titular.setModel(tabela);
        table_cliente_Titular.getColumnModel().getColumn(0).setPreferredWidth(150);
        table_cliente_Titular.getColumnModel().getColumn(0).setResizable(false);
        table_cliente_Titular.getColumnModel().getColumn(1).setPreferredWidth(187);
        table_cliente_Titular.getColumnModel().getColumn(1).setResizable(false);
        table_cliente_Titular.getColumnModel().getColumn(2).setPreferredWidth(150);
        table_cliente_Titular.getColumnModel().getColumn(2).setResizable(false);
        table_cliente_Titular.getColumnModel().getColumn(3).setPreferredWidth(90);
        table_cliente_Titular.getColumnModel().getColumn(3).setResizable(false);
        table_cliente_Titular.getColumnModel().getColumn(4).setPreferredWidth(90);
        table_cliente_Titular.getColumnModel().getColumn(4).setResizable(false);
        table_cliente_Titular.getTableHeader().setReorderingAllowed(false);

        table_cliente_Titular.setAutoResizeMode(table_cliente_Titular.AUTO_RESIZE_OFF);
        table_cliente_Titular.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

//METODO PARA SALVAR SENHAS TITULADAS
    public void salvarSenhas() {
        String nome, idade, genero, deficiente;
        int id_frequencia;

        nome = txtCliente.getText();
        id_frequencia = Integer.parseInt(txtid_cliente.getText());
        idade = txtidade1.getText();
        genero = txtgenero.getText();
        deficiente = txtdeficiencia.getText();

        if (!nome.isEmpty()) {
            senha.setCliente(nome);
            senha.setGenero(genero);
            senha.setIdade(idade);
            senha.setDeficiencia(deficiente);
            senha.setStatus_cliente(status_cliente);
            senha.setData_refeicao(data2);

            System.out.println(senha);

            // Criando um objeto Frequencia
            Frequencia frequencia = new Frequencia(id_frequencia, nome, new Date(), "presente");

            // Adicionando frequência ao banco de dados
            FrequenciaDAO frequenciaDAO = new FrequenciaDAO();
            frequenciaDAO.adicionarFrequencia(frequencia);
            frequenciaDAO.adicionarFrequenciaGeral(frequencia);

            boolean resultado = controllerSenha.controlSaveSenhas(senha);
            if (resultado == true) {
                senhaDao.escreverNoTXT(senhaDao.recuperarSenha());
                ultimaSenha.setText("Última senha: " + controllerSenha.controlRetornarUltimaSenha());
                txtPesquisarNomeClientes.setText("");
                txtCliente.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao Salvar!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um nome!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            return;
        }//FIM IF "rua"

    }//FIM.

    //METODO PARA SALVAR SENHAS GENERICAS
    public void salvarSenhaGenerica() {
        senha.setData_refeicao(data2);

        boolean resultado = controllerSenha.controlSaveSenhasGenericas(senha);
        if (resultado == true) {
            JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
            senhaDao.escreverNoTXT(senhaDao.recuperarSenha());
            ultimaSenha.setText("Última senha: " + senhaDao.retornarUltimaSenha());
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao Salvar!");
        }
    }//FIM.
    
    public void Buscar() {
        titular.setPesquisar(txtPesquisarNomeClientes.getText());
        titular = titularDao.buscarClientes(titular);
        id_clientes = Integer.parseInt((String.valueOf(titular.getId())));
        tabela_cliente_titular("SELECT t.id, t.nome_Completo, t.cpf, t.idade_cliente, t.genero_cliente, "
                + "t.status_Cliente, GROUP_CONCAT(d.nome_dependente ORDER BY d.nome_dependente) AS dependentes "
                + "FROM tb_titular t LEFT JOIN tb_dependentes d ON d.id_titular = t.id WHERE t.nome_Completo like '%" + titular.getPesquisar() + "%' or t.cpf like '%" + titular.getPesquisar() + "' GROUP BY t.id, t.nome_Completo, t.cpf, t.idade_cliente, t.genero_cliente, t.status_Cliente");

    }

// Método para popular a tabela com os resultados
    private void tabela_cliente_titular(List<Titular> clientes) {
        String nome = "" + table_cliente_Titular.getValueAt(table_cliente_Titular.getSelectedRow(), 0);
        con.getConectar();
        con.executarSql("select t.id,t.nome_Completo, t.cpf, t.idade_cliente, t.genero_cliente, s.deficiencia, d.nome_dependente, d.Idade, d.genero, d.dependencia_cliente from tb_titular t left join tb_dependentes d on d.id_titular = t.id left join tb_socio_economico_saude s on s.id_titular = t.id where t.nome_Completo ='" + nome + "'");
        DefaultTableModel modelo = (DefaultTableModel) table_cliente_Dependente.getModel();
        modelo.setNumRows(0);
        try {
            while (con.getResultSet().next()) {

                txtid_cliente.setText(con.getResultSet().getString("t.id"));

                txtCliente.setText(con.getResultSet().getString("t.nome_Completo"));
                nome_dependente = con.getResultSet().getString("d.nome_dependente");

                txtidade1.setText(con.getResultSet().getString("t.idade_cliente"));
                idade = txtidade1.getText();

                txtgenero.setText(con.getResultSet().getString("t.genero_cliente"));
                genero = txtgenero.getText();

                txtdeficiencia.setText(con.getResultSet().getString("s.deficiencia"));
                deficiencia = txtdeficiencia.getText();

                idade_dependente = con.getResultSet().getString("d.Idade");
//                idade_dependente = txtidade2.getText();

                genero_dependente = con.getResultSet().getString("d.genero");
                dependencia = con.getResultSet().getString("d.dependencia_cliente");

                table_cliente_Dependente_principal.setVerticalScrollBar(new ScrollBar());
                table_cliente_Dependente_principal.getVerticalScrollBar().setBackground(Color.WHITE);
                table_cliente_Dependente_principal.getViewport().setBackground(Color.WHITE);
                JPanel p = new JPanel();
                p.setBackground(Color.WHITE);
                table_cliente_Dependente_principal.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

                //PASSANDO OS DEPENDENTES PARA A OUTRA TABELA.
                for (int i = 0; i < 1; i++) {
                    table_cliente_Dependente.addRow(new Object[]{
                        nome_dependente,
                        idade_dependente,
                        genero_dependente,
                        dependencia
                    });
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no ao selecionar os dados" + ex);
        }
        if (controllerSenha.controlVerificarSenhaCliente(table_cliente_Titular.getValueAt(table_cliente_Titular.getSelectedRow(), 0).toString())) {
            JOptionPane.showMessageDialog(null, "Este cliente já comprou senha neste dia!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
            txtCliente.setText("");
            txtPesquisarNomeClientes.setText("");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        txtPesquisarNomeClientes = new javax.swing.JTextField();
        btnTitulada = new javax.swing.JButton();
        btnGenerico = new javax.swing.JButton();
        ultimaSenha = new javax.swing.JLabel();
        txtData_refeicao = new javax.swing.JLabel();
        btnAtualizar = new javax.swing.JButton();
        txtCliente = new javax.swing.JTextField();
        btnBuscarClienteTitular = new button.Button();
        table_cliente_Dependente_principal = new javax.swing.JScrollPane();
        table_cliente_Dependente = new view.com.raven.swing.Table();
        txtidade1 = new javax.swing.JTextField();
        txtgenero = new javax.swing.JTextField();
        txtdeficiencia = new javax.swing.JTextField();
        txtid_cliente = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_cliente_Titular = new javax.swing.JTable();

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nome Completo:");

        txtPesquisarNomeClientes.setDisabledTextColor(new java.awt.Color(187, 187, 187));
        txtPesquisarNomeClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarNomeClientesActionPerformed(evt);
            }
        });

        btnTitulada.setText("Gerar Senha");
        btnTitulada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTituladaActionPerformed(evt);
            }
        });

        btnGenerico.setText("Senha Genérica");
        btnGenerico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenericoActionPerformed(evt);
            }
        });

        ultimaSenha.setForeground(new java.awt.Color(255, 255, 255));

        btnAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/atualizar.png"))); // NOI18N
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        txtCliente.setEditable(false);
        txtCliente.setDisabledTextColor(new java.awt.Color(187, 187, 187));
        txtCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteActionPerformed(evt);
            }
        });

        btnBuscarClienteTitular.setText("Pesquisar");
        btnBuscarClienteTitular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteTitularActionPerformed(evt);
            }
        });

        table_cliente_Dependente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NOME", "IDADE", "GENERO", "DEPENDENCIA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_cliente_Dependente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_cliente_DependenteMouseClicked(evt);
            }
        });
        table_cliente_Dependente_principal.setViewportView(table_cliente_Dependente);

        table_cliente_Titular.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "NOME", "IDADE"
            }
        ));
        table_cliente_Titular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_cliente_TitularMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_cliente_Titular);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtData_refeicao, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(txtid_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnTitulada)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGenerico))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPesquisarNomeClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBuscarClienteTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAtualizar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(table_cliente_Dependente_principal, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(ultimaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(528, 528, 528)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtgenero, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtidade1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(txtdeficiencia, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(210, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtData_refeicao, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtPesquisarNomeClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(34, 34, 34))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtid_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnTitulada)
                                .addComponent(btnGenerico))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBuscarClienteTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAtualizar)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(table_cliente_Dependente_principal, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ultimaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtdeficiencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66))
                    .addComponent(txtidade1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtgenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void table_cliente_FamiliaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_cliente_FamiliaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table_cliente_FamiliaMouseClicked

    private void btnTituladaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTituladaActionPerformed

        int resultado = JOptionPane.showConfirmDialog(null, "Deseja gerar senha para: " + txtCliente.getText() + "?");

        if (resultado == 0) {

            if (controllerSenha.controlRetornarUltimaSenha() < contador) {

                for (int i = 0; i < 1; i++) {

                    if (controllerSenha.controlRetornarUltimaSenha() < contador) {
                        salvarSenhas();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Senhas encerradas!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cancelado!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
            txtCliente.setText("");
        }

        String nome = txtCliente.getText();


    }//GEN-LAST:event_btnTituladaActionPerformed

    private void btnGenericoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenericoActionPerformed

        int resultado = JOptionPane.showConfirmDialog(null, "Deseja gerar senha genérica?");

        if (resultado == 0) {

            if (controllerSenha.controlRetornarUltimaSenha() < contador) {

                for (int i = 0; i < 1; i++) {

                    if (senhaDao.retornarUltimaSenha() < contador) {
                        salvarSenhaGenerica();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Senhas encerradas!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cancelado!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
            txtCliente.setText("");
        }
    }//GEN-LAST:event_btnGenericoActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        tabela_cliente_titular("select t.id, t.nome_Completo, t.cpf, t.rg, t.idade_cliente, t.genero_cliente, t.status_Cliente, d.nome_dependente, d.Idade, d.genero, d.dependencia_cliente from tb_titular t left join tb_dependentes d USING (id) order by t.nome_Completo;");


    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void txtPesquisarNomeClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarNomeClientesActionPerformed
        btnBuscarClienteTitularActionPerformed(evt);
    }//GEN-LAST:event_txtPesquisarNomeClientesActionPerformed

    private void txtClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteActionPerformed

    private void btnBuscarClienteTitularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteTitularActionPerformed
        Buscar();
    }//GEN-LAST:event_btnBuscarClienteTitularActionPerformed

    private void table_cliente_DependenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_cliente_DependenteMouseClicked
        String nome = "" + table_cliente_Dependente.getValueAt(table_cliente_Dependente.getSelectedRow(), 0);
        txtCliente.setText(nome);

        int index = table_cliente_Dependente.getSelectedRow();
        TableModel model = table_cliente_Dependente.getModel();
        int value1 = Integer.parseInt(model.getValueAt(index, 1).toString());
        String value2 = model.getValueAt(index, 2).toString();

        String idade = Integer.toString(value1);

        txtidade1.setText(idade);
        txtgenero.setText(value2);

        if (controllerSenha.controlVerificarSenhaCliente(table_cliente_Dependente.getValueAt(table_cliente_Dependente.getSelectedRow(), 0).toString())) {
            JOptionPane.showMessageDialog(null, "Este cliente já comprou senha neste dia!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
            txtCliente.setText("");
            txtPesquisarNomeClientes.setText("");
        }


    }//GEN-LAST:event_table_cliente_DependenteMouseClicked

    private void table_cliente_TitularMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_cliente_TitularMouseClicked
        String nome = "" + table_cliente_Titular.getValueAt(table_cliente_Titular.getSelectedRow(), 0);
        con.getConectar();
        con.executarSql("select t.id, t.nome_Completo, t.cpf, t.idade_cliente, t.genero_cliente, s.deficiencia, t.status_Cliente, d.nome_dependente, d.Idade, d.genero, d.dependencia_cliente from tb_titular t left join tb_dependentes d on d.id_titular = t.id left join tb_socio_economico_saude s on s.id_titular = t.id where t.nome_Completo ='" + nome + "'");
        DefaultTableModel modelo = (DefaultTableModel) table_cliente_Dependente.getModel();
        modelo.setNumRows(0);
        try {
            while (con.getResultSet().next()) {
                txtid_cliente.setText(con.getResultSet().getString("t.id"));

                txtCliente.setText(con.getResultSet().getString("t.nome_Completo"));
                nome_dependente = con.getResultSet().getString("d.nome_dependente");

                txtidade1.setText(con.getResultSet().getString("t.idade_cliente"));
                idade = txtidade1.getText();

                txtgenero.setText(con.getResultSet().getString("t.genero_cliente"));
                genero = txtgenero.getText();

                txtdeficiencia.setText(con.getResultSet().getString("s.deficiencia"));
                deficiencia = txtdeficiencia.getText();

                idade_dependente = con.getResultSet().getString("d.Idade");
//                idade_dependente = txtidade2.getText();

                genero_dependente = con.getResultSet().getString("d.genero");
                
                status_cliente = con.getResultSet().getString("t.status_Cliente");
                dependencia = con.getResultSet().getString("d.dependencia_cliente");

                table_cliente_Dependente_principal.setVerticalScrollBar(new ScrollBar());
                table_cliente_Dependente_principal.getVerticalScrollBar().setBackground(Color.WHITE);
                table_cliente_Dependente_principal.getViewport().setBackground(Color.WHITE);
                JPanel p = new JPanel();
                p.setBackground(Color.WHITE);
                table_cliente_Dependente_principal.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

                //PASSANDO OS DEPENDENTES PARA A OUTRA TABELA.
                for (int i = 0; i < 1; i++) {
                    table_cliente_Dependente.addRow(new Object[]{
                        nome_dependente,
                        idade_dependente,
                        genero_dependente,
                        dependencia
                    });
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no ao selecionar os dados" + ex);
        }
        if (controllerSenha.controlVerificarSenhaCliente(table_cliente_Titular.getValueAt(table_cliente_Titular.getSelectedRow(), 0).toString())) {
            JOptionPane.showMessageDialog(null, "Este cliente já comprou senha neste dia!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
            txtCliente.setText("");
            txtPesquisarNomeClientes.setText("");
        }
    }//GEN-LAST:event_table_cliente_TitularMouseClicked

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
    private javax.swing.JButton btnAtualizar;
    private button.Button btnBuscarClienteTitular;
    private javax.swing.JButton btnGenerico;
    private javax.swing.JButton btnTitulada;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private view.com.raven.swing.Table table_cliente_Dependente;
    private javax.swing.JScrollPane table_cliente_Dependente_principal;
    private javax.swing.JTable table_cliente_Titular;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JLabel txtData_refeicao;
    private javax.swing.JTextField txtPesquisarNomeClientes;
    private javax.swing.JTextField txtdeficiencia;
    private javax.swing.JTextField txtgenero;
    private javax.swing.JLabel txtid_cliente;
    private javax.swing.JTextField txtidade1;
    private javax.swing.JLabel ultimaSenha;
    // End of variables declaration//GEN-END:variables
}

package view.com.raven.form;

import com.raven.banco.ConexaoBD;
import com.raven.swing.icon.GoogleMaterialDesignIcons;
import com.raven.swing.icon.IconFontSwing;
import com.raven.tabelas.TabelaUniversal;
import view.com.raven.swing.ScrollBar;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import view.com.raven.model.ModelCard;
import view.com.raven.swing.Table2;

public class Form_Home extends javax.swing.JPanel {

    //INSTANCIAMENTO DE CLASSE DE CONEXÃO DO BANCO DE DADOS.
    ConexaoBD con = new ConexaoBD();

    public Form_Home() {
        initComponents();
        initData();
        tabela_cliente_socio_economico("select t.nome_Completo, t.nome_Social, t.cor_cliente, t.nome_Mae, t.telefone, t.data_nascimento, t.idade_cliente, t.genero_cliente, \n"
                + "t.estado_Civil, t.rg, t.cpf, t.nis, t.status_Cliente, e.escolariedade, e.renda_mensal_familia, e.programas_sociais, e.composicao_familiar, \n"
                + "e.moradia, e.estrutura_Moradia, e.qtdPessoas_Trabalhando, e.emprego, e.profissao_Responsavel, e.AB_Agua, e.SN_basico, e.Energia_eletrica, \n"
                + "e.lixo_Domiciliar, e.frequenta_Centro from tb_titular t left join tb_socio_economico e on e.id = t.id;");

    }

    private void initData() {
        initCardData();
    }

    private void initCardData() {
        Icon icon1 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PEOPLE, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        card1.setData(new ModelCard("TITULARES", icon1));
        Icon icon2 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PEOPLE, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        card2.setData(new ModelCard("DEPENDENTES", icon2));
        Icon icon3 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PEOPLE, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        card31.setData(new ModelCard("TOTAL CADASTRADOS", icon3));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        card1 = new view.com.raven.component.Card();
        card2 = new view.com.raven.component.Card2();
        card31 = new view.com.raven.component.Card3();
        panelBorder1 = new view.com.raven.swing.PanelBorder();
        spTable = new javax.swing.JScrollPane();
        tabela_cliente_socio_economico = new view.com.raven.swing.Table2();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(242, 238, 238));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(142, 142, 250));
        card1.setColor2(new java.awt.Color(123, 123, 245));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(142, 142, 250));
        card2.setColor2(new java.awt.Color(123, 123, 245));
        panel.add(card2);

        card31.setColor1(new java.awt.Color(142, 142, 250));
        card31.setColor2(new java.awt.Color(123, 123, 245));
        panel.add(card31);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        spTable.setBorder(null);

        tabela_cliente_socio_economico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        spTable.setViewportView(tabela_cliente_socio_economico);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/atualizar.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 793, Short.MAX_VALUE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tabela_cliente_socio_economico("select t.nome_Completo, t.nome_Social, t.cor_cliente, t.nome_Mae, t.telefone, t.data_nascimento, t.idade_cliente, t.genero_cliente, \n"
                + "t.estado_Civil, t.rg, t.cpf, t.nis, t.status_Cliente, e.escolariedade, e.renda_mensal_familia, e.programas_sociais, e.composicao_familiar, \n"
                + "e.moradia, e.estrutura_Moradia, e.qtdPessoas_Trabalhando, e.emprego, e.profissao_Responsavel, e.AB_Agua, e.SN_basico, e.Energia_eletrica, \n"
                + "e.lixo_Domiciliar, e.frequenta_Centro from tb_titular t left join tb_socio_economico e on e.id = t.id;");
    }//GEN-LAST:event_jButton1ActionPerformed

    public final void tabela_cliente_socio_economico(String Sql) {
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);

        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{
            "NOME COMPLETO",
            "NOME SOCIAL",
            "COR/ETNIA",
            "NOME DA MÃE",
            "TELEFONE",
            "DATA NASCIMENTO",
            "IDADE", "GENERO",
            "ESTADO CIVIL",
            "RG",
            "CPF",
            "NIS",
            "STATUS",
            "ESCOLARIEDADE",
            "RENDA FAMILIAR",
            "PROGRAMAS SOCIAIS",
            "COMPOSIÇÃO FAMILIAR",
            "MORADIA",
            "ESTRUTURA MORADIA",
            "QTD PESSOAS TRABALHANDO",
            "CATEGORIA DE TRABALHADORES",
            "PROFISSÃO RESPONSÁVEL",
            "ÁGUA ENCANADA",
            "SANEAMENTO BÁSICO",
            "ENERGIA ELETRICA",
            "LIXO_DOMICILIAR",
            "FREQUENTA_CENTRO"};

        con.getConectar();
        con.executarSql(Sql);
        //Inserir dados na tabela
        try {
            con.getResultSet().first();
            do {
                dados.add(new Object[]{con.getResultSet().getString("t.nome_Completo"),
                    con.getResultSet().getString("t.nome_Social"),
                    con.getResultSet().getString("t.cor_cliente"),
                    con.getResultSet().getString("t.nome_Mae"),
                    con.getResultSet().getString("t.telefone"),
                    con.getResultSet().getString("t.data_nascimento"),
                    con.getResultSet().getInt("t.idade_cliente"),
                    con.getResultSet().getString("t.genero_cliente"),
                    con.getResultSet().getString("t.estado_Civil"),
                    con.getResultSet().getString("t.rg"),
                    con.getResultSet().getString("t.cpf"),
                    con.getResultSet().getString("t.nis"),
                    con.getResultSet().getString("t.status_Cliente"),
                    con.getResultSet().getString("e.escolariedade"),
                    con.getResultSet().getString("e.renda_mensal_familia"),
                    con.getResultSet().getString("e.programas_sociais"),
                    con.getResultSet().getString("e.composicao_familiar"),
                    con.getResultSet().getString("e.moradia"),
                    con.getResultSet().getInt("e.qtdPessoas_Trabalhando"),
                    con.getResultSet().getString("e.emprego"),
                    con.getResultSet().getString("e.profissao_Responsavel"),
                    con.getResultSet().getString("e.AB_Agua"),
                    con.getResultSet().getString("e.SN_basico"),
                    con.getResultSet().getString("e.Energia_eletrica"),
                    con.getResultSet().getString("e.lixo_Domiciliar"),
                    con.getResultSet().getString("e.frequenta_Centro")
                });
            } while (con.getResultSet().next());
        } catch (SQLException e) {

        }

        TabelaUniversal tabela = new TabelaUniversal(dados, colunas);

        tabela_cliente_socio_economico.setModel(tabela);

        tabela_cliente_socio_economico.getColumnModel().getColumn(0).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(0).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(1).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(2).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(2).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(3).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(3).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(4).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(4).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(5).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(5).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(6).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(6).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(7).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(7).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(8).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(8).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(9).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(9).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(10).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(10).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(11).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(11).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(12).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(12).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(13).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(13).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(14).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(14).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(15).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(15).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(16).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(16).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(17).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(17).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(18).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(18).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(19).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(19).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(20).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(20).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(21).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(21).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(22).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(22).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(23).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(23).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(24).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(24).setResizable(false);
        tabela_cliente_socio_economico.getColumnModel().getColumn(25).setPreferredWidth(200);
        tabela_cliente_socio_economico.getColumnModel().getColumn(25).setResizable(false);
        tabela_cliente_socio_economico.getTableHeader().setReorderingAllowed(false);
        tabela_cliente_socio_economico.setAutoResizeMode(Table2.AUTO_RESIZE_OFF);
        tabela_cliente_socio_economico.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.com.raven.component.Card card1;
    private view.com.raven.component.Card2 card2;
    private view.com.raven.component.Card3 card31;
    private javax.swing.JButton jButton1;
    private javax.swing.JLayeredPane panel;
    private view.com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private view.com.raven.swing.Table2 tabela_cliente_socio_economico;
    // End of variables declaration//GEN-END:variables
}

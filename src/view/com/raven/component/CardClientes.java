package view.com.raven.component;

import view.com.raven.model.CPF;
import com.raven.banco.ConexaoBD;
import com.raven.controller.ControllerTitular;
import com.raven.controller.ControllerDependentes;
import com.raven.controller.ControllerSocioEconomico;
import com.raven.controller.ControllerSocioEconomicoSaude;
import com.raven.dao.ClientesDao;
import com.raven.dao.DependenteDao;
import com.raven.dao.TitularDao;
import com.raven.model.Titular;
import com.raven.model.Dependentes;
import com.raven.model.Endereco;
import com.raven.model.SocioEconomico;
import com.raven.model.SocioEconomicoSaude;
import com.raven.tabelas.TabelaUniversal;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import view.com.raven.swing.ScrollBar;
import view.com.raven.swing.Table2;

public final class CardClientes extends javax.swing.JPanel {

    // VARIAVEIS GLOBAIS.
    int id, flag = 0, flag1 = 0, cliIdade, id_dependente, id_titular, id_cliente;

    //INSTANCIAMENTO DE CLASSES.
    Titular titular = new Titular();
    Endereco endereco = new Endereco();
    Dependentes dependentes = new Dependentes();
    SocioEconomico socioEconomico = new SocioEconomico();
    SocioEconomicoSaude socioEconomicoSaude = new SocioEconomicoSaude();

    //INSTANCIAMENTO PARA METODO ARRAYLIST.
    ArrayList<Titular> listTitular = new ArrayList<>();
    ArrayList<Dependentes> listDependentes = new ArrayList<>();

    //INSTANCIAMENTO DE CLASSE DE CONEXÃO DO BANCO DE DADOS.
    ConexaoBD con = new ConexaoBD();

    //INSTANCIAMENTO DAS CASSES CONTROLADORAS.
    ControllerTitular controllerTitular = new ControllerTitular();
    ControllerDependentes controllerDependetes = new ControllerDependentes();
    ControllerSocioEconomico controllerSocioEconomico = new ControllerSocioEconomico();
    ControllerSocioEconomicoSaude controllerSocioEconomicoSaude = new ControllerSocioEconomicoSaude();

    TitularDao titularDao = new TitularDao();
    DependenteDao dependenteDao = new DependenteDao();

    public CardClientes() {
        initComponents();
        setOpaque(false);

        //CHAMADA DE METODOS PARA DESABILITAR CAMPOS E BOTÕES.
        desabilitarCampos();
        DesabilitarCamposDependentes();
        desabilitarBotao();
        desabilitarBotaoDependente();

        //INICIALIZAÇÃO DE TABELAS AO LIGAR O SISTEMA
        atualizarTabelas();
    }

    //TABELA CARREGANDO DADOS DOS CLIENTES NA GUIA CLIENTE
    public final void tabela_cliente(String Sql) {
        spTableCliente.setVerticalScrollBar(new ScrollBar());
        spTableCliente.getVerticalScrollBar().setBackground(Color.WHITE);
        spTableCliente.getViewport().setBackground(Color.WHITE);

        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTableCliente.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"ID", "Nome", "RG", "CPF", "NIS", "IDADE", "SEXO", "STATUS", "DATA CADASTRO"};

        con.getConectar();
        con.executarSql(Sql);
        //Inserir dados na tabela
        try {
            con.getResultSet().first();
            do {
                dados.add(new Object[]{con.getResultSet().getInt("c.id"),
                    con.getResultSet().getString("c.nome_Completo"),
                    con.getResultSet().getString("c.rg"),
                    con.getResultSet().getString("c.cpf"),
                    con.getResultSet().getString("c.nis"),
                    con.getResultSet().getString("c.idade_cliente"),
                    con.getResultSet().getString("c.genero_cliente"),
                    con.getResultSet().getString("c.status_Cliente"),
                    con.getResultSet().getString("c.registration_date")
                });
            } while (con.getResultSet().next());
        } catch (SQLException e) {

        }

        TabelaUniversal tabela = new TabelaUniversal(dados, colunas);

        table_cliente.setModel(tabela);

        table_cliente.getColumnModel().getColumn(0).setPreferredWidth(30);
        table_cliente.getColumnModel().getColumn(0).setResizable(false);
        table_cliente.getColumnModel().getColumn(1).setPreferredWidth(190);
        table_cliente.getColumnModel().getColumn(1).setResizable(false);
        table_cliente.getColumnModel().getColumn(2).setPreferredWidth(120);
        table_cliente.getColumnModel().getColumn(2).setResizable(false);
        table_cliente.getColumnModel().getColumn(3).setPreferredWidth(115);
        table_cliente.getColumnModel().getColumn(3).setResizable(false);
        table_cliente.getColumnModel().getColumn(4).setPreferredWidth(50);
        table_cliente.getColumnModel().getColumn(4).setResizable(false);
        table_cliente.getColumnModel().getColumn(5).setPreferredWidth(168);
        table_cliente.getColumnModel().getColumn(5).setResizable(false);
        table_cliente.getColumnModel().getColumn(6).setPreferredWidth(187);
        table_cliente.getColumnModel().getColumn(6).setResizable(false);
        table_cliente.getColumnModel().getColumn(7).setPreferredWidth(187);
        table_cliente.getColumnModel().getColumn(7).setResizable(false);
        table_cliente.getColumnModel().getColumn(8).setPreferredWidth(187);
        table_cliente.getColumnModel().getColumn(8).setResizable(false);
        table_cliente.getTableHeader().setReorderingAllowed(false);
        table_cliente.setAutoResizeMode(Table2.AUTO_RESIZE_OFF);
        table_cliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }//FIM.

//TABELA CARREGANDO DADOS DOS CLIENTES NA GUIA DEPENDENTES 
    public final void tabela_cliente_dependente(String Sql) {
        spTableCliente_dependente.setVerticalScrollBar(new ScrollBar());
        spTableCliente_dependente.getVerticalScrollBar().setBackground(Color.WHITE);
        spTableCliente_dependente.getViewport().setBackground(Color.WHITE);

        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTableCliente_dependente.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"ID", "Nome", "RG", "CPF", "DT NASCIMENTO", "TELEFONE", "ESTADO CIVIL", "STATUS"};

        con.getConectar();
        con.executarSql(Sql);
        //Inserir dados na tabela
        try {
            con.getResultSet().first();
            do {
                dados.add(new Object[]{con.getResultSet().getInt("id"), con.getResultSet().getString("nome_Completo"),
                    con.getResultSet().getString("rg"), con.getResultSet().getString("cpf"),
                    con.getResultSet().getString("data_Nascimento"), con.getResultSet().getString("telefone"), con.getResultSet().getString("estado_Civil"),
                    con.getResultSet().getString("status_Cliente")
                });
            } while (con.getResultSet().next());
        } catch (SQLException e) {

        }

        TabelaUniversal tabela = new TabelaUniversal(dados, colunas);

        table_cliente_dependente.setModel(tabela);
        table_cliente_dependente.getColumnModel().getColumn(0).setPreferredWidth(30);
        table_cliente_dependente.getColumnModel().getColumn(0).setResizable(false);
        table_cliente_dependente.getColumnModel().getColumn(1).setPreferredWidth(190);
        table_cliente_dependente.getColumnModel().getColumn(1).setResizable(false);
        table_cliente_dependente.getColumnModel().getColumn(2).setPreferredWidth(120);
        table_cliente_dependente.getColumnModel().getColumn(2).setResizable(false);
        table_cliente_dependente.getColumnModel().getColumn(3).setPreferredWidth(115);
        table_cliente_dependente.getColumnModel().getColumn(3).setResizable(false);
        table_cliente_dependente.getColumnModel().getColumn(4).setPreferredWidth(140);
        table_cliente_dependente.getColumnModel().getColumn(4).setResizable(false);
        table_cliente_dependente.getColumnModel().getColumn(5).setPreferredWidth(168);
        table_cliente_dependente.getColumnModel().getColumn(5).setResizable(false);
        table_cliente_dependente.getColumnModel().getColumn(6).setPreferredWidth(115);
        table_cliente_dependente.getColumnModel().getColumn(6).setResizable(false);
        table_cliente_dependente.getColumnModel().getColumn(7).setPreferredWidth(140);
        table_cliente_dependente.getColumnModel().getColumn(7).setResizable(false);
        table_cliente_dependente.getTableHeader().setReorderingAllowed(false);
        table_cliente_dependente.setAutoResizeMode(table_cliente_dependente.AUTO_RESIZE_OFF);
        table_cliente_dependente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }//FIM.

//    TABELA CARREGANDO DADOS DEPENDENTES NA GUIA DEPENDENTES
    public final void tabela_dependente() {
        spTableDependentes.setVerticalScrollBar(new ScrollBar());
        spTableDependentes.getVerticalScrollBar().setBackground(Color.WHITE);
        spTableDependentes.getViewport().setBackground(Color.WHITE);

        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTableDependentes.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

        listDependentes = controllerDependetes.returnListDependentesController();
        DefaultTableModel table = (DefaultTableModel) spTableDependente.getModel();
        table.setNumRows(0);
        //Inserir dados na tabela
        int cont = listDependentes.size();
        for (int i = 0; i < cont; i++) {
            table.addRow(new Object[]{
                listDependentes.get(i).getId(),
                listDependentes.get(i).getNome_Completo(),
                listDependentes.get(i).getRg(),
                listDependentes.get(i).getCpf(),
                listDependentes.get(i).getIdade_cliente(),
                listDependentes.get(i).getGenero_cliente(),
                listDependentes.get(i).getDependencia_cliente(),
                listDependentes.get(i).getDt_criacao()
            });
        }
    }//FIM.

    public final void tabela_dependente(List<Dependentes> listaDependentes) {
        spTableDependentes.setVerticalScrollBar(new ScrollBar());
        spTableDependentes.getVerticalScrollBar().setBackground(Color.WHITE);
        spTableDependentes.getViewport().setBackground(Color.WHITE);

        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTableDependentes.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

        // CORRETO: usar o nome da JTable, não do JScrollPane!
        DefaultTableModel table = (DefaultTableModel) spTableDependente.getModel();
        table.setNumRows(0);

        for (Dependentes d : listaDependentes) {
            table.addRow(new Object[]{
                d.getId(),
                d.getNome_Completo(),
                d.getRg(),
                d.getCpf(),
                d.getIdade_cliente(),
                d.getGenero_cliente(),
                d.getDependencia_cliente(),
                d.getDt_criacao()
            });
        }
    }

    public void buscarEPreencherTabela(String filtro) {
        DependenteDao dao = new DependenteDao();
        Dependentes filtroDependente = new Dependentes();
        filtroDependente.setPesquisar(filtro);

        List<Dependentes> listaFiltrada = dao.buscarDependentesPorDependente(filtroDependente);

        if (listaFiltrada.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum dependente encontrado.");
        }

        tabela_dependente(listaFiltrada);
    }

    //TABELA CARREGANDO DADOS DOS CLIENTES NA GUIA SOCIO_ECONOMICO
    public final void tabela_cliente_socio_economico(String Sql) {
        spTableCliente_Socio_Economico.setVerticalScrollBar(new ScrollBar());
        spTableCliente_Socio_Economico.getVerticalScrollBar().setBackground(Color.WHITE);
        spTableCliente_Socio_Economico.getViewport().setBackground(Color.WHITE);

        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTableCliente_Socio_Economico.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"ID", "Nome", "RG", "CPF", "DT NASCIMENTO", "TELEFONE", "ESTADO CIVIL", "STATUS"};

        con.getConectar();
        con.executarSql(Sql);
        //Inserir dados na tabela
        try {
            con.getResultSet().first();
            do {
                dados.add(new Object[]{con.getResultSet().getInt("id"), con.getResultSet().getString("nome_Completo"),
                    con.getResultSet().getString("rg"), con.getResultSet().getString("cpf"),
                    con.getResultSet().getString("data_Nascimento"), con.getResultSet().getString("telefone"), con.getResultSet().getString("estado_Civil"),
                    con.getResultSet().getString("status_Cliente")});
            } while (con.getResultSet().next());
        } catch (SQLException e) {

        }

        TabelaUniversal tabela = new TabelaUniversal(dados, colunas);

        table_cliente_socio_Economico.setModel(tabela);
        table_cliente_socio_Economico.getColumnModel().getColumn(0).setPreferredWidth(30);
        table_cliente_socio_Economico.getColumnModel().getColumn(0).setResizable(false);
        table_cliente_socio_Economico.getColumnModel().getColumn(1).setPreferredWidth(190);
        table_cliente_socio_Economico.getColumnModel().getColumn(1).setResizable(false);
        table_cliente_socio_Economico.getColumnModel().getColumn(2).setPreferredWidth(120);
        table_cliente_socio_Economico.getColumnModel().getColumn(2).setResizable(false);
        table_cliente_socio_Economico.getColumnModel().getColumn(3).setPreferredWidth(115);
        table_cliente_socio_Economico.getColumnModel().getColumn(3).setResizable(false);
        table_cliente_socio_Economico.getColumnModel().getColumn(4).setPreferredWidth(140);
        table_cliente_socio_Economico.getColumnModel().getColumn(4).setResizable(false);
        table_cliente_socio_Economico.getColumnModel().getColumn(5).setPreferredWidth(168);
        table_cliente_socio_Economico.getColumnModel().getColumn(5).setResizable(false);
        table_cliente_socio_Economico.getColumnModel().getColumn(6).setPreferredWidth(115);
        table_cliente_socio_Economico.getColumnModel().getColumn(6).setResizable(false);
        table_cliente_socio_Economico.getColumnModel().getColumn(7).setPreferredWidth(140);
        table_cliente_socio_Economico.getColumnModel().getColumn(7).setResizable(false);
        table_cliente_socio_Economico.getTableHeader().setReorderingAllowed(false);
        table_cliente_socio_Economico.setAutoResizeMode(table_cliente_socio_Economico.AUTO_RESIZE_OFF);
        table_cliente_socio_Economico.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }//FIM.

    //TABELA CARREGANDO DADOS DOS CLIENTES NA GUIA SOCIO_ECONOMICO_SAUDE
    public final void tabela_cliente_socio_economico_Saude(String Sql) {
        spTableCliente_Socio_EconomicoSaude.setVerticalScrollBar(new ScrollBar());
        spTableCliente_Socio_EconomicoSaude.getVerticalScrollBar().setBackground(Color.WHITE);
        spTableCliente_Socio_EconomicoSaude.getViewport().setBackground(Color.WHITE);

        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        spTableCliente_Socio_EconomicoSaude.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);

        //Inserir dados na tabela
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"ID", "Nome", "CPF", "RG", "IDADE", "SEXO"};

        con.getConectar();
        con.executarSql(Sql);
        //Inserir dados na tabela
        try {
            con.getResultSet().first();
            do {
                dados.add(new Object[]{con.getResultSet().getInt("c.id"),
                    con.getResultSet().getString("c.nome_Completo"),
                    con.getResultSet().getString("c.cpf"),
                    con.getResultSet().getString("c.rg"),
                    con.getResultSet().getString("c.idade_cliente"),
                    con.getResultSet().getString("c.genero_cliente")});
            } while (con.getResultSet().next());
        } catch (SQLException e) {

        }

        TabelaUniversal tabela = new TabelaUniversal(dados, colunas);

        table_cliente_socio_EconomicoSaude.setModel(tabela);
        table_cliente_socio_EconomicoSaude.getColumnModel().getColumn(0).setPreferredWidth(30);
        table_cliente_socio_EconomicoSaude.getColumnModel().getColumn(0).setResizable(false);
        table_cliente_socio_EconomicoSaude.getColumnModel().getColumn(1).setPreferredWidth(190);
        table_cliente_socio_EconomicoSaude.getColumnModel().getColumn(1).setResizable(false);
        table_cliente_socio_EconomicoSaude.getColumnModel().getColumn(2).setPreferredWidth(120);
        table_cliente_socio_EconomicoSaude.getColumnModel().getColumn(2).setResizable(false);
        table_cliente_socio_EconomicoSaude.getColumnModel().getColumn(3).setPreferredWidth(115);
        table_cliente_socio_EconomicoSaude.getColumnModel().getColumn(3).setResizable(false);
        table_cliente_socio_EconomicoSaude.getColumnModel().getColumn(4).setPreferredWidth(140);
        table_cliente_socio_EconomicoSaude.getColumnModel().getColumn(4).setResizable(false);
        table_cliente_socio_EconomicoSaude.getColumnModel().getColumn(5).setPreferredWidth(420);
        table_cliente_socio_EconomicoSaude.getColumnModel().getColumn(5).setResizable(false);
        table_cliente_socio_EconomicoSaude.getTableHeader().setReorderingAllowed(false);
        table_cliente_socio_EconomicoSaude.setAutoResizeMode(table_cliente_socio_EconomicoSaude.AUTO_RESIZE_OFF);
        table_cliente_socio_EconomicoSaude.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }//FIM.

    //METODO PARA LIMPAR CAMPOS DA TELA CLIENTE
    public void limparCampos() {
        txtnomeCompleto.setText("");
        txtnomeSocial.setText("");
        txtnomeMae.setText("");
        txtDTNascimento.setText("");
        txtFone.setText("");
        txtRg.setText("");
        txtNis.setText("");
        txtcpf.setText("");
        txtcep.setText("");
        txtbairro.setText("");
        txtrua.setText("");
        txtnumerocasa.setText("");
        txtreferencia.setText("");
        txtidade.setText("");
        txtnaturalidade.setText("");
        txtCidade.setText("");
        txttempomoradia.setText("");
        txtNomeClientePesquisar.setText("");
    }//FIM.

    //METODO PARA LIMPAR CAMPOS DA TELA DEPENDENTES
    public void limparCamposDependentes() {
        txtNomeClienteTitular.setText("");
        txtnomeCompletoDependente.setText("");
        txtRgDependente.setText("");
        txtcpfDependente.setText("");
        txtIdateDependente.setText("");
        txt_pesquisar_dependente.setText("");
    }//FIM.

    //METODO PARA LIMPAR CAMPOS DA TELA SOCIO_ECONOMICO
    public void limparCamposSocioEconomico() {
        txtNomeClienteTitularSocioEconomico.setText("");
        txtProfissaoResponsavel.setText("");
        txtPessoasTrabalhando.setText("");
        txtNomeClienteSocioEconomico.setText("");
    }//FIM.

    //METODO PARA LIMPAR CAMPOS DA TELA SOCIO_ECONOMICO_SAUDE
    public void limparCampoSocioEconomicoSaude() {
        txtoutrasDoencas.setText("");
        txtjustificativaDeficiencia.setText("");
        txtObservação.setText("");
        txtNomeClienteTitularSocioEconomicoSaude.setText("");
    }//FIM.

    //METODO PARA DESABILITAR CAMPOS DA TELA CLIENTE
    public final void desabilitarCampos() {
        txtnomeCompleto.setEnabled(false);
        txtnomeSocial.setEnabled(false);
        txtnomeMae.setEnabled(false);
        comboCor.setEnabled(false);
        txtFone.setEnabled(false);
        txtDTNascimento.setEnabled(false);
        comboSexo.setEnabled(false);
        comboEst_Civil.setEnabled(false);
        txtRg.setEnabled(false);
        txtcpf.setEnabled(false);
        txtNis.setEnabled(false);
        comboStatus.setEnabled(false);
        txtcep.setEnabled(false);
        txtbairro.setEnabled(false);
        txtrua.setEnabled(false);
        txtnumerocasa.setEnabled(false);
        txtreferencia.setEnabled(false);
        txtidade.setEnabled(false);
        combonacionalidade.setEnabled(false);
        txtnaturalidade.setEnabled(false);
        txtCidade.setEnabled(false);
        txttempomoradia.setEnabled(false);
    }//FIM.

    //METODO PARA HABILITAR CAMPOS DA TELA CLIENTE
    public void habilitarCampos() {
        txtnomeCompleto.setEnabled(true);
        txtnomeSocial.setEnabled(true);
        txtnomeMae.setEnabled(true);
        comboCor.setEnabled(true);
        txtFone.setEnabled(true);
        txtDTNascimento.setEnabled(true);
        comboSexo.setEnabled(true);
        comboEst_Civil.setEnabled(true);
        txtRg.setEnabled(true);
        txtcpf.setEnabled(true);
        txtNis.setEnabled(true);
        comboStatus.setEnabled(true);
        txtcep.setEnabled(true);
        txtbairro.setEnabled(true);
        txtrua.setEnabled(true);
        txtnumerocasa.setEnabled(true);
        txtreferencia.setEnabled(true);
        txtidade.setEnabled(true);
        combonacionalidade.setEnabled(true);
        txtnaturalidade.setEnabled(true);
        txtCidade.setEnabled(true);
        txttempomoradia.setEnabled(true);
    }//FIM.

    //METODO PARA HABILITAR CAMPOS DA TELA CLIENTE
    public void habilitarCamposDependentes() {
        txtnomeCompletoDependente.setEnabled(true);
        txtRgDependente.setEnabled(true);
        txtcpfDependente.setEnabled(true);
        txtIdateDependente.setEnabled(true);
        comboSexoDependente.setEnabled(true);
        comboParentesco.setEnabled(true);
    }

    //METODO PARA HABILITAR CAMPOS DA TELA CLIENTE
    public void DesabilitarCamposDependentes() {
        txtnomeCompletoDependente.setEnabled(false);
        txtRgDependente.setEnabled(false);
        txtcpfDependente.setEnabled(false);
        txtIdateDependente.setEnabled(false);
        comboSexoDependente.setEnabled(false);
        comboParentesco.setEnabled(false);
    }

    //METODO PARA DESABILITAR BOTÃO DA TELA CLIENTE
    public final void desabilitarBotao() {
        btnNovo.setEnabled(true);
        btnAlterar.setEnabled(false);
        btnSalvar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnExcluir.setEnabled(false);
    }//FIM.

    //METODO PARA DESABILITAR BOTÃO DA TELA DEPENDENTE
    public final void desabilitarBotaoDependente() {
        btnSalvarDependente.setEnabled(false);
        btnAlterarDependente.setEnabled(false);
        btnCancelarDep.setEnabled(false);
        btnRemoverDependente.setEnabled(false);
    }//FIM.

    //METODO PARA HABILITAR BOTÃO DA TELA CLIENTE
    public void habilitarBotao() {
        btnNovo.setEnabled(false);
        btnSalvar.setEnabled(true);
        btnAlterar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }//FIM.

    //METODO PARA HABILITAR BOTÃO DA TELA CLIENTE
    public void habilitarBotaoDependente() {
        btnSalvarDependente.setEnabled(true);
        btnCancelarDep.setEnabled(true);
    }//FIM.

    //METODO PARA VALIDAR CAMPOS NA TELA CLIENTE
    public void validarCamposTitular() {
        if (txtnomeCompleto.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Campo Nome é Obrigatório!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            txtnomeCompleto.requestFocus();
            return;
        }

        if (txtFone.getText().equals("(  )      -    ")) {
            JOptionPane.showMessageDialog(this, "Campo Telefone é Obrigatório!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            txtFone.requestFocus();
            return;
        }

        if (txtcpf.getText().equals("   .   .   -  ")) {
            JOptionPane.showMessageDialog(this, "Campo CPF é Obrigatório!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            txtcpf.requestFocus();
            return;
        }

        if (txtDTNascimento.getText().equals("  /  /    ")) {
            JOptionPane.showMessageDialog(this, "Campo Data de Nascimento é Obrigatório!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            txtDTNascimento.requestFocus();
            return;
        }

        if (txtidade.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Campo Idade é Obrigatório!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            txtidade.requestFocus();
            return;
        }
    }//FIM.

    //METODO PARA VALIDAR CAMPOS NA TELA DEPENDENTES
    public void validarCamposDependentes() {
        if (txtNomeClienteTitular.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Selecione um Titular!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (txtnomeCompletoDependente.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Campo Nome é Obrigatório!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            txtnomeCompletoDependente.requestFocus();
            return;
        }

        if (txtIdateDependente.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Campo Idade é Obrigatório!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            txtIdateDependente.requestFocus();
            return;
        }

    }//FIM.

    //METODO PARA VALIDAR CAMPOS NA TELA SOCIO_ECONOMICO
    public void validarCamposSocioEconomico() {
        if (txtNomeClienteTitularSocioEconomico.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Selecione um Titular!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (txtPessoasTrabalhando.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Informe a quantidade de pessoas trabalham!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            txtPessoasTrabalhando.requestFocus();
            return;
        }
    }//FIM.

    //METODO PARA VALIDAR CAMPOS NA TELA SOCIO_ECONOMICO_SAUDE
    public void validarCamposSocioEconomicoSaude() {
        if (txtNomeClienteTitularSocioEconomicoSaude.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Selecione um Titular!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            txtNomeClienteTitularSocioEconomicoSaude.requestFocus();
            return;
        }
    }//FIM.

    //METODO SALVAR CLIENTES
    public void saveClientes() {
        // ====== 1. Validação de IDADE ======
        String idadeTexto = txtidade.getText().trim();
        if (idadeTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha o campo Idade!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            txtidade.requestFocus();
            return;
        }
        try {
            cliIdade = Integer.parseInt(idadeTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Digite apenas números no campo Idade!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            txtidade.requestFocus();
            return;
        }

        // ====== 2. Campos de texto ======
        String rua = txtrua.getText().trim();
        String bairro = txtbairro.getText().trim();
        String cidade = txtCidade.getText().trim();
        String cpf = txtcpf.getText().trim();

        // ====== 3. Validação de CPF ======
        if (cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha o campo CPF!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            txtcpf.requestFocus();
            return;
        }

        CPF pf = new CPF(cpf);

        // ====== 4. Validação de NIS (se for numérico) ======
        String nisTexto = txtNis.getText().trim();
        if (!nisTexto.isEmpty()) {
            // Permite apenas dígitos, pontos e traço
            if (!nisTexto.matches("[0-9.\\-]+")) {
                JOptionPane.showMessageDialog(this, "O NIS deve conter apenas números, pontos e traço!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                txtNis.requestFocus();
                return;
            }
        }

        // ====== 5. Validação de Número da casa ======
        String numeroCasaTexto = txtnumerocasa.getText().trim();
        if (!numeroCasaTexto.isEmpty()) {
            try {
                Integer.parseInt(numeroCasaTexto);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Digite apenas números no campo Número da Casa!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                txtnumerocasa.requestFocus();
                return;
            }
        }

        // ====== 6. Continuação da lógica original ======
        if (controllerTitular.controlVerificarCPFCadastrado(cpf, id) == false) {
            if (!bairro.isEmpty()) {
                if (!rua.isEmpty()) {
                    if (!cidade.isEmpty()) {
                        if (cliIdade > 17) {
                            if (pf.isCPF()) {
                                titular.setNome_Completo(txtnomeCompleto.getText());
                                titular.setNome_Social(txtnomeSocial.getText());
                                titular.setNome_Mae(txtnomeMae.getText());
                                titular.setCor_cliente((String) this.comboCor.getSelectedItem());
                                titular.setTelefone(txtFone.getText());
                                titular.setData_Nascimento(txtDTNascimento.getText());
                                titular.setGenero_cliente((String) this.comboSexo.getSelectedItem());
                                titular.setEstado_Civil((String) this.comboEst_Civil.getSelectedItem());
                                titular.setRg(txtRg.getText());
                                titular.setCpf(cpf);
                                titular.setNis(nisTexto);
                                titular.setStatus_Cliente((String) this.comboStatus.getSelectedItem());
                                titular.setIdade_cliente(cliIdade);

                                endereco.setCep(txtcep.getText());
                                endereco.setBairro(bairro);
                                endereco.setRua(rua);
                                endereco.setNumero(numeroCasaTexto);
                                endereco.setReferencia(txtreferencia.getText());
                                endereco.setNacionalidade((String) this.combonacionalidade.getSelectedItem());
                                endereco.setNaturalidade(txtnaturalidade.getText());
                                endereco.setCidade(cidade);
                                endereco.setTempoDeMoradia_cliente(txttempomoradia.getText());

                                boolean resultado = controllerTitular.controlSaveClientes(titular, endereco);
                                if (resultado) {
                                    ClientesDao clientesDao = new ClientesDao();
                                    clientesDao.inserirClientesCadastrados();
                                    JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
                                    atualizarTabelas();
                                    limparCampos();
                                    desabilitarCampos();
                                    desabilitarBotao();
                                } else {
                                    JOptionPane.showMessageDialog(this, "Erro ao Salvar!");
                                    limparCampos();
                                    desabilitarCampos();
                                    desabilitarBotao();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "CPF inválido!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Cliente deve ter pelo menos 18 anos!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                            txtidade.requestFocus();
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Preencha o campo Cidade!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                        txtCidade.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Preencha o campo Rua!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                    txtrua.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Preencha o campo Bairro!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                txtbairro.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cliente com o mesmo CPF já cadastrado!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
            txtcpf.requestFocus();
        }
    }

// Método para atualizar todas as tabelas (para evitar repetição de código)
    private void atualizarTabelas() {
        tabela_cliente("select c.id, c.nome_Completo, c.nome_Social, c.cor_cliente, c.nome_Mae, c.telefone, \n"
                + "		c.data_Nascimento, c.idade_cliente, c.genero_cliente, c.estado_Civil, c.rg, c.cpf, c.nis,c.status_Cliente, c.registration_date,\n"
                + "		e.id, e.cep, e.bairro, e.rua, e.numero, e.referencia, e.nacionalidade, e.naturalidade, e.cidade, e.tempoDeMoradia_cliente\n"
                + "	from tb_titular c inner join tb_endereco e on c.id_endereco = e.id order by nome_Completo");
        tabela_cliente_dependente("select c.id, c.nome_Completo, c.nome_Social, c.cor_cliente, c.nome_Mae, c.telefone, \n"
                + "		c.data_Nascimento, c.idade_cliente, c.genero_cliente, c.estado_Civil, c.rg, c.cpf, c.status_Cliente, \n"
                + "		e.id, e.cep, e.bairro, e.rua, e.numero, e.referencia, e.nacionalidade, e.naturalidade, e.cidade, e.tempoDeMoradia_cliente\n"
                + "	from tb_titular c inner join tb_endereco e on c.id_endereco = e.id order by nome_Completo");

        tabela_cliente_socio_economico("select c.id, c.nome_Completo, c.nome_Social, c.cor_cliente, c.nome_Mae, c.telefone, \n"
                + "		c.data_Nascimento, c.idade_cliente, c.genero_cliente, c.estado_Civil, c.rg, c.cpf, c.status_Cliente, \n"
                + "		e.id, e.cep, e.bairro, e.rua, e.numero, e.referencia, e.nacionalidade, e.naturalidade, e.cidade, e.tempoDeMoradia_cliente\n"
                + "	from tb_titular c inner join tb_endereco e on c.id_endereco = e.id order by nome_Completo");

        tabela_cliente_socio_economico_Saude("select c.id, c.nome_Completo, c.nome_Social, c.cor_cliente, c.nome_Mae, c.telefone, \n"
                + "		c.data_Nascimento, c.idade_cliente, c.genero_cliente, c.estado_Civil, c.rg, c.cpf, c.status_Cliente, \n"
                + "		e.id, e.cep, e.bairro, e.rua, e.numero, e.referencia, e.nacionalidade, e.naturalidade, e.cidade, e.tempoDeMoradia_cliente\n"
                + "	from tb_titular c inner join tb_endereco e on c.id_endereco = e.id order by nome_Completo");

        tabela_dependente();
    }

    //METODO UPDATE CLIENTES
    public void updateClientes() {

        String nomeCompleto, cpf, telefone, rua, bairro, cidade;

        nomeCompleto = txtnomeCompleto.getText();
        cpf = txtcpf.getText();
        telefone = txtFone.getText();
        rua = txtrua.getText();
        bairro = txtbairro.getText();
        cidade = txtCidade.getText();

        cliIdade = Integer.parseInt(txtidade.getText());
        if (controllerTitular.controlVerificarCPFCadastrado(txtcpf.getText(), id) == false) {//PRIMEIRO IF DE VERIFICAÇÃO DE CPF CADASTRADO
            if (!nomeCompleto.isEmpty()) {//PRIMEIRO IF "nomeCompleto"
                if (!cpf.isEmpty()) {//SEGUNDO IF "cpf"
                    if (!telefone.isEmpty()) {//TERCEIRO IF "telefone"
                        if (!rua.isEmpty()) {//QUARTO IF "rua"
                            if (!bairro.isEmpty()) {//QUINTO IF "bairro"
                                if (!cidade.isEmpty()) {//SEXTO IF "cidade"
                                    titular.setId(this.id);
                                    titular.setNome_Completo(nomeCompleto);
                                    titular.setNome_Social(txtnomeSocial.getText());
                                    titular.setNome_Mae(txtnomeMae.getText());
                                    titular.setCor_cliente((String) this.comboCor.getSelectedItem());
                                    titular.setTelefone(telefone);
                                    titular.setData_Nascimento(txtDTNascimento.getText());
                                    titular.setGenero_cliente((String) this.comboSexo.getSelectedItem());
                                    titular.setEstado_Civil((String) this.comboEst_Civil.getSelectedItem());
                                    titular.setRg(txtRg.getText());
                                    titular.setCpf(cpf);
                                    titular.setNis(txtNis.getText());
                                    titular.setStatus_Cliente((String) this.comboStatus.getSelectedItem());
                                    titular.setIdade_cliente(cliIdade);

                                    endereco.setCep(txtcep.getText());
                                    endereco.setBairro(bairro);
                                    endereco.setRua(rua);
                                    endereco.setNumero(txtnumerocasa.getText());
                                    endereco.setReferencia(txtreferencia.getText());
                                    endereco.setNacionalidade((String) this.combonacionalidade.getSelectedItem());
                                    endereco.setNaturalidade(txtnaturalidade.getText());
                                    endereco.setCidade(cidade);
                                    endereco.setTempoDeMoradia_cliente(txttempomoradia.getText());

                                    boolean resultado = controllerTitular.controlUpdateClientes(titular, endereco);
                                    if (resultado == true) {//CONDIÇÃO PARA MANDAR O RESULTADO PARA O BACK-END, PARA SALVAR
                                        JOptionPane.showMessageDialog(this, "Alterado com sucesso!");
                                        tabela_cliente("select c.id, c.nome_Completo, c.nome_Social, c.cor_cliente, c.nome_Mae, c.telefone, \n"
                                                + "		c.data_Nascimento, c.idade_cliente, c.genero_cliente, c.estado_Civil, c.rg, c.cpf, c.nis,c.status_Cliente, c.registration_date, \n"
                                                + "		e.id, e.cep, e.bairro, e.rua, e.numero, e.referencia, e.nacionalidade, e.naturalidade, e.cidade, e.tempoDeMoradia_cliente\n"
                                                + "	from tb_titular c inner join tb_endereco e on c.id_endereco = e.id order by nome_Completo");
                                        tabela_cliente_dependente("select c.id, c.nome_Completo, c.nome_Social, c.cor_cliente, c.nome_Mae, c.telefone, \n"
                                                + "		c.data_Nascimento, c.idade_cliente, c.genero_cliente, c.estado_Civil, c.rg, c.cpf, c.status_Cliente, \n"
                                                + "		e.id, e.cep, e.bairro, e.rua, e.numero, e.referencia, e.nacionalidade, e.naturalidade, e.cidade, e.tempoDeMoradia_cliente\n"
                                                + "	from tb_titular c inner join tb_endereco e on c.id_endereco = e.id order by nome_Completo");
                                        tabela_cliente_socio_economico("select c.id, c.nome_Completo, c.nome_Social, c.cor_cliente, c.nome_Mae, c.telefone, \n"
                                                + "		c.data_Nascimento, c.idade_cliente, c.genero_cliente, c.estado_Civil, c.rg, c.cpf, c.status_Cliente, \n"
                                                + "		e.id, e.cep, e.bairro, e.rua, e.numero, e.referencia, e.nacionalidade, e.naturalidade, e.cidade, e.tempoDeMoradia_cliente\n"
                                                + "	from tb_titular c inner join tb_endereco e on c.id_endereco = e.id order by nome_Completo");
                                        tabela_cliente_socio_economico_Saude("select c.id, c.nome_Completo, c.nome_Social, c.cor_cliente, c.nome_Mae, c.telefone, \n"
                                                + "		c.data_Nascimento, c.idade_cliente, c.genero_cliente, c.estado_Civil, c.rg, c.cpf, c.status_Cliente, \n"
                                                + "		e.id, e.cep, e.bairro, e.rua, e.numero, e.referencia, e.nacionalidade, e.naturalidade, e.cidade, e.tempoDeMoradia_cliente\n"
                                                + "	from tb_titular c inner join tb_endereco e on c.id_endereco = e.id order by nome_Completo");
                                        limparCampos();
                                        desabilitarCampos();
                                        desabilitarBotao();
                                    } else {
                                        JOptionPane.showMessageDialog(this, "Erro ao Salvar!");
                                        limparCampos();
                                        desabilitarCampos();
                                        desabilitarBotao();
                                    }//FIM DA CONDIÇÃO PARA SALVAR.
                                } else {
                                    JOptionPane.showMessageDialog(this, "Preencha o campo Cidade!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                                    txtCidade.requestFocus();
                                }//FIM IF "Cidade".         
                            } else {
                                JOptionPane.showMessageDialog(this, "Preencha o campo Bairro!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                                txtbairro.requestFocus();
                            }//FIM IF "bairro". 
                        } else {
                            JOptionPane.showMessageDialog(this, "Preencha o campo Rua!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                            txtrua.requestFocus();
                        }//FIM IF "rua"
                    } else {
                        JOptionPane.showMessageDialog(this, "Preencha o campo Telefone!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                        txtFone.requestFocus();
                    }//FIM IF "Fone"
                } else {
                    JOptionPane.showMessageDialog(this, "Preencha o campo CPF!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                    txtcpf.requestFocus();
                }//FIM IF "cpf"
            } else {
                JOptionPane.showMessageDialog(this, "Preencha o campo Nome!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                txtnomeCompleto.requestFocus();
            }//FIM IF "nomeCompleto"
        } else {
            JOptionPane.showMessageDialog(null, "Cliente com o mesmo CPF já cadastrado!", "Mensagem", JOptionPane.PLAIN_MESSAGE);
            txtcpf.requestFocus();
        }//FIM IF "VERIFICAÇÃO DE CPF"
//FIM.
    }

    public void excluirTitulares() {
        desabilitarCampos();
        int linha = table_cliente.getSelectedRow();
        String tNome = (String) table_cliente.getValueAt(linha, 1);
        int codigo = (int) table_cliente.getValueAt(linha, 0);
        int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir esse TITULAR ? \n"
                + tNome + "?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.OK_OPTION) {
            boolean resultado = controllerTitular.controlDeleteFuncionarios(codigo);
            if (resultado == true) {
                JOptionPane.showMessageDialog(this, "TITULAR removido com sucesso!");
                tabela_cliente("select c.id, c.nome_Completo, c.nome_Social, c.cor_cliente, c.nome_Mae, c.telefone, \n"
                        + "		c.data_Nascimento, c.idade_cliente, c.genero_cliente, c.estado_Civil, c.rg, c.cpf, c.nis,c.status_Cliente, c.registration_date,\n"
                        + "		e.id, e.cep, e.bairro, e.rua, e.numero, e.referencia, e.nacionalidade, e.naturalidade, e.cidade, e.tempoDeMoradia_cliente\n"
                        + "	from tb_titular c inner join tb_endereco e on c.id_endereco = e.id order by nome_Completo");
                limparCampos();
                desabilitarCampos();
                desabilitarBotao();
                btnNovo.setEnabled(true);
                atualizarTabelas();
            }
        }
    }

    //METODO SALVAR DEPENDENTES
    public void saveDependentes() {
        int cliIdadedep;
        String nomeDependente;
        nomeDependente = txtnomeCompletoDependente.getText();

        cliIdadedep = Integer.parseInt(txtIdateDependente.getText());

        if (cliIdadedep >= 5 && cliIdadedep <= 17) {
            if (!nomeDependente.isEmpty()) {
                dependentes.setId_titular(id);
                dependentes.setNome_Completo(nomeDependente);
                dependentes.setRg(txtRgDependente.getText());
                dependentes.setCpf(txtcpfDependente.getText());
                dependentes.setIdade_cliente(cliIdadedep);
                dependentes.setGenero_cliente((String) comboSexoDependente.getSelectedItem());
                dependentes.setDependencia_cliente((String) comboParentesco.getSelectedItem());
                boolean resultado = controllerDependetes.controlSaveDependentes(dependentes);
                if (resultado == true) {//CONDIÇÃO PARA MANDAR O RESULTADO PARA O BACK-END, PARA SALVAR
                    ClientesDao clientesDao = new ClientesDao();
                    clientesDao.inserirClientesCadastrados();
                    JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
                    limparCamposDependentes();
                    tabela_dependente(listDependentes);
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao Salvar!");
                }//FIM DA CONDIÇÃO PARA SALVAR.
            } else {
                JOptionPane.showMessageDialog(this, "Preencha o Nome do Dependente!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                txtnomeCompletoDependente.requestFocus();
            }//FIM IF "Idade".
        } else {
            JOptionPane.showMessageDialog(this, "Dependente com 18 anos não precisa ser cadastrado no sistema com dependente!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            txtIdateDependente.requestFocus();
        }//FIM IF "Idade".  
    }//FIM.

    public void updateDependentes() {
        dependentes.setId(id_dependente);
        dependentes.setId_titular(id_titular);
        dependentes.setNome_Completo(txtnomeCompletoDependente.getText());
        dependentes.setRg(txtRgDependente.getText());
        dependentes.setCpf(txtcpfDependente.getText());
        dependentes.setIdade_cliente(Integer.parseInt(txtIdateDependente.getText()));
        dependentes.setGenero_cliente((String) comboSexoDependente.getSelectedItem());
        dependentes.setDependencia_cliente((String) comboParentesco.getSelectedItem());
        controllerDependetes.controlUpdateDependente(dependentes);
        limparCamposDependentes();
        DesabilitarCamposDependentes();
        tabela_dependente(listDependentes);
    }

    public void excluirDependente() {
        desabilitarCampos();
        int linha = spTableDependente.getSelectedRow();
        String tNome = (String) spTableDependente.getValueAt(linha, 1);
        int codigo = (int) spTableDependente.getValueAt(linha, 0);
        int opcao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir esse DEPENDENTE ? \n"
                + tNome + "?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (opcao == JOptionPane.OK_OPTION) {
            boolean resultado = controllerDependetes.controlDeleteDependentes(codigo);
            if (resultado == true) {
                JOptionPane.showMessageDialog(this, "DEPENDENTE removido com sucesso!");

                limparCampos();
                desabilitarCampos();
                desabilitarBotao();
                btnNovo.setEnabled(true);
                atualizarTabelas();
            }
        }
    }

    //METODO SALVAR SOCIO_ECONOMICO
    public void saveSocioEconomico() {
        socioEconomico.setIId_titular(id);
        socioEconomico.setEscolariedade((String) this.comboEscolariedade.getSelectedItem());
        socioEconomico.setRenda_mensal_familia((String) this.comboRenda.getSelectedItem());
        socioEconomico.setProgramas_sociais((String) this.comboProgramaSocial.getSelectedItem());
        socioEconomico.setComposicao_familiar((String) this.comboComposicaoFamiliar.getSelectedItem());
        socioEconomico.setMoradia((String) this.comboMoradia.getSelectedItem());
        socioEconomico.setEstrutura_Moradia((String) this.comboEstruturaMoraria.getSelectedItem());
        socioEconomico.setQtdPessoas_Trabalhando(Integer.parseInt(txtPessoasTrabalhando.getText()));
        socioEconomico.setEmprego((String) this.comboEmprego.getSelectedItem());
        socioEconomico.setProfissao_Responsavel(txtProfissaoResponsavel.getText());
        socioEconomico.setAB_Agua((String) this.comboAgua.getSelectedItem());
        socioEconomico.setSN_basico((String) this.comboSaneamento.getSelectedItem());
        socioEconomico.setEnergia_eletrica((String) this.comboEnergia.getSelectedItem());
        socioEconomico.setLixo_Domiciliar((String) this.comboLixo.getSelectedItem());
        socioEconomico.setFrequenta_Centro((String) this.comboCentroConvivencia.getSelectedItem());

        boolean resultado = controllerSocioEconomico.controlSaveSocioEconomico(socioEconomico);
        if (resultado == true) {
            JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
            limparCamposSocioEconomico();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao Salvar!");
            limparCamposSocioEconomico();
        }
    }//FIM.

    //METODO SALVAR SOCIO_ECONOMICO_SAUDE
    public void saveSocioEconomicoSaude() {

        socioEconomicoSaude.setId_titular(id);
        socioEconomicoSaude.setDoenca((String) this.comboDoenca.getSelectedItem());
        socioEconomicoSaude.setOutras_Doenças(txtoutrasDoencas.getText());
        socioEconomicoSaude.setDeficiencia((String) this.comboDeficiencia.getSelectedItem());
        socioEconomicoSaude.setJustificar_deficiencia(txtjustificativaDeficiencia.getText());
        socioEconomicoSaude.setLaudo((String) this.comboLaudo.getSelectedItem());
        socioEconomicoSaude.setObservacao(txtObservação.getText());

        boolean resultado = controllerSocioEconomicoSaude.controlSaveSocioEconomico(socioEconomicoSaude);
        if (resultado == true) {//CONDIÇÃO PARA MANDAR O RESULTADO PARA O BACK-END, PARA SALVAR
            JOptionPane.showMessageDialog(this, "Salvo com sucesso!");
            limparCampoSocioEconomicoSaude();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao Salvar!");
            limparCampoSocioEconomicoSaude();
        }//FIM DA CONDIÇÃO PARA SALVAR.
    }//FIM.

    public void BuscarTitularPreenchendoTable() {
        titular.setPesquisar(txtNomeClientePesquisar.getText().trim());

        // Busca um titular específico e preenche os campos da tela
        titular = titularDao.buscarTitular(titular, endereco);
        id = titular.getId();
        txtnomeCompleto.setText(titular.getNome_Completo());
        txtnomeSocial.setText(titular.getNome_Social());
        comboCor.setSelectedItem(titular.getCor_cliente());
        txtnomeMae.setText(titular.getNome_Mae());
        txtFone.setText(titular.getTelefone());
        txtDTNascimento.setText(titular.getData_Nascimento());
        txtidade.setText(String.valueOf(titular.getIdade_cliente()));
        comboSexo.setSelectedItem(titular.getGenero_cliente());
        comboEst_Civil.setSelectedItem(titular.getEstado_Civil());
        txtRg.setText(titular.getRg());
        txtcpf.setText(titular.getCpf());
        txtNis.setText(titular.getNis());
        comboStatus.setSelectedItem(titular.getStatus_Cliente());
        txtcep.setText(endereco.getCep());
        txtbairro.setText(endereco.getBairro());
        txtrua.setText(endereco.getRua());
        txtnumerocasa.setText(endereco.getNumero());
        txtreferencia.setText(endereco.getReferencia());
        combonacionalidade.setSelectedItem(endereco.getNacionalidade());
        txtnaturalidade.setText(endereco.getNaturalidade());
        txtCidade.setText(endereco.getCidade());
        txttempomoradia.setText(endereco.getTempoDeMoradia_cliente());

        // Preenche a tabela somente com o titular pesquisado
        String sql = "SELECT c.id, c.nome_Completo, c.nome_Social, c.cor_cliente, c.nome_Mae, c.telefone, "
                + "c.data_Nascimento, c.idade_cliente, c.genero_cliente, c.estado_Civil, c.rg, c.cpf, c.nis, c.status_Cliente, c.registration_date, "
                + "e.id, e.cep, e.bairro, e.rua, e.numero, e.referencia, e.nacionalidade, e.naturalidade, e.cidade, e.tempoDeMoradia_cliente "
                + "FROM tb_titular c "
                + "INNER JOIN tb_endereco e ON c.id_endereco = e.id "
                + "WHERE c.nome_Completo LIKE '%" + titular.getPesquisar() + "%' "
                + "   OR c.cpf LIKE '%" + titular.getPesquisar() + "%'";

        tabela_cliente(sql);
    }

    public void BuscarTitularDependentePreenchendoTable() {
        titular.setPesquisar(txtPesquisarNomeTitular.getText());
        titular = titularDao.buscarTitular(titular, endereco);

        id = Integer.parseInt((String.valueOf(titular.getId())));
        txtNomeClienteTitular.setText((String.valueOf(titular.getNome_Completo())));

        tabela_cliente_dependente("select c.id, c.nome_Completo, c.nome_Social, c.cor_cliente, c.nome_Mae, c.telefone, \n"
                + "		c.data_Nascimento, c.idade_cliente, c.genero_cliente, c.estado_Civil, c.rg, c.cpf, c.status_Cliente, \n"
                + "		e.id, e.cep, e.bairro, e.rua, e.numero, e.referencia, e.nacionalidade, e.naturalidade, e.cidade, e.tempoDeMoradia_cliente\n"
                + "	from tb_titular c inner join tb_endereco e on c.id_endereco = e.id where nome_Completo like '%" + titular.getPesquisar() + "%' or c.cpf like '%" + titular.getPesquisar() + "'");
    }

    public void BuscarTitularSocioEconomicoPreenchendoTable() {
        titular.setPesquisar(txtNomeClienteSocioEconomico.getText());
        titular = titularDao.buscarTitular(titular, endereco);
        id = Integer.parseInt((String.valueOf(titular.getId())));
        txtNomeClienteTitularSocioEconomico.setText((String.valueOf(titular.getNome_Completo())));

        tabela_cliente_socio_economico("select c.id, c.nome_Completo, c.nome_Social, c.cor_cliente, c.nome_Mae, c.telefone, \n"
                + "		c.data_Nascimento, c.idade_cliente, c.genero_cliente, c.estado_Civil, c.rg, c.cpf, c.status_Cliente, \n"
                + "		e.id, e.cep, e.bairro, e.rua, e.numero, e.referencia, e.nacionalidade, e.naturalidade, e.cidade, e.tempoDeMoradia_cliente\n"
                + "	from tb_titular c inner join tb_endereco e on c.id_endereco = e.id where nome_Completo like '%" + titular.getPesquisar() + "%' or c.cpf like '%" + titular.getPesquisar() + "'");
    }

    public void BuscarTitularSocioEconomicoSaudePreenchendoTable() {
        titular.setPesquisar(txtNomeClienteSocioEconomicoEconomico.getText());
        titular = titularDao.buscarTitular(titular, endereco);
        id = Integer.parseInt((String.valueOf(titular.getId())));
        txtNomeClienteTitularSocioEconomicoSaude.setText((String.valueOf(titular.getNome_Completo())));

        tabela_cliente_socio_economico_Saude("select id_clientes, nome_cliente, cpf_cliente, rg_cliente, idade_cliente, genero from tb_clientes where nome_cliente like '%" + titular.getPesquisar() + "%'");
    }

    public void BuscarDependentePreenchendoTela() {
        // Define o filtro de pesquisa com base no campo de texto
        dependentes.setPesquisar(txt_pesquisar_dependente.getText().trim());

        // Chama o DAO para buscar os dados do dependente (retorna lista)
        List<Dependentes> lista = dependenteDao.buscarDependentesPorDependente(dependentes);

        // Verifica se encontrou resultados
        if (!lista.isEmpty()) {
            // Pega o primeiro da lista (ou adapte para percorrer todos)
            Dependentes d = lista.get(0);

            // Preenche os campos da tela com os dados do dependente
            id = d.getId();
            txtnomeCompletoDependente.setText(d.getNome_Completo());
            txtRgDependente.setText(d.getRg());
            txtIdateDependente.setText(String.valueOf(d.getIdade_cliente()));
            txtDTNascimento.setText(d.getData_Nascimento()); // certifique-se que tem este campo
            comboSexoDependente.setSelectedItem(d.getGenero_cliente());
            comboParentesco.setSelectedItem(d.getDependencia_cliente()); // ajustado para correto

            // Agora preenche a tabela usando a mesma consulta
            String sql = "SELECT d.id AS id_dependente, d.nome_dependente, d.rg AS rg_dependente, d.cpf AS cpf_dependente, "
                    + "d.idade AS idade_dependente, d.genero AS genero_dependente, d.dependencia_cliente, "
                    + "d.registration_date AS data_cadastro_dependente, d.registration_date_update AS data_atualizacao_dependente, "
                    + "t.id AS id_titular, t.nome_Completo AS nome_titular, t.cpf AS cpf_titular "
                    + "FROM tb_dependente d "
                    + "INNER JOIN tb_titular t ON d.id_titular = t.id "
                    + "WHERE t.nome_Completo LIKE '%" + dependentes.getPesquisar() + "%' "
                    + "   OR t.cpf LIKE '%" + dependentes.getPesquisar() + "%'";

            // Método que popula a JTable (você já deve ter um)
            tabela_dependente(lista);
            btnAlterarDependente.setEnabled(true);
            btnCancelarDep.setEnabled(true);
            btnRemoverDependente.setEnabled(true);
            btnNovoDependente.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum dependente encontrado.");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbnis = new javax.swing.JLabel();
        txtnomeMae = new javax.swing.JTextField();
        txtnomeCompleto = new javax.swing.JTextField();
        txtcpf = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        comboStatus = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtnomeSocial = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        comboSexo = new javax.swing.JComboBox<>();
        txtFone = new javax.swing.JFormattedTextField();
        jLabel38 = new javax.swing.JLabel();
        comboCor = new javax.swing.JComboBox<>();
        comboEst_Civil = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtcep = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        txtreferencia = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtrua = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtnumerocasa = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtbairro = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        combonacionalidade = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        txtnaturalidade = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txttempomoradia = new javax.swing.JTextField();
        spTableCliente = new javax.swing.JScrollPane();
        table_cliente = new view.com.raven.swing.Table2();
        txtNomeClientePesquisar = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txtCidade = new javax.swing.JTextField();
        txtDTNascimento = new javax.swing.JFormattedTextField();
        txtidade = new javax.swing.JTextField();
        btnBuscarCliente = new button.Button();
        btnNovo = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtNis = new javax.swing.JFormattedTextField();
        jLabel55 = new javax.swing.JLabel();
        txtRg = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtPesquisarNomeTitular = new javax.swing.JTextField();
        btnBuscarClienteDependente = new javax.swing.JButton();
        spTableCliente_dependente = new javax.swing.JScrollPane();
        table_cliente_dependente = new view.com.raven.swing.Table2();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtnomeCompletoDependente = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtRgDependente = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        txtcpfDependente = new javax.swing.JFormattedTextField();
        jLabel42 = new javax.swing.JLabel();
        comboParentesco = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        comboSexoDependente = new javax.swing.JComboBox<>();
        spTableDependentes = new javax.swing.JScrollPane();
        spTableDependente = new view.com.raven.swing.Table2();
        jLabel7 = new javax.swing.JLabel();
        txtNomeClienteTitular = new javax.swing.JTextField();
        txtIdateDependente = new javax.swing.JTextField();
        btnAlterarDependente = new javax.swing.JButton();
        btnSalvarDependente = new javax.swing.JButton();
        btnNovoDependente = new javax.swing.JButton();
        btnCancelarDep = new javax.swing.JButton();
        btnRemoverDependente = new javax.swing.JButton();
        txt_pesquisar_dependente = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        comboEscolariedade = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        comboRenda = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        comboEmprego = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        comboProgramaSocial = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        comboComposicaoFamiliar = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        comboMoradia = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        comboAgua = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        comboEnergia = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        comboSaneamento = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        comboLixo = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        comboEstruturaMoraria = new javax.swing.JComboBox<>();
        jLabel39 = new javax.swing.JLabel();
        txtPessoasTrabalhando = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txtProfissaoResponsavel = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        comboCentroConvivencia = new javax.swing.JComboBox<>();
        spTableCliente_Socio_Economico = new javax.swing.JScrollPane();
        table_cliente_socio_Economico = new view.com.raven.swing.Table2();
        jLabel8 = new javax.swing.JLabel();
        txtNomeClienteTitularSocioEconomico = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        txtNomeClienteSocioEconomico = new javax.swing.JTextField();
        btnBuscarClienteSocioEconomico = new javax.swing.JButton();
        btnSalvarClienteSocioEconomico = new button.Button();
        jPanel4 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        txtNomeClienteSocioEconomicoEconomico = new javax.swing.JTextField();
        btnBuscarClienteSocioEconomicoSaude = new javax.swing.JButton();
        spTableCliente_Socio_EconomicoSaude = new javax.swing.JScrollPane();
        table_cliente_socio_EconomicoSaude = new view.com.raven.swing.Table2();
        jLabel48 = new javax.swing.JLabel();
        txtNomeClienteTitularSocioEconomicoSaude = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        comboDoenca = new javax.swing.JComboBox<>();
        jLabel50 = new javax.swing.JLabel();
        txtoutrasDoencas = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        comboDeficiencia = new javax.swing.JComboBox<>();
        jLabel52 = new javax.swing.JLabel();
        txtjustificativaDeficiencia = new javax.swing.JTextField();
        comboLaudo = new javax.swing.JComboBox<>();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        txtObservação = new javax.swing.JTextField();
        btnSalvarSocioEconomicoSaude = new button.Button();

        setPreferredSize(new java.awt.Dimension(1095, 697));

        jTabbedPane1.setBackground(new java.awt.Color(0,0,0,50));
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0,0,0,0));
        jPanel1.setToolTipText("");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nome Completo: *");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nome da Mãe:");

        lbnis.setForeground(new java.awt.Color(255, 255, 255));
        lbnis.setText("NIS:");

        txtnomeMae.setDisabledTextColor(new java.awt.Color(187, 187, 187));

        txtnomeCompleto.setToolTipText("");
        txtnomeCompleto.setDisabledTextColor(new java.awt.Color(187, 187, 187));
        txtnomeCompleto.setName(""); // NOI18N

        try {
            txtcpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtcpf.setDisabledTextColor(new java.awt.Color(187, 187, 187));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("CPF:");

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Status:");

        comboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ATIVO", "INATIVO" }));
        comboStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboStatusActionPerformed(evt);
            }
        });

        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Data Nascimento:");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nome Social:");

        txtnomeSocial.setDisabledTextColor(new java.awt.Color(187, 187, 187));

        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Telefone:");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Sexo:");

        comboSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino" }));

        try {
            txtFone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtFone.setDisabledTextColor(new java.awt.Color(187, 187, 187));

        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Estado Civil:");

        comboCor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Branco", "Pardo", "Negro", "Indígenas" }));

        comboEst_Civil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Casado (a)", "Solteiro (a)", "União Estável", "Viúvo (a)", "Separado, divorciado" }));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("COR/ETNIA:");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("CEP:");

        try {
            txtcep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtcep.setDisabledTextColor(new java.awt.Color(187, 187, 187));

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Bairro:");

        txtreferencia.setDisabledTextColor(new java.awt.Color(187, 187, 187));

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Rua:");

        txtrua.setDisabledTextColor(new java.awt.Color(187, 187, 187));

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Nº:");

        txtnumerocasa.setDisabledTextColor(new java.awt.Color(187, 187, 187));

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Referência:");

        txtbairro.setDisabledTextColor(new java.awt.Color(187, 187, 187));

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Idade:");

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Nacionalidade:");

        combonacionalidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Brasil", "Argentina", "Bolívia", "Chile", "Colômbia", "Cuba", "Costa Rica", "Equador", "El Salvador", "Guatemala", "Haiti", "Honduras", "México", "Nicarágua", "Panamá", "Paraguai", "Peru", "República Dominicana", "Uruguai", "Venezuela" }));

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Naturalidade:");

        txtnaturalidade.setDisabledTextColor(new java.awt.Color(187, 187, 187));

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Tempo de moradia:");

        txttempomoradia.setDisabledTextColor(new java.awt.Color(187, 187, 187));

        spTableCliente.setBorder(null);

        table_cliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table_cliente.getTableHeader().setReorderingAllowed(false);
        table_cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_clienteMouseClicked(evt);
            }
        });
        spTableCliente.setViewportView(table_cliente);

        txtNomeClientePesquisar.setDisabledTextColor(new java.awt.Color(187, 187, 187));
        txtNomeClientePesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeClientePesquisarActionPerformed(evt);
            }
        });

        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Cidade:");

        txtCidade.setDisabledTextColor(new java.awt.Color(187, 187, 187));

        try {
            txtDTNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtDTNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDTNascimentoActionPerformed(evt);
            }
        });

        btnBuscarCliente.setText("Pesquisar");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        btnNovo.setText("NOVO");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnSalvar.setText("SALVAR");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnAlterar.setText("ALTERAR");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        try {
            txtNis.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.#####.##-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtNis.setDisabledTextColor(new java.awt.Color(187, 187, 187));
        txtNis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNisActionPerformed(evt);
            }
        });

        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("RG:");

        txtRg.setDisabledTextColor(new java.awt.Color(187, 187, 187));

        jButton1.setText("CONDIÇÕES DE RISCO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnExcluir.setText("EXCLUIR");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtnomeCompleto, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                            .addComponent(txtFone)
                                            .addComponent(txtcep))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtnomeSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(17, 17, 17)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(combonacionalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(txtbairro, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(comboEst_Civil, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(txtidade))
                                                        .addGap(64, 64, 64))))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtreferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                        .addGap(157, 157, 157)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(jLabel2))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addComponent(lbnis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtnomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(txtNis)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtnaturalidade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                            .addComponent(txtrua, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnExcluir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboCor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcpf))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txttempomoradia))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtnumerocasa, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(106, 106, 106))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDTNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(spTableCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 862, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(txtNomeClientePesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnomeCompleto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnomeSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtnomeMae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFone)
                            .addComponent(jLabel38)
                            .addComponent(comboEst_Civil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboSexo, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbnis, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDTNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtrua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtnumerocasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtreferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combonacionalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnaturalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttempomoradia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnSalvar)
                    .addComponent(btnAlterar)
                    .addComponent(btnCancelar)
                    .addComponent(jButton1)
                    .addComponent(btnExcluir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeClientePesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spTableCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cliente", jPanel1);

        jPanel2.setBackground(new java.awt.Color(0,0,0,0));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ADICIONAR DEPENDENTE");

        txtPesquisarNomeTitular.setDisabledTextColor(new java.awt.Color(187, 187, 187));
        txtPesquisarNomeTitular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesquisarNomeTitularActionPerformed(evt);
            }
        });

        btnBuscarClienteDependente.setText("PESQUISAR");
        btnBuscarClienteDependente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteDependenteActionPerformed(evt);
            }
        });

        table_cliente_dependente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "RG", "CPF", "DT NASCIMENTO", "TELEFONE", "ESTADO CIVIL", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_cliente_dependente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_cliente_dependenteMouseClicked(evt);
            }
        });
        spTableCliente_dependente.setViewportView(table_cliente_dependente);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nome Completo:");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nome Completo:");

        txtnomeCompletoDependente.setDisabledTextColor(new java.awt.Color(187, 187, 187));

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("RG:");

        try {
            txtRgDependente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#######-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtRgDependente.setDisabledTextColor(new java.awt.Color(187, 187, 187));

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("CPF:");

        try {
            txtcpfDependente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtcpfDependente.setDisabledTextColor(new java.awt.Color(187, 187, 187));

        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Dependencia:");

        comboParentesco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CÔNJUGE", "UNIÃO ESTÁVEL", "FILHO/ENTEADO", "IRMÃO", "AVÓ", "INCAPAZ", "AGREGADO" }));
        comboParentesco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboParentescoActionPerformed(evt);
            }
        });

        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Idade:");

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Sexo:");

        comboSexoDependente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino" }));

        spTableDependentes.setBorder(null);

        spTableDependente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "RG", "CPF", "IDADE", "GENERO", "DEPENDENTE", "DATA CADASTRO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTableDependente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                spTableDependenteMouseClicked(evt);
            }
        });
        spTableDependentes.setViewportView(spTableDependente);

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Titular:");

        txtNomeClienteTitular.setEditable(false);
        txtNomeClienteTitular.setDisabledTextColor(new java.awt.Color(187, 187, 187));

        btnAlterarDependente.setText("ALTERAR");
        btnAlterarDependente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarDependenteActionPerformed(evt);
            }
        });

        btnSalvarDependente.setText("SALVAR");
        btnSalvarDependente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarDependenteActionPerformed(evt);
            }
        });

        btnNovoDependente.setText("NOVO");
        btnNovoDependente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoDependenteActionPerformed(evt);
            }
        });

        btnCancelarDep.setText("CANCELAR");
        btnCancelarDep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarDepActionPerformed(evt);
            }
        });

        btnRemoverDependente.setText("EXCLUIR");
        btnRemoverDependente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverDependenteActionPerformed(evt);
            }
        });

        jButton2.setText("PESQUISAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisarNomeTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscarClienteDependente))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeClienteTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spTableCliente_dependente, javax.swing.GroupLayout.PREFERRED_SIZE, 1023, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(spTableDependentes, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(comboSexoDependente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel42)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(comboParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(txtnomeCompletoDependente, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtRgDependente, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtcpfDependente, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addGap(406, 406, 406)
                                            .addComponent(txt_pesquisar_dependente, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel43)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtIdateDependente, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2)))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(btnCancelarDep)
                            .addGap(18, 18, 18)
                            .addComponent(btnRemoverDependente)
                            .addGap(18, 18, 18)
                            .addComponent(btnAlterarDependente)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnSalvarDependente)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnNovoDependente))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarNomeTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarClienteDependente)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTableCliente_dependente, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeClienteTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtnomeCompletoDependente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtRgDependente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtcpfDependente)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIdateDependente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboSexoDependente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlterarDependente)
                    .addComponent(btnSalvarDependente)
                    .addComponent(btnNovoDependente)
                    .addComponent(btnCancelarDep)
                    .addComponent(btnRemoverDependente)
                    .addComponent(txt_pesquisar_dependente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(spTableDependentes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Dependentes", jPanel2);

        jPanel3.setBackground(new java.awt.Color(0,0,0,0));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Escolariedade:");

        comboEscolariedade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ANALFABETO", "ENSINO FUNDAMENTAL - COMPLETO", "ENSINO FUNDAMENTAL - INCOMPLETO", "ENSINO MÉDIO - COMPLETO", "ENSINO MÉDIO - INCOMPLETO", "NIVEL SUPERIOR" }));

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Renda Mensal:");

        comboRenda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0 A 89,00", "89,01 A 178,00", "178,01 A MEIO SALÁRIO", "RENDA DE 1 SALÁRIO", "RENDA DE 1 A 2  SALÁRIOS", "RENDA DE 2 A 3  SALÁRIOS" }));

        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Emprego:");

        comboEmprego.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CLT", "CONTRATO", "EMPREGADO", "DESEMPREGADO", "AUTÔNOMO", "APOSENTADO" }));

        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Programas Sociais:");

        comboProgramaSocial.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BOLSA FÁMILIA", "AUXÍLIO DOENÇA", "SEGURO DESEMPREGO", "CADASTRO ÚNICO DO GOVERNO FEDERAL", "BPC / IDOSO", "BPC / PCD", "APOSENTADO(A) / INSS", "PENSIONISTA / INSS", "NÃO PARTIPA DE NENHUM PROGRAMA SOCIAL" }));

        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Composição Familiar:");

        comboComposicaoFamiliar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MORA SOZINHO", "1 A 3 PESSOAS", "4 A 7 PESSOAS", "8 A 10 PESSOAS", "ACIMA DE 10 PESSOAS" }));

        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Moradia:");

        comboMoradia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PRÓPRIA", "ALUGADA", "CEDIDA", "OCUPADA" }));

        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Água Encanada ?");

        comboAgua.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SIM", "NÃO", "POÇO" }));

        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Energia Elétrica ?");

        comboEnergia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SIM", "NÃO" }));

        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Saneamento Básico ?");

        comboSaneamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SIM", "NÃO" }));

        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Lixo Domiciliar / Coleta ?");

        comboLixo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SIM", "NÃO" }));

        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Estrutura Moradia:");

        comboEstruturaMoraria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALVENARIA", "MADEIRA", "MISTA" }));

        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Nº Pessoas trabalham:");

        txtPessoasTrabalhando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPessoasTrabalhandoActionPerformed(evt);
            }
        });

        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Profissão:");

        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Frequenta Centro de Convivencia ?");
        jLabel41.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        comboCentroConvivencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SIM", "NÃO" }));

        spTableCliente_Socio_Economico.setBorder(null);

        table_cliente_socio_Economico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "RG", "CPF", "DT NASCIMENTO", "TELEFONE", "ESTADO CIVIL", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_cliente_socio_Economico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_cliente_socio_EconomicoMouseClicked(evt);
            }
        });
        spTableCliente_Socio_Economico.setViewportView(table_cliente_socio_Economico);

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nome:");

        txtNomeClienteTitularSocioEconomico.setEditable(false);
        txtNomeClienteTitularSocioEconomico.setDisabledTextColor(new java.awt.Color(187, 187, 187));

        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Nome Completo:");

        txtNomeClienteSocioEconomico.setDisabledTextColor(new java.awt.Color(187, 187, 187));
        txtNomeClienteSocioEconomico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeClienteSocioEconomicoActionPerformed(evt);
            }
        });

        btnBuscarClienteSocioEconomico.setText("PESQUISAR");
        btnBuscarClienteSocioEconomico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteSocioEconomicoActionPerformed(evt);
            }
        });

        btnSalvarClienteSocioEconomico.setBackground(new java.awt.Color(30, 180, 114));
        btnSalvarClienteSocioEconomico.setForeground(new java.awt.Color(245, 245, 245));
        btnSalvarClienteSocioEconomico.setText("Salvar");
        btnSalvarClienteSocioEconomico.setRippleColor(new java.awt.Color(255, 255, 255));
        btnSalvarClienteSocioEconomico.setShadowColor(new java.awt.Color(30, 180, 114));
        btnSalvarClienteSocioEconomico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarClienteSocioEconomicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(comboEscolariedade, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboComposicaoFamiliar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboEmprego, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboEnergia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomeClienteTitularSocioEconomico))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboCentroConvivencia, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addGap(18, 18, 18)
                                .addComponent(comboLixo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 6, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtProfissaoResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboRenda, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboMoradia, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboEstruturaMoraria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboProgramaSocial, 0, 1, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboSaneamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(137, 137, 137))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPessoasTrabalhando, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comboAgua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(38, 38, 38))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomeClienteSocioEconomico, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarClienteSocioEconomico))
                            .addComponent(btnSalvarClienteSocioEconomico, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spTableCliente_Socio_Economico, javax.swing.GroupLayout.PREFERRED_SIZE, 1023, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeClienteSocioEconomico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarClienteSocioEconomico)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spTableCliente_Socio_Economico, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeClienteTitularSocioEconomico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboEscolariedade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(comboRenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(comboProgramaSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboComposicaoFamiliar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel29)
                        .addComponent(comboMoradia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel34)
                        .addComponent(comboEstruturaMoraria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPessoasTrabalhando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboAgua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtProfissaoResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel40, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comboEmprego, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboLixo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboEnergia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboSaneamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboCentroConvivencia)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalvarClienteSocioEconomico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );

        jTabbedPane1.addTab("Socio Economico", jPanel3);

        jPanel4.setBackground(new java.awt.Color(0,0,0,0));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Nome Completo:");

        txtNomeClienteSocioEconomicoEconomico.setDisabledTextColor(new java.awt.Color(187, 187, 187));
        txtNomeClienteSocioEconomicoEconomico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeClienteSocioEconomicoEconomicoActionPerformed(evt);
            }
        });

        btnBuscarClienteSocioEconomicoSaude.setText("PESQUISAR");
        btnBuscarClienteSocioEconomicoSaude.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteSocioEconomicoSaudeActionPerformed(evt);
            }
        });

        spTableCliente_Socio_EconomicoSaude.setBorder(null);

        table_cliente_socio_EconomicoSaude.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "RG", "CPF", "DT NASCIMENTO", "TELEFONE", "ESTADO CIVIL", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_cliente_socio_EconomicoSaude.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_cliente_socio_EconomicoSaudeMouseClicked(evt);
            }
        });
        spTableCliente_Socio_EconomicoSaude.setViewportView(table_cliente_socio_EconomicoSaude);

        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Nome:");

        txtNomeClienteTitularSocioEconomicoSaude.setEditable(false);
        txtNomeClienteTitularSocioEconomicoSaude.setDisabledTextColor(new java.awt.Color(187, 187, 187));

        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Possui Alguma Doença?");

        comboDoenca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NÃO", "SIM", " " }));
        comboDoenca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDoencaActionPerformed(evt);
            }
        });

        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Outras Doenças?");

        txtoutrasDoencas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtoutrasDoencasActionPerformed(evt);
            }
        });

        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Deficiencia?");

        comboDeficiencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NÃO", "SIM", " " }));
        comboDeficiencia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboDeficienciaItemStateChanged(evt);
            }
        });
        comboDeficiencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDeficienciaActionPerformed(evt);
            }
        });

        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("Justificativa Deficiencia:");

        txtjustificativaDeficiencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtjustificativaDeficienciaActionPerformed(evt);
            }
        });

        comboLaudo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NÃO", "SIM", " " }));
        comboLaudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLaudoActionPerformed(evt);
            }
        });

        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Possui Laudo?");

        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Observação:");

        txtObservação.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtObservaçãoActionPerformed(evt);
            }
        });

        btnSalvarSocioEconomicoSaude.setBackground(new java.awt.Color(30, 180, 114));
        btnSalvarSocioEconomicoSaude.setForeground(new java.awt.Color(245, 245, 245));
        btnSalvarSocioEconomicoSaude.setText("Salvar");
        btnSalvarSocioEconomicoSaude.setRippleColor(new java.awt.Color(255, 255, 255));
        btnSalvarSocioEconomicoSaude.setShadowColor(new java.awt.Color(30, 180, 114));
        btnSalvarSocioEconomicoSaude.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarSocioEconomicoSaudeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboDoenca, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomeClienteTitularSocioEconomicoSaude))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtjustificativaDeficiencia)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtoutrasDoencas, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboDeficiencia, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboLaudo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtObservação, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(146, 146, 146))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomeClienteSocioEconomicoEconomico, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarClienteSocioEconomicoSaude))
                            .addComponent(btnSalvarSocioEconomicoSaude, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spTableCliente_Socio_EconomicoSaude, javax.swing.GroupLayout.PREFERRED_SIZE, 1023, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeClienteSocioEconomicoEconomico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarClienteSocioEconomicoSaude)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spTableCliente_Socio_EconomicoSaude, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeClienteTitularSocioEconomicoSaude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboDoenca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtoutrasDoencas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboDeficiencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtjustificativaDeficiencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboLaudo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtObservação, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSalvarSocioEconomicoSaude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(300, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Socio Economico Saúde", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1085, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboStatusActionPerformed

    private void table_clienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_clienteMouseClicked
        btnNovo.setEnabled(false);
        btnAlterar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnExcluir.setEnabled(true);
        String nome = "" + table_cliente.getValueAt(table_cliente.getSelectedRow(), 1);
        con.getConectar();

        con.executarSql("select c.id, c.nome_Completo, c.nome_Social, c.cor_cliente, c.nome_Mae, c.telefone, \n"
                + "		c.data_Nascimento, c.idade_cliente, c.genero_cliente, c.estado_Civil, c.rg, c.cpf, c.nis, c.status_Cliente, \n"
                + "		e.id, e.cep, e.bairro, e.rua, e.numero, e.referencia, e.nacionalidade, e.naturalidade, e.cidade, e.tempoDeMoradia_cliente\n"
                + "	from tb_titular c inner join tb_endereco e on c.id_endereco = e.id where nome_Completo ='" + nome + "'");
        try {
            con.getResultSet().first();
            id = Integer.parseInt(con.getResultSet().getString("id"));
            txtnomeCompleto.setText(con.getResultSet().getString("nome_Completo"));
            txtnomeSocial.setText(con.getResultSet().getString("nome_Social"));
            comboCor.setSelectedItem(con.getResultSet().getString("cor_cliente"));
            txtnomeMae.setText(con.getResultSet().getString("nome_Mae"));
            txtFone.setText(con.getResultSet().getString("telefone"));
            txtDTNascimento.setText(con.getResultSet().getString("data_Nascimento"));
            txtidade.setText(con.getResultSet().getString("idade_cliente"));
            comboSexo.setSelectedItem(con.getResultSet().getString("genero_cliente"));
            comboEst_Civil.setSelectedItem(con.getResultSet().getString("estado_Civil"));
            txtRg.setText(con.getResultSet().getString("rg"));
            txtcpf.setText(con.getResultSet().getString("cpf"));
            txtNis.setText(con.getResultSet().getString("nis"));
            comboStatus.setSelectedItem(con.getResultSet().getString("status_Cliente"));
            txtcep.setText(con.getResultSet().getString("cep"));
            txtbairro.setText(con.getResultSet().getString("bairro"));
            txtrua.setText(con.getResultSet().getString("rua"));
            txtnumerocasa.setText(con.getResultSet().getString("numero"));
            txtreferencia.setText(con.getResultSet().getString("referencia"));
            combonacionalidade.setSelectedItem(con.getResultSet().getString("nacionalidade"));
            txtnaturalidade.setText(con.getResultSet().getString("naturalidade"));
            txtCidade.setText(con.getResultSet().getString("cidade"));
            txttempomoradia.setText(con.getResultSet().getString("tempoDeMoradia_cliente"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no ao selecionar os dados" + ex);
        }
    }//GEN-LAST:event_table_clienteMouseClicked

    private void txtNomeClientePesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeClientePesquisarActionPerformed
        btnBuscarClienteActionPerformed(evt);
    }//GEN-LAST:event_txtNomeClientePesquisarActionPerformed

    private void txtDTNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDTNascimentoActionPerformed

    }//GEN-LAST:event_txtDTNascimentoActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        BuscarTitularPreenchendoTable();
        btnNovo.setEnabled(false);
        btnAlterar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnExcluir.setEnabled(true);
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void txtPesquisarNomeTitularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesquisarNomeTitularActionPerformed
        btnBuscarClienteDependenteActionPerformed(evt);
    }//GEN-LAST:event_txtPesquisarNomeTitularActionPerformed

    private void btnBuscarClienteDependenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteDependenteActionPerformed
        BuscarTitularDependentePreenchendoTable();
    }//GEN-LAST:event_btnBuscarClienteDependenteActionPerformed

    private void table_cliente_dependenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_cliente_dependenteMouseClicked
        String nome = "" + table_cliente_dependente.getValueAt(table_cliente_dependente.getSelectedRow(), 1);
        con.getConectar();

        con.executarSql("select c.id, c.nome_Completo, c.nome_Social, c.cor_cliente, c.nome_Mae, c.telefone, \n"
                + "		c.data_Nascimento, c.idade_cliente, c.genero_cliente, c.estado_Civil, c.rg, c.cpf, c.status_Cliente, \n"
                + "		e.id, e.cep, e.bairro, e.rua, e.numero, e.referencia, e.nacionalidade, e.naturalidade, e.cidade, e.tempoDeMoradia_cliente\n"
                + "	from tb_titular c inner join tb_endereco e on c.id_endereco = e.id where nome_Completo ='" + nome + "'");
        try {
            con.getResultSet().first();
            id = Integer.parseInt(con.getResultSet().getString("id"));
            txtNomeClienteTitular.setText(con.getResultSet().getString("nome_Completo"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no ao selecionar os dados" + ex);
        }
    }//GEN-LAST:event_table_cliente_dependenteMouseClicked

    private void comboParentescoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboParentescoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboParentescoActionPerformed

    private void spTableDependenteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spTableDependenteMouseClicked
        btnNovoDependente.setEnabled(false);
        btnAlterarDependente.setEnabled(true);
        btnSalvarDependente.setEnabled(false);
        btnCancelarDep.setEnabled(true);
        btnRemoverDependente.setEnabled(true);
        String nome = "" + spTableDependente.getValueAt(spTableDependente.getSelectedRow(), 1);
        con.getConectar();
        con.executarSql("select d.id_titular, t.nome_Completo, d.id, d.nome_dependente,d.rg, d.cpf, d.Idade, d.genero, d.dependencia_cliente from tb_titular t inner join tb_dependentes d on d.id_titular = t.id where nome_dependente ='" + nome + "'");
        try {
            con.getResultSet().first();
            id_dependente = Integer.parseInt(con.getResultSet().getString("d.id"));
            id_titular = Integer.parseInt(con.getResultSet().getString("d.id_titular"));
            txtNomeClienteTitular.setText(con.getResultSet().getString("t.nome_Completo"));
            txtnomeCompletoDependente.setText(con.getResultSet().getString("d.nome_dependente"));
            txtRgDependente.setText(con.getResultSet().getString("d.rg"));
            txtcpfDependente.setText(con.getResultSet().getString("d.cpf"));
            txtIdateDependente.setText(con.getResultSet().getString("d.Idade"));
            comboSexoDependente.setSelectedItem(con.getResultSet().getString("d.genero"));
            comboParentesco.setSelectedItem(con.getResultSet().getString("d.dependencia_cliente"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Dependente não cadastrado" + ex);
        }
    }//GEN-LAST:event_spTableDependenteMouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        txtNomeClienteTitular.setText("");
        limparCamposDependentes();
    }//GEN-LAST:event_jPanel2MouseClicked

    private void txtPessoasTrabalhandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPessoasTrabalhandoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPessoasTrabalhandoActionPerformed

    private void table_cliente_socio_EconomicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_cliente_socio_EconomicoMouseClicked
        String nome = "" + table_cliente_socio_Economico.getValueAt(table_cliente_socio_Economico.getSelectedRow(), 1);
        con.getConectar();

        con.executarSql("select c.id, c.nome_Completo, c.nome_Social, c.cor_cliente, c.nome_Mae, c.telefone, \n"
                + "		c.data_Nascimento, c.idade_cliente, c.genero_cliente, c.estado_Civil, c.rg, c.cpf, c.status_Cliente, \n"
                + "		e.id, e.cep, e.bairro, e.rua, e.numero, e.referencia, e.nacionalidade, e.naturalidade, e.cidade, e.tempoDeMoradia_cliente\n"
                + "	from tb_titular c inner join tb_endereco e on c.id_endereco = e.id where nome_Completo ='" + nome + "'");
        try {
            con.getResultSet().first();
            id = Integer.parseInt(con.getResultSet().getString("id"));
            txtNomeClienteTitularSocioEconomico.setText(con.getResultSet().getString("nome_Completo"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no ao selecionar os dados" + ex);
        }
    }//GEN-LAST:event_table_cliente_socio_EconomicoMouseClicked

    private void txtNomeClienteSocioEconomicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeClienteSocioEconomicoActionPerformed
        btnBuscarClienteSocioEconomicoActionPerformed(evt);
    }//GEN-LAST:event_txtNomeClienteSocioEconomicoActionPerformed

    private void btnBuscarClienteSocioEconomicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteSocioEconomicoActionPerformed
        BuscarTitularSocioEconomicoPreenchendoTable();
    }//GEN-LAST:event_btnBuscarClienteSocioEconomicoActionPerformed

    private void btnSalvarClienteSocioEconomicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarClienteSocioEconomicoActionPerformed
        validarCamposSocioEconomico();
        saveSocioEconomico();
    }//GEN-LAST:event_btnSalvarClienteSocioEconomicoActionPerformed

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        txtNomeClienteTitularSocioEconomico.setText("");
    }//GEN-LAST:event_jPanel3MouseClicked

    private void txtNomeClienteSocioEconomicoEconomicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeClienteSocioEconomicoEconomicoActionPerformed
        btnBuscarClienteSocioEconomicoSaudeActionPerformed(evt);
    }//GEN-LAST:event_txtNomeClienteSocioEconomicoEconomicoActionPerformed

    private void btnBuscarClienteSocioEconomicoSaudeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteSocioEconomicoSaudeActionPerformed
        BuscarTitularSocioEconomicoSaudePreenchendoTable();
    }//GEN-LAST:event_btnBuscarClienteSocioEconomicoSaudeActionPerformed

    private void table_cliente_socio_EconomicoSaudeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_cliente_socio_EconomicoSaudeMouseClicked
        String nome = "" + table_cliente_socio_EconomicoSaude.getValueAt(table_cliente_socio_EconomicoSaude.getSelectedRow(), 1);
        con.getConectar();

        con.executarSql("select c.id, c.nome_Completo, c.nome_Social, c.cor_cliente, c.nome_Mae, c.telefone, \n"
                + "		c.data_Nascimento, c.idade_cliente, c.genero_cliente, c.estado_Civil, c.rg, c.cpf, c.status_Cliente, \n"
                + "		e.id, e.cep, e.bairro, e.rua, e.numero, e.referencia, e.nacionalidade, e.naturalidade, e.cidade, e.tempoDeMoradia_cliente\n"
                + "	from tb_titular c inner join tb_endereco e on c.id_endereco = e.id where nome_Completo ='" + nome + "'");
        try {
            con.getResultSet().first();
            id = Integer.parseInt(con.getResultSet().getString("id"));
            txtNomeClienteTitularSocioEconomicoSaude.setText(con.getResultSet().getString("nome_Completo"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro no ao selecionar os dados" + ex);
        }
    }//GEN-LAST:event_table_cliente_socio_EconomicoSaudeMouseClicked

    private void comboDoencaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDoencaActionPerformed

    }//GEN-LAST:event_comboDoencaActionPerformed

    private void txtoutrasDoencasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtoutrasDoencasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtoutrasDoencasActionPerformed

    private void comboDeficienciaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboDeficienciaItemStateChanged

    }//GEN-LAST:event_comboDeficienciaItemStateChanged

    private void comboDeficienciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDeficienciaActionPerformed

    }//GEN-LAST:event_comboDeficienciaActionPerformed

    private void txtjustificativaDeficienciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtjustificativaDeficienciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtjustificativaDeficienciaActionPerformed

    private void comboLaudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLaudoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboLaudoActionPerformed

    private void txtObservaçãoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtObservaçãoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObservaçãoActionPerformed

    private void btnSalvarSocioEconomicoSaudeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarSocioEconomicoSaudeActionPerformed
        validarCamposSocioEconomicoSaude();
        saveSocioEconomicoSaude();
    }//GEN-LAST:event_btnSalvarSocioEconomicoSaudeActionPerformed

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        txtNomeClienteTitularSocioEconomicoSaude.setText("");
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        txtNomeClienteTitular.setText("");
        txtNomeClienteTitularSocioEconomico.setText("");
        txtNomeClienteTitularSocioEconomicoSaude.setText("");
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        flag = 1;
        habilitarCampos();
        habilitarBotao();
        btnNovo.setEnabled(false);
        btnAlterar.setEnabled(false);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (flag == 1) {
            validarCamposTitular();
            saveClientes();
        } else {
            validarCamposTitular();
            updateClientes();
        };
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        flag = 2;
        habilitarCampos();
        habilitarBotao();
        btnNovo.setEnabled(false);
        btnAlterar.setEnabled(false);
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limparCampos();
        desabilitarBotao();
        desabilitarCampos();
        tabela_cliente("select c.id, c.nome_Completo, c.nome_Social, c.cor_cliente, c.nome_Mae, c.telefone, \n"
                + "		c.data_Nascimento, c.idade_cliente, c.genero_cliente, c.estado_Civil, c.rg, c.cpf, c.nis,c.status_Cliente, c.registration_date,\n"
                + "		e.id, e.cep, e.bairro, e.rua, e.numero, e.referencia, e.nacionalidade, e.naturalidade, e.cidade, e.tempoDeMoradia_cliente\n"
                + "	from tb_titular c inner join tb_endereco e on c.id_endereco = e.id order by nome_Completo");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNovoDependenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoDependenteActionPerformed
        flag1 = 1;
        btnAlterarDependente.setEnabled(false);
        btnNovoDependente.setEnabled(false);
        habilitarBotaoDependente();
        habilitarCamposDependentes();
    }//GEN-LAST:event_btnNovoDependenteActionPerformed

    private void btnSalvarDependenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarDependenteActionPerformed
        if (flag1 == 1) {
            validarCamposDependentes();
            saveDependentes();
        } else {
            updateDependentes();
        };
    }//GEN-LAST:event_btnSalvarDependenteActionPerformed

    private void btnAlterarDependenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarDependenteActionPerformed
        flag1 = 2;
        btnNovoDependente.setEnabled(false);
        btnAlterarDependente.setEnabled(false);
        btnSalvarDependente.setEnabled(true);
        btnCancelarDep.setEnabled(true);
        habilitarCamposDependentes();

    }//GEN-LAST:event_btnAlterarDependenteActionPerformed

    private void btnCancelarDepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarDepActionPerformed
        limparCamposDependentes();
        btnNovoDependente.setEnabled(true);
        desabilitarBotaoDependente();
        DesabilitarCamposDependentes();
        atualizarTabelas();
    }//GEN-LAST:event_btnCancelarDepActionPerformed

    private void txtNisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNisActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Risco risco = new Risco();
        risco.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluirTitulares();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnRemoverDependenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverDependenteActionPerformed
        excluirDependente();
    }//GEN-LAST:event_btnRemoverDependenteActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        BuscarDependentePreenchendoTela();
    }//GEN-LAST:event_jButton2ActionPerformed

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
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnAlterarDependente;
    private button.Button btnBuscarCliente;
    private javax.swing.JButton btnBuscarClienteDependente;
    private javax.swing.JButton btnBuscarClienteSocioEconomico;
    private javax.swing.JButton btnBuscarClienteSocioEconomicoSaude;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarDep;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnNovoDependente;
    private javax.swing.JButton btnRemoverDependente;
    private javax.swing.JButton btnSalvar;
    private button.Button btnSalvarClienteSocioEconomico;
    private javax.swing.JButton btnSalvarDependente;
    private button.Button btnSalvarSocioEconomicoSaude;
    private javax.swing.JComboBox<String> comboAgua;
    private javax.swing.JComboBox<String> comboCentroConvivencia;
    private javax.swing.JComboBox<String> comboComposicaoFamiliar;
    private javax.swing.JComboBox<String> comboCor;
    private javax.swing.JComboBox<String> comboDeficiencia;
    private javax.swing.JComboBox<String> comboDoenca;
    private javax.swing.JComboBox<String> comboEmprego;
    private javax.swing.JComboBox<String> comboEnergia;
    private javax.swing.JComboBox<String> comboEscolariedade;
    private javax.swing.JComboBox<String> comboEst_Civil;
    private javax.swing.JComboBox<String> comboEstruturaMoraria;
    private javax.swing.JComboBox<String> comboLaudo;
    private javax.swing.JComboBox<String> comboLixo;
    private javax.swing.JComboBox<String> comboMoradia;
    private javax.swing.JComboBox<String> comboParentesco;
    private javax.swing.JComboBox<String> comboProgramaSocial;
    private javax.swing.JComboBox<String> comboRenda;
    private javax.swing.JComboBox<String> comboSaneamento;
    private javax.swing.JComboBox<String> comboSexo;
    private javax.swing.JComboBox<String> comboSexoDependente;
    private javax.swing.JComboBox<String> comboStatus;
    private javax.swing.JComboBox<String> combonacionalidade;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbnis;
    private javax.swing.JScrollPane spTableCliente;
    private javax.swing.JScrollPane spTableCliente_Socio_Economico;
    private javax.swing.JScrollPane spTableCliente_Socio_EconomicoSaude;
    private javax.swing.JScrollPane spTableCliente_dependente;
    private view.com.raven.swing.Table2 spTableDependente;
    private javax.swing.JScrollPane spTableDependentes;
    private view.com.raven.swing.Table2 table_cliente;
    private view.com.raven.swing.Table2 table_cliente_dependente;
    private view.com.raven.swing.Table2 table_cliente_socio_Economico;
    private view.com.raven.swing.Table2 table_cliente_socio_EconomicoSaude;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JFormattedTextField txtDTNascimento;
    private javax.swing.JFormattedTextField txtFone;
    private javax.swing.JTextField txtIdateDependente;
    private javax.swing.JFormattedTextField txtNis;
    private javax.swing.JTextField txtNomeClientePesquisar;
    private javax.swing.JTextField txtNomeClienteSocioEconomico;
    private javax.swing.JTextField txtNomeClienteSocioEconomicoEconomico;
    private javax.swing.JTextField txtNomeClienteTitular;
    private javax.swing.JTextField txtNomeClienteTitularSocioEconomico;
    private javax.swing.JTextField txtNomeClienteTitularSocioEconomicoSaude;
    private javax.swing.JTextField txtObservação;
    private javax.swing.JTextField txtPesquisarNomeTitular;
    private javax.swing.JTextField txtPessoasTrabalhando;
    private javax.swing.JTextField txtProfissaoResponsavel;
    private javax.swing.JTextField txtRg;
    private javax.swing.JFormattedTextField txtRgDependente;
    private javax.swing.JTextField txt_pesquisar_dependente;
    private javax.swing.JTextField txtbairro;
    private javax.swing.JFormattedTextField txtcep;
    private javax.swing.JFormattedTextField txtcpf;
    private javax.swing.JFormattedTextField txtcpfDependente;
    private javax.swing.JTextField txtidade;
    private javax.swing.JTextField txtjustificativaDeficiencia;
    private javax.swing.JTextField txtnaturalidade;
    private javax.swing.JTextField txtnomeCompleto;
    private javax.swing.JTextField txtnomeCompletoDependente;
    private javax.swing.JTextField txtnomeMae;
    private javax.swing.JTextField txtnomeSocial;
    private javax.swing.JTextField txtnumerocasa;
    private javax.swing.JTextField txtoutrasDoencas;
    private javax.swing.JTextField txtreferencia;
    private javax.swing.JTextField txtrua;
    private javax.swing.JTextField txttempomoradia;
    // End of variables declaration//GEN-END:variables
}

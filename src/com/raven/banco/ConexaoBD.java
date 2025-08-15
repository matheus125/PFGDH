package com.raven.banco;

import java.sql.*;
import javax.swing.JOptionPane;

public class ConexaoBD {

    /**
     * Variveis para conexão com o banco de dados
     */
    public Statement stm;
    public ResultSet rs;
    public Connection con = null;
    private Statement statement;
    private ResultSet resultSet;

    /**
     * Variavel para login com banco
     */
    // Constantes para conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost/tabatinga";       // Exemplo: "jdbc:mysql://localhost/dev05"
    private static final String USER = "root";     // Exemplo: "root"
    private static final String PASSWORD = "#Wiccan13#"; // Exemplo: "password"
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private final String url = "jdbc:mysql://localhost/tabatinga";
    private final String user = "root";
    private final String pass = "#Wiccan13#";
    private final String driver = "com.mysql.cj.jdbc.Driver";

    /**
     * metodo para conectar com o banco de dados
     *
     * @return
     */
    public boolean getConectar() {

        try {
            //passando por paramentro o Driver JDBC
            Class.forName(driver).newInstance();
            //caminho para o banco de dados com: local, usuario, senha
            this.con = DriverManager.getConnection(url, user, pass);
            return true;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados!" + erro.getMessage());
            return false;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados! :)" + e.getMessage());
            return false;
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            // Carregar o driver JDBC
            Class.forName(DRIVER);
            // Retornar a conexão com o banco de dados
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC não encontrado!", e);
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar ao banco de dados!", e);
        }
    }

    /**
     * metodo executa um comando SQL pasado por @parametro um String Sql
     *
     * @param sql
     * @return
     */
    public boolean executarSql(String sql) {
        try {
            //createStatement de con para criar o Statement
            this.statement = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // Definido o Statement, executamos a query no banco de dados
            //comando sql das classe DAO
            this.resultSet = this.statement.executeQuery(sql);
            //caso não ocorra nenhum erro retorne a verdadeiro
            return true;
        } catch (SQLException erroSql) {
            //emitir o erro para ser visualizado p/ correação
            System.out.println("Erro: " + erroSql.getMessage());
            System.out.println("Erro: " + erroSql.getErrorCode());
            return false;
        }
    }

    public void executar(String sql) {
        try {
            stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Executar Sql:\n" + e.getMessage());
        }
    }

    public boolean executarUpdateDeleteSQL(String pSQL) {
        try {

            //createStatement de con para criar o Statement
            this.setStatement(getCon().createStatement());

            // Definido o Statement, executamos a query no banco de dados
            getStatement().executeUpdate(pSQL);

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    //

    public boolean executarUpdateSQL(String sql) {
        try {
            this.statement = con.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            this.statement.executeUpdate(sql);
            return true;

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            return false;
        }
    }

    public int insertSQL(String pSQL) {
        int status = 0;
        try {
            //createStatement de con para criar o Statement
            this.setStatement(getCon().createStatement());

            // Definido o Statement, executamos a query no banco de dados
            this.getStatement().executeUpdate(pSQL);

            //consulta o ultimo id inserido
            this.setResultSet(this.getStatement().executeQuery("SELECT last_insert_id();"));

            //recupera o ultimo id inserido
            while (this.resultSet.next()) {
                status = this.resultSet.getInt(1);
            }

            //retorna o ultimo id inserido
            return status;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return status;
        }
    }

    /**
     * Metodo para encerrar conexão com o banco de dados
     *
     * @return
     */
    public boolean getfecharConexao() {
        try {
            if ((this.getResultSet() != null) && (this.statement != null)) {
                this.getResultSet().close();
                this.statement.close();
            }
            //System.out.println("Conexão desligada com sucesso!!");
            this.getCon().close();
            return true;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "erro: " + erro.getMessage());
        }
        return false;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public void executarSql(ResultSet Sql) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}

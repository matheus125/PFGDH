package com.raven.dao;

import com.raven.banco.ConexaoBD;
import com.raven.model.Frequencia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;

<<<<<<< HEAD
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FrequenciaDAO extends ConexaoBD {

    private static final String INSERT_DIARIA
            = "INSERT INTO tb_frequencia_diaria (cliente_id, nome, data, status) VALUES (?, ?, ?, ?)";
    private static final String INSERT_GERAL
            = "INSERT INTO tb_frequencia_geral (cliente_id, nome, data, status) VALUES (?, ?, ?, ?)";
    private static final String SELECT_FREQUENCIA_POR_ALUNO
            = "SELECT * FROM tb_frequencia_diaria WHERE cliente_id = ?";
    private static final String LIMPAR_DIARIA = "CALL sp_limpar_frequencia_diaria";

    // SALVAR FREQUÊNCIAS EM UMA TABELA DIÁRIA
    public void adicionarFrequencia(Frequencia frequencia) {
        try (
                 Connection conn = ConexaoBD.getConnection();  PreparedStatement stmt = conn.prepareStatement(INSERT_DIARIA)) {
=======
public class FrequenciaDAO extends ConexaoBD {

    //SALVAR FREQUENCIAS EM UM TABELA DIARIA.
    public void adicionarFrequencia(Frequencia frequencia) {
        String sql = "INSERT INTO tb_frequencia_diaria (cliente_id,nome ,data, status) VALUES (?, ?, ?, ?)";

        try (
                 Connection conn = ConexaoBD.getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {

>>>>>>> eb9b89a (PFGDH_2.1)
            stmt.setInt(1, frequencia.getClienteId());
            stmt.setString(2, frequencia.getNome());
            stmt.setDate(3, new java.sql.Date(frequencia.getData().getTime()));
            stmt.setString(4, frequencia.getStatus());

            stmt.executeUpdate();
<<<<<<< HEAD
        } catch (SQLException e) {
            // Substituir por framework de log, exemplo: logger.error("Erro ao adicionar frequência diária", e);
            System.err.println("Erro ao adicionar frequência diária: " + e.getMessage());
        }
    }

    // LISTAR CLIENTES FREQUENTES
    public List<Frequencia> consultarFrequenciaPorAluno(int alunoId) {
        List<Frequencia> frequencias = new ArrayList<>();
        try (
                 Connection conn = ConexaoBD.getConnection();  PreparedStatement stmt = conn.prepareStatement(SELECT_FREQUENCIA_POR_ALUNO)) {
            stmt.setInt(1, alunoId);
            try ( ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Frequencia frequencia = new Frequencia(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getDate("data"),
                            rs.getString("status")
                    );
                    frequencias.add(frequencia);
                }
            }
        } catch (SQLException e) {
            // Substituir por framework de log
            System.err.println("Erro ao consultar frequência por aluno: " + e.getMessage());
        }
        return frequencias;
    }

    // SALVAR FREQUÊNCIAS EM UMA TABELA GERAL
    public void adicionarFrequenciaGeral(Frequencia frequencia) {
        try (
                 Connection conn = ConexaoBD.getConnection();  PreparedStatement stmt = conn.prepareStatement(INSERT_GERAL)) {
=======
            System.out.println("Frequência registrada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //LISTAR CLIENTES FREQUENTES.
    public List<Frequencia> consultarFrequenciaPorAluno(int alunoId) {
        List<Frequencia> frequencias = new ArrayList<>();
        String sql = "SELECT * FROM tb_frequencia_diaria WHERE cliente_id = ?";

        try (
                 Connection conn = ConexaoBD.getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, alunoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Date data = rs.getDate("data");
                String status = rs.getString("status");

                Frequencia frequencia = new Frequencia(id, nome, data, status);
                frequencias.add(frequencia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return frequencias;
    }

    //SALVAR FREQUENCIAS EM OUTRA TABELA DE UMA FORMA GERAL.
    public void adicionarFrequenciaGeral(Frequencia frequencia) {
        String sql = "INSERT INTO tb_frequencia_geral (cliente_id,nome ,data, status) VALUES (?, ?, ?, ?)";

        try (
                 Connection conn = ConexaoBD.getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {

>>>>>>> eb9b89a (PFGDH_2.1)
            stmt.setInt(1, frequencia.getClienteId());
            stmt.setString(2, frequencia.getNome());
            stmt.setDate(3, new java.sql.Date(frequencia.getData().getTime()));
            stmt.setString(4, frequencia.getStatus());

            stmt.executeUpdate();
<<<<<<< HEAD
        } catch (SQLException e) {
            // Substituir por framework de log
            System.err.println("Erro ao adicionar frequência geral: " + e.getMessage());
        }
    }

    // MÉTODO PARA LIMPAR TABELA DIÁRIA AO FECHAR O DIA
    public boolean limparTabelaFrequenciaDiaria() {
        try (
                 Connection conn = ConexaoBD.getConnection();  PreparedStatement stmt = conn.prepareStatement(LIMPAR_DIARIA)) {
            stmt.execute();
            return true;
        } catch (SQLException e) {
            // Substituir por framework de log
            System.err.println("Erro ao limpar tabela de frequência diária: " + e.getMessage());
            return false;
        }
    }
=======
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //METODO PARA LIMPAR TABELA "controllimparFrequencia", AO FECHAR O DIA.
    public boolean limparTabelaFrequencia_diaria() {

        try {
            this.getConectar();
            this.executarSql("call sp_limpar_frequencia_diaria");
            return true;
        } catch (Exception e) {
            return false;
        }
    }//FIM.
>>>>>>> eb9b89a (PFGDH_2.1)
}

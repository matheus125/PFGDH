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
            stmt.setInt(1, frequencia.getClienteId());
            stmt.setString(2, frequencia.getNome());
            stmt.setDate(3, new java.sql.Date(frequencia.getData().getTime()));
            stmt.setString(4, frequencia.getStatus());

            stmt.executeUpdate();
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
            stmt.setInt(1, frequencia.getClienteId());
            stmt.setString(2, frequencia.getNome());
            stmt.setDate(3, new java.sql.Date(frequencia.getData().getTime()));
            stmt.setString(4, frequencia.getStatus());

            stmt.executeUpdate();
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
}

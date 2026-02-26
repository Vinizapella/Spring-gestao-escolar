package com.weg.gestao_escolar.repository;

import com.weg.gestao_escolar.model.Aula;
import com.weg.gestao_escolar.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AulaRepository {

    public Aula criarAula(Aula aula) throws SQLException {
        String sql = """
                INSERT 
                INTO
                aula(
                turma_id,
                data_hora,
                assunto)
                VALUES
                (?,?,?)
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, aula.getTurmaId());
            stmt.setObject(2, aula.getDataHora());
            stmt.setString(3, aula.getAssunto());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                aula.setId(rs.getInt(1));
            }
        }
        return aula;
    }

    public List<Aula> listarAulas() throws SQLException {
        String sql = """
                SELECT
                id,
                turma_id,
                data_hora,
                assunto
                FROM
                aula
                """;
        List<Aula> aulas = new ArrayList<>();
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int turmaId = rs.getInt("turma_id");
                LocalDateTime dataHora = rs.getObject("data_hora", LocalDateTime.class);
                String assunto = rs.getString("assunto");
                aulas.add(new Aula(id, turmaId, dataHora, assunto));
            }
        }
        return aulas;
    }

    public Aula listarPorId(int id) throws SQLException {
        String sql = """
                SELECT
                id,
                turma_id,
                data_hora,
                assunto
                FROM
                aula
                WHERE
                id = ?
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int identidade = rs.getInt("id");
                int turmaId = rs.getInt("turma_id");
                LocalDateTime dataHora = rs.getObject("data_hora", LocalDateTime.class);
                String assunto = rs.getString("assunto");
                return new Aula(identidade, turmaId, dataHora, assunto);
            }
        }
        return null;
    }

    public void atualizaAula(Aula aula) throws SQLException {
        String sql = """
                UPDATE
                aula
                SET
                turma_id = ?,
                data_hora = ?,
                assunto = ?
                WHERE
                id = ?
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, aula.getTurmaId());
            stmt.setObject(2, aula.getDataHora());
            stmt.setString(3, aula.getAssunto());
            stmt.setInt(4, aula.getId());
            stmt.executeUpdate();
        }
    }

    public void deletaAula(int id) throws SQLException {
        String sql = """
                DELETE
                FROM
                aula
                WHERE
                id = ?
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
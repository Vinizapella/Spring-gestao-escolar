package com.weg.gestao_escolar.repository;

import com.weg.gestao_escolar.model.Turma;
import com.weg.gestao_escolar.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TurmaRepository {

    public Turma criarTurma(Turma turma) throws SQLException {
        String sql = """
                INSERT 
                INTO
                turma(
                nome,
                curso_id,
                professor_id)
                VALUES
                (?,?,?)
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, turma.getNome());
            stmt.setInt(2, turma.getCurso_id());
            stmt.setInt(3, turma.getProfessor_id());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                turma.setId(rs.getInt(1));
            }
        }
        return turma;
    }

    public List<Turma> listarTurmas() throws SQLException {
        String sql = """
                SELECT
                id,
                nome,
                curso_id,
                professor_id
                FROM
                turma
                """;
        List<Turma> turmas = new ArrayList<>();
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int cursoId = rs.getInt("curso_id");
                int professorId = rs.getInt("professor_id");
                Turma turma = new Turma(id, nome, cursoId, professorId);
                turmas.add(turma);
            }
        }
        return turmas;
    }

    public Turma listarPorId(int id) throws SQLException {
        String sql = """
                SELECT
                id,
                nome,
                curso_id,
                professor_id
                FROM
                turma
                WHERE
                id = ?
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int identidade = rs.getInt("id");
                String nome = rs.getString("nome");
                int cursoId = rs.getInt("curso_id");
                int professorId = rs.getInt("professor_id");
                return new Turma(identidade, nome, cursoId, professorId);
            }
        }
        return null;
    }

    public void atualizaTurma(Turma turma) throws SQLException {
        String sql = """
                UPDATE
                turma
                SET
                nome = ?,
                curso_id = ?,
                professor_id = ?
                WHERE
                id = ?
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, turma.getNome());
            stmt.setInt(2, turma.getCurso_id());
            stmt.setInt(3, turma.getProfessor_id());
            stmt.setInt(4, turma.getId());
            stmt.executeUpdate();
        }
    }

    public void deletaTurma(int id) throws SQLException {
        String sql = """
                DELETE
                FROM
                turma
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

package com.weg.gestao_escolar.repository;

import com.weg.gestao_escolar.model.Nota;
import com.weg.gestao_escolar.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NotaRepository {

    public Nota criarNota(Nota nota) throws SQLException {
        String sql = """
                INSERT 
                INTO
                nota(
                aluno_id,
                aula_id,
                valor)
                VALUES
                (?,?,?)
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, nota.getAluno_id());
            stmt.setInt(2, nota.getAula_id());
            stmt.setDouble(3, nota.getValor());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                nota.setId(rs.getInt(1));
            }
        }
        return nota;
    }

    public List<Nota> listarNotas() throws SQLException {
        String sql = """
                SELECT
                id,
                aluno_id,
                aula_id,
                valor
                FROM
                nota
                """;
        List<Nota> notas = new ArrayList<>();
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int alunoId = rs.getInt("aluno_id");
                int aulaId = rs.getInt("aula_id");
                double valor = rs.getDouble("valor");
                notas.add(new Nota(id, alunoId, aulaId, valor));
            }
        }
        return notas;
    }

    public Nota listarPorId(int id) throws SQLException {
        String sql = """
                SELECT
                id,
                aluno_id,
                aula_id,
                valor
                FROM
                nota
                WHERE
                id = ?
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int identidade = rs.getInt("id");
                int alunoId = rs.getInt("aluno_id");
                int aulaId = rs.getInt("aula_id");
                double valor = rs.getDouble("valor");
                return new Nota(identidade, alunoId, aulaId, valor);
            }
        }
        return null;
    }

    public void atualizaNota(Nota nota) throws SQLException {
        String sql = """
                UPDATE
                nota
                SET
                aluno_id = ?,
                aula_id = ?,
                valor = ?
                WHERE
                id = ?
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, nota.getAluno_id());
            stmt.setLong(2, nota.getAula_id());
            stmt.setDouble(3, nota.getValor());
            stmt.setInt(4, nota.getId());
            stmt.executeUpdate();
        }
    }

    public void deletaNota(int id) throws SQLException {
        String sql = """
                DELETE
                FROM
                nota
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

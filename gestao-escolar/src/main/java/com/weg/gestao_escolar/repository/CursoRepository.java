package com.weg.gestao_escolar.repository;

import com.weg.gestao_escolar.model.Curso;
import com.weg.gestao_escolar.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoRepository {

    public Curso criaCurso(Curso curso)throws SQLException{
        String sql = """
                INSERT
                INTO
                curso(
                nome,
                codigo)
                VALUES
                (?,?)
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCodigo());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                curso.setId(rs.getInt(1));
            }
        }
        return curso;
    }

    public List<Curso>cursos()throws SQLException{
        String sql = """
                SELECT
                id,
                nome,
                codigo
                FROM
                curso
                """;
        List<Curso>cursos = new ArrayList<>();
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String codigo = rs.getString("codigo");
                Curso curso = new Curso(id, nome, codigo);
                cursos.add(curso);
            }
        }
        return cursos;
    }

    public Curso buscaId(int id)throws SQLException{
        String sql = """
                SELECT
                id,
                nome,
                codigo
                FROM
                curso
                WHERE
                id = ?
                """;
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                int identidade = rs.getInt("id");
                String nome = rs.getString("nome");
                String codigo = rs.getString("codigo");
                Curso curso = new Curso(id, nome, codigo);
                return curso;
            }
        }
        return null;
    }

    public void atualizaCurso(Curso curso)throws SQLException {
        String sql = """
                UPDATE
                curso
                SET
                nome = ?
                codigo = ?
                WHERE
                id = ?
                """;
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getCodigo());
            stmt.setInt(3, curso.getId());
            stmt.executeUpdate();
        }
    }

    public void delete(int id)throws SQLException {
        String sql = """
                DELETE
                FROM
                curso
                WHERE
                id = ?
                """;
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

package com.weg.gestao_escolar.repository;

import com.weg.gestao_escolar.dto.professor.ProfessorRequestDto;
import com.weg.gestao_escolar.model.Professor;
import com.weg.gestao_escolar.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfessorRepository {

    public Professor criarProfessor(Professor professor)throws SQLException {
        String sql = """
                INSERT
                INTO
                professor(
                nome,
                email,
                disciplina)
                VALUES
                (?, ?, ?)
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getDisciplina());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                professor.setId(rs.getInt(1));
            }
        }
        return professor;
    }

    public List<Professor>professores()throws SQLException{
        String sql = """
                SELECT
                id,
                nome,
                email,
                disciplina
                FROM
                professor
                """;
        List<Professor>professores = new ArrayList<>();
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();;
            while (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String disciplina = rs.getString("disciplina");
                Professor professor = new Professor(id, nome, email, disciplina);
                professores.add(professor);
            }
        }
        return professores;
    }

    public Professor buscaId(int id)throws SQLException {
        String sql = """
                SELECT
                id,
                nome,
                email,
                disciplina
                FROM
                professor
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
                String email = rs.getString("email");
                String disciplina = rs.getString("disciplina");
                Professor professor1 = new Professor(identidade, nome, email, disciplina);
                return professor1;
            }
        }
        return null;
    }

}

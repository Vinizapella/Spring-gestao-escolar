package com.weg.gestao_escolar.repository;

import com.weg.gestao_escolar.model.Professor;
import com.weg.gestao_escolar.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;

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

}

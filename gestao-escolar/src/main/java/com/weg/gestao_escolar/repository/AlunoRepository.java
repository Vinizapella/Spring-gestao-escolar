package com.weg.gestao_escolar.repository;

import com.weg.gestao_escolar.model.Aluno;
import com.weg.gestao_escolar.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class AlunoRepository {

    public Aluno criarAluno(Aluno aluno)throws SQLException{
        String sql = """
                INSERT 
                INTO
                aluno(
                nome,
                email,
                matricula,
                data_nascimento)
                VALUES
                (?,?,?,?)
                """;
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getMatricula());
            stmt.setObject(4, aluno.getData_nascimento());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                aluno.setId(rs.getInt(1));
            }
        }
        return aluno;
    }
}

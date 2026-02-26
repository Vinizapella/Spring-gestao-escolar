package com.weg.gestao_escolar.repository;

import com.weg.gestao_escolar.model.Aluno;
import com.weg.gestao_escolar.utils.Conexao;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public List<Aluno>listarAlunos()throws SQLException{
        String sql = """
                SELECT
                id,
                nome,
                email,
                matricula,
                data_nascimento
                FROM
                aluno
                """;
        List<Aluno>alunos = new ArrayList<>();
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String matricula = rs.getString("matricula");
                LocalDate data_nascimento = rs.getObject("data_nascimento", LocalDate.class);
                Aluno aluno = new Aluno(id, nome, email, matricula, data_nascimento);
                alunos.add(aluno);
            }
        }
        return alunos;
    }
}

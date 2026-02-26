package com.weg.gestao_escolar.mapper;

import com.weg.gestao_escolar.dto.aluno.AlunoRequestDto;
import com.weg.gestao_escolar.dto.aluno.AlunoResponseDto;
import com.weg.gestao_escolar.model.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    public Aluno toEntity(
            AlunoRequestDto alunoRequestDto
    ){
        return new Aluno(
                alunoRequestDto.nome(),
                alunoRequestDto.email(),
                alunoRequestDto.matricula(),
                alunoRequestDto.data_nascimento()
        );
    }

    public AlunoResponseDto toResponse(
            Aluno aluno
    ){
        return new AlunoResponseDto(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getMatricula(),
                aluno.getData_nascimento()
        );
    }
}

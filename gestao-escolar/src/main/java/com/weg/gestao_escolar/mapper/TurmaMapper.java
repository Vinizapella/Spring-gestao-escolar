package com.weg.gestao_escolar.mapper;

import com.weg.gestao_escolar.dto.turma.TurmaRequestDto;
import com.weg.gestao_escolar.dto.turma.TurmaResponseDto;
import com.weg.gestao_escolar.model.Turma;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TurmaMapper {
    public Turma toEntity(
            TurmaRequestDto dto
    ) {
        return new Turma(
                dto.nome(), dto.cursoId(), dto.professorId()
        );
    }

    public TurmaResponseDto toResponse(Turma turma, String nomeCurso, String nomeProfessor, List<String> nomesAlunos) {
        return new TurmaResponseDto(
                turma.getId(),
                turma.getNome(),
                nomeCurso,
                nomeProfessor,
                nomesAlunos
        );
    }
}

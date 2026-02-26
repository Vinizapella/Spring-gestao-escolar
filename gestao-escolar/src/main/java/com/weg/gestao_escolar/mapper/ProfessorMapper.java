package com.weg.gestao_escolar.mapper;

import com.weg.gestao_escolar.dto.professor.ProfessorRequestDto;
import com.weg.gestao_escolar.dto.professor.ProfessorResponseDto;
import com.weg.gestao_escolar.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    public Professor toEntity(
            ProfessorRequestDto professorRequestDto
    ){
        return new Professor(
                professorRequestDto.nome(),
                professorRequestDto.email(),
                professorRequestDto.disciplina()
        );
    }

    public ProfessorResponseDto toResponse(
            Professor professor
    ){
        return new ProfessorResponseDto(
                professor.getId(),
                professor.getNome(),
                professor.getEmail(),
                professor.getDisciplina()
        );
    }

}

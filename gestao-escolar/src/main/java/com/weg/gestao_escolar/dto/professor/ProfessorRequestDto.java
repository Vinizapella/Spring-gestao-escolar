package com.weg.gestao_escolar.dto.professor;

public record ProfessorRequestDto(
        String nome,
        String email,
        String disciplina
) {
}

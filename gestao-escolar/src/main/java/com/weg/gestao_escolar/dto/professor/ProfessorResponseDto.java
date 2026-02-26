package com.weg.gestao_escolar.dto.professor;

public record ProfessorResponseDto(
        int id,
        String nome,
        String email,
        String disciplina
) {
}

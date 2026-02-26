package com.weg.gestao_escolar.dto.turma;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record TurmaResponseDto(
        int id,
        String nome,
        String nomeCurso,
        String nomeProfessor,
        List<String> nomesAlunos
) {
}

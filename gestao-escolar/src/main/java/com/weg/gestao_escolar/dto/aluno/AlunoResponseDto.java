package com.weg.gestao_escolar.dto.aluno;


import java.time.LocalDate;

public record AlunoResponseDto(
        int id,
        String nome,
        String email,
        String matricula,
        LocalDate data_nascimento
) {
}

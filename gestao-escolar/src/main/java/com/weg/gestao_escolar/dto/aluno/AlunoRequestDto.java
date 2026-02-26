package com.weg.gestao_escolar.dto.aluno;

import java.time.LocalDate;

public record AlunoRequestDto(
        String nome,
        String email,
        String matricula,
        LocalDate data_nascimento
) {
}

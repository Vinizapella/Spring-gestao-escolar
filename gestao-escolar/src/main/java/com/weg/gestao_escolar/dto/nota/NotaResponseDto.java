package com.weg.gestao_escolar.dto.nota;

public record NotaResponseDto(
        int id,
        String alunoNome,
        String aulaAssunto,
        Double valor
) {
}

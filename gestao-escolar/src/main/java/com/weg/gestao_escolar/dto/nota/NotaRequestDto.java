package com.weg.gestao_escolar.dto.nota;

public record NotaRequestDto(
        int alunoId,
        int aulaId,
        Double valor
) {
}

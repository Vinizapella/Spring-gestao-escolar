package com.weg.gestao_escolar.dto.nota;

import jakarta.validation.constraints.*;

public record NotaRequestDto(
        @NotNull(message = "o id nao poder ser nulo")
        @Min(value = 1, message = "O id deve ser valido")
        int alunoId,
        @NotNull(message = "o id nao poder ser nulo")
        @Min(value = 1, message = "O id deve ser valido")
        int aulaId,
        @NotNull(message = "O valor nao pode ser nulo")
        @PositiveOrZero(message = "O valor nao pode ser negativa")
        @Max(value = 10, message = "A nota máxima permitida é 10")
        Double valor
) {
}

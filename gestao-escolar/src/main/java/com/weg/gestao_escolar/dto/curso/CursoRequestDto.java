package com.weg.gestao_escolar.dto.curso;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CursoRequestDto(
        @NotBlank(message = "Nome é obrigatorio")
        @Size(min = 5, max = 100, message = "O minimo é 5 o maximo é 100")
        String nome,
        @NotBlank(message = "O codigo não pode ser deixado em branco")
        @Size(min = 2, max = 20, message = "O minimo de caracteres é 2 o maximo é 20")
        String codigo
) {
}

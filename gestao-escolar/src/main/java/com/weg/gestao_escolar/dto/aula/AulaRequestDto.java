package com.weg.gestao_escolar.dto.aula;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDateTime;

public record AulaRequestDto(
        @NotNull(message = "Obrigatorio a turma")
        @Min(value = 1, message = "Id deve ser valido")
        int turmaId,
        @NotNull(message = "a hora nao pode ser nula")
        LocalDateTime dataHora,
        @NotBlank(message = "Assunto nao pode ser em branco")
        String assunto
) {
}

package com.weg.gestao_escolar.dto.turma;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record TurmaRequestDto(
        @NotBlank(message = "nome da turma nao pode ser invalido")
        @Size(min = 1, max = 50, message = "o minimo é 1 o maximo é 50")
        String nome,
        @NotNull(message = "nao pode ser nula")
        int cursoId,
        @NotNull(message = "nao pode ser nula")
        int professorId,
        @NotEmpty(message = "A lista de alunos não pode estar vazia")
        List<Integer> alunoIds
) {
}

package com.weg.gestao_escolar.dto.professor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProfessorRequestDto(
        @NotBlank(message = "Nome nao pode ser invalido")
        @Size(min = 3, max = 50, message = "O nome deve ter no minimo ")
        String nome,
        @NotBlank(message = "Email é obrigatorio")
        @Email(message = "Email invalido")
        String email,
        @NotBlank(message = "Nome nao pode ser invalido")
        @Size(min = 5, max = 100, message = "O nome deve ter no minimo ")
        String disciplina
) {
}

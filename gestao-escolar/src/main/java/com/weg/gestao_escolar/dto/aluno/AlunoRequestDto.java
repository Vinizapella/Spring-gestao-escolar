package com.weg.gestao_escolar.dto.aluno;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record AlunoRequestDto(
        @NotBlank(message = "Nome é Obrigatorio")
        @Size(min = 3, max = 50, message = "o minimo de caracteres é de 3 e o maximo é 50")
        String nome,
        @NotBlank(message = "O email e obrigatorio")
        @Email(message = "Email invalido")
        String email,
        @NotBlank(message = "Matricula nao pode estar em branco")
        String matricula,
        @NotNull(message = "Obrigatorio ter data de nascimento")
        @Past(message = "A data de nascimento deve ser de uma data passada")
        LocalDate data_nascimento
) {
}

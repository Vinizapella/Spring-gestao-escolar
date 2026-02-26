package com.weg.gestao_escolar.dto.aula;

import java.time.LocalDateTime;

public record AulaRequestDto(
        int turmaId,
        LocalDateTime dataHora,
        String assunto
) {
}

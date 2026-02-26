package com.weg.gestao_escolar.mapper;

import com.weg.gestao_escolar.dto.aula.AulaRequestDto;
import com.weg.gestao_escolar.dto.aula.AulaResponseDto;
import com.weg.gestao_escolar.model.Aula;
import org.springframework.stereotype.Component;

@Component
public class AulaMapper {
    public Aula toEntity(
            AulaRequestDto dto
    ) {
        return new Aula(
                dto.turmaId(), dto.dataHora(), dto.assunto()
        );
    }

    public AulaResponseDto toResponse(
            Aula aula, String nomeTurma
    ) {
        return new AulaResponseDto(
                aula.getId(),
                nomeTurma,
                aula.getDataHora(),
                aula.getAssunto()
        );
    }
}

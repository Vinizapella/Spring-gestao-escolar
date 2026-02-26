package com.weg.gestao_escolar.mapper;

import com.weg.gestao_escolar.dto.nota.NotaRequestDto;
import com.weg.gestao_escolar.dto.nota.NotaResponseDto;
import com.weg.gestao_escolar.model.Nota;
import org.springframework.stereotype.Component;

@Component
public class NotaMapper {
    public Nota toEntity(
            NotaRequestDto dto
    ) {
        return new Nota(dto.alunoId(), dto.aulaId(), dto.valor());
    }

    public NotaResponseDto toResponse(
            Nota nota, String alunoNome, String aulaAssunto
    ) {
        return new NotaResponseDto(
                nota.getId(),
                alunoNome,
                aulaAssunto,
                nota.getValor()
        );
    }
}
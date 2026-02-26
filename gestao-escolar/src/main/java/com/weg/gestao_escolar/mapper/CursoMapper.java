package com.weg.gestao_escolar.mapper;

import com.weg.gestao_escolar.dto.curso.CursoRequestDto;
import com.weg.gestao_escolar.dto.curso.CursoResponseDto;
import com.weg.gestao_escolar.model.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public Curso toEntity(
            CursoRequestDto cursoRequestDto
    ){
        return new Curso(
                cursoRequestDto.nome(),
                cursoRequestDto.codigo()
        );
    }

    public CursoResponseDto toResponse(
            Curso curso
    ){
        return new CursoResponseDto(
                curso.getId(),
                curso.getNome(),
                curso.getCodigo()
        );
    }
}

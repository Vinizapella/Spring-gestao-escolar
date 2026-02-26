package com.weg.gestao_escolar.service;

import com.weg.gestao_escolar.dto.professor.ProfessorRequestDto;
import com.weg.gestao_escolar.dto.professor.ProfessorResponseDto;
import com.weg.gestao_escolar.mapper.ProfessorMapper;
import com.weg.gestao_escolar.model.Professor;
import com.weg.gestao_escolar.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    public ProfessorService(
            ProfessorRepository professorRepository,
            ProfessorMapper professorMapper
    ){
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    public ProfessorResponseDto salvaProfessor(ProfessorRequestDto professorRequestDto){

        try {
            Professor professor = professorMapper.toEntity(professorRequestDto);
            Professor professorSalva = professorRepository.criarProfessor(professor);
            ProfessorResponseDto professorResponseDto = professorMapper.toResponse(professorSalva);
            return professorResponseDto;
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

}

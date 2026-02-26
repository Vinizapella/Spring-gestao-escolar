package com.weg.gestao_escolar.controller;

import com.weg.gestao_escolar.dto.professor.ProfessorRequestDto;
import com.weg.gestao_escolar.dto.professor.ProfessorResponseDto;
import com.weg.gestao_escolar.service.ProfessorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService){
        this.professorService = professorService;
    }

    @PostMapping
    public ProfessorResponseDto criaProfessor(
            @RequestBody ProfessorRequestDto professorRequestDto){
        return professorService.salvaProfessor(professorRequestDto);
    }

}

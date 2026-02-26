package com.weg.gestao_escolar.controller;

import com.weg.gestao_escolar.dto.professor.ProfessorRequestDto;
import com.weg.gestao_escolar.dto.professor.ProfessorResponseDto;
import com.weg.gestao_escolar.service.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<ProfessorResponseDto> listaProfessores(){
        return professorService.listaProfessor();
    }

    @GetMapping("/{id}")
    public ProfessorResponseDto buscaId(
            @PathVariable int id
    ){
        return professorService.buscaID(id);
    }

    @PutMapping("/{id}")
    public ProfessorResponseDto atualizaProfessor(
            @RequestBody ProfessorRequestDto professorRequestDto,
            @PathVariable int id
    ){
        return professorService.atualiza(professorRequestDto, id);
    }
}

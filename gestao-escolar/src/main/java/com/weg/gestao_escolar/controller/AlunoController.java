package com.weg.gestao_escolar.controller;

import com.weg.gestao_escolar.dto.aluno.AlunoRequestDto;
import com.weg.gestao_escolar.dto.aluno.AlunoResponseDto;
import com.weg.gestao_escolar.service.AlunoService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping ("/aluno")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService){
        this.alunoService = alunoService;
    }

    @PutMapping
    public AlunoResponseDto criarAluno(
            @RequestBody AlunoRequestDto alunoRequestDto
    ){
        try {
            return alunoService.salvarAluno(alunoRequestDto);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}

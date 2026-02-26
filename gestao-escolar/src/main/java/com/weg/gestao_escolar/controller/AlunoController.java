package com.weg.gestao_escolar.controller;

import com.weg.gestao_escolar.dto.aluno.AlunoRequestDto;
import com.weg.gestao_escolar.dto.aluno.AlunoResponseDto;
import com.weg.gestao_escolar.service.AlunoService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping ("/aluno")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService){
        this.alunoService = alunoService;
    }

    @PostMapping
    public AlunoResponseDto criarAluno(
            @RequestBody AlunoRequestDto alunoRequestDto
    ){
            return alunoService.salvarAluno(alunoRequestDto);
    }

    @GetMapping
    public List<AlunoResponseDto> listarAlunos(){
            return alunoService.listarAlunos();
    }

    @GetMapping("/{id}")
    public AlunoResponseDto procuraId(
            @PathVariable int id
    ){
        return alunoService.procuraId(id);
    }

    @PutMapping("/{id}")
    public AlunoResponseDto atualizaAluno(
            @PathVariable int id,
            @RequestBody AlunoRequestDto alunoRequestDto
    ){
        return alunoService.atualizacao(alunoRequestDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteAluno(
            @PathVariable int id
    ){
        alunoService.deletarAluno(id);
    }
}

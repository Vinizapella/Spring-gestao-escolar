package com.weg.gestao_escolar.controller;

import com.weg.gestao_escolar.dto.curso.CursoRequestDto;
import com.weg.gestao_escolar.dto.curso.CursoResponseDto;
import com.weg.gestao_escolar.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public CursoResponseDto criarCurso(
            @Valid @RequestBody CursoRequestDto cursoRequestDto
    ) {
        return cursoService.salvarCurso(cursoRequestDto);
    }

    @GetMapping
    public List<CursoResponseDto> listarCursos() {
        return cursoService.listarCursos();
    }

    @GetMapping("/{id}")
    public CursoResponseDto buscarPorId(
            @PathVariable int id
    ) {
        return cursoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public CursoResponseDto atualizarCurso(
            @PathVariable int id,
            @Valid @RequestBody CursoRequestDto cursoRequestDto
    ) {
        return cursoService.atualizar(cursoRequestDto, id);
    }

    @DeleteMapping("/{id}")
    public void deletarCurso(
            @PathVariable int id
    ) {
        cursoService.deletar(id);
    }
}
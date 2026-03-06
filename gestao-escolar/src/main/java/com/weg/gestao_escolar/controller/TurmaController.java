package com.weg.gestao_escolar.controller;

import com.weg.gestao_escolar.dto.turma.TurmaRequestDto;
import com.weg.gestao_escolar.dto.turma.TurmaResponseDto;
import com.weg.gestao_escolar.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping
    public TurmaResponseDto criar(
            @Valid @RequestBody TurmaRequestDto dto
    ) {
        return turmaService.salvar(dto);
    }

    @GetMapping
    public List<TurmaResponseDto> listar() {
        return turmaService.listar();
    }

    @GetMapping("/{id}")
    public TurmaResponseDto buscar(
            @PathVariable int id
    ) {
        return turmaService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(
            @PathVariable int id
    ) {
        turmaService.deletar(id);
    }
}
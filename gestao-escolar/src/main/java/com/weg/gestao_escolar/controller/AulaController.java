package com.weg.gestao_escolar.controller;

import com.weg.gestao_escolar.dto.aula.AulaRequestDto;
import com.weg.gestao_escolar.dto.aula.AulaResponseDto;
import com.weg.gestao_escolar.service.AulaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aula")
public class AulaController {

    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @PostMapping
    public AulaResponseDto criar(
            @Valid @RequestBody AulaRequestDto dto
    ) {
        return aulaService.salvarAula(dto);
    }

    @GetMapping
    public List<AulaResponseDto> listar() {
        return aulaService.listarAulas();
    }

    @GetMapping("/{id}")
    public AulaResponseDto buscarPorId(
            @PathVariable int id
    ) {
        return aulaService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public AulaResponseDto atualizar(
            @PathVariable int id,
            @Valid @RequestBody AulaRequestDto dto
    ) {
        return aulaService.atualizar(dto, id);
    }

    @DeleteMapping("/{id}")
    public void deletar(
            @PathVariable int id
    ) {
        aulaService.deletar(id);
    }
}
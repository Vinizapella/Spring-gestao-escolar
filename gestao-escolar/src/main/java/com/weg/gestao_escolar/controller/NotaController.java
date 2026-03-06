package com.weg.gestao_escolar.controller;

import com.weg.gestao_escolar.dto.nota.NotaRequestDto;
import com.weg.gestao_escolar.dto.nota.NotaResponseDto;
import com.weg.gestao_escolar.service.NotaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nota")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @PostMapping
    public NotaResponseDto criar(@RequestBody NotaRequestDto dto) {
        return notaService.salvarNota(dto);
    }

    @GetMapping
    public List<NotaResponseDto> listar() {
        return notaService.listarNotas();
    }

    @GetMapping("/{id}")
    public NotaResponseDto buscar(@PathVariable int id) {
        return notaService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable int id) {
        notaService.deletarNota(id);
    }
}
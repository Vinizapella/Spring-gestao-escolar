package com.weg.gestao_escolar.service;

import com.weg.gestao_escolar.dto.curso.CursoRequestDto;
import com.weg.gestao_escolar.dto.curso.CursoResponseDto;
import com.weg.gestao_escolar.mapper.CursoMapper;
import com.weg.gestao_escolar.model.Curso;
import com.weg.gestao_escolar.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CursoService {

    private final CursoMapper cursoMapper;
    private final CursoRepository cursoRepository;

    public CursoService(CursoMapper cursoMapper, CursoRepository cursoRepository) {
        this.cursoMapper = cursoMapper;
        this.cursoRepository = cursoRepository;
    }

    public CursoResponseDto salvarCurso(CursoRequestDto cursoRequestDto) {
        if (cursoRequestDto.nome() == null || cursoRequestDto.nome().trim().isEmpty()) {
            throw new RuntimeException("O nome do curso não pode ser vazio");
        }

        try {
            Curso curso = cursoMapper.toEntity(cursoRequestDto);
            Curso cursoSalvo = cursoRepository.criaCurso(curso);
            return cursoMapper.toResponse(cursoSalvo);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar curso: " + e.getMessage());
        }
    }

    public List<CursoResponseDto> listarCursos() {
        try {
            List<Curso> cursosBanco = cursoRepository.cursos();
            List<CursoResponseDto> listaDto = new ArrayList<>();
            for (Curso c : cursosBanco) {
                listaDto.add(cursoMapper.toResponse(c));
            }
            return listaDto;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar cursos: " + e.getMessage());
        }
    }

    public CursoResponseDto buscarPorId(int id) {
        try {
            Curso curso = cursoRepository.buscaId(id);
            if (curso == null) {
                throw new RuntimeException("Curso não encontrado com o ID: " + id);
            }
            return cursoMapper.toResponse(curso);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar curso: " + e.getMessage());
        }
    }

    public CursoResponseDto atualizar(CursoRequestDto cursoRequestDto, int id) {
        try {
            Curso curso = cursoMapper.toEntity(cursoRequestDto);
            curso.setId(id);
            cursoRepository.atualizaCurso(curso);
            return cursoMapper.toResponse(curso);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar curso: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        try {
            cursoRepository.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar curso: " + e.getMessage());
        }
    }
}
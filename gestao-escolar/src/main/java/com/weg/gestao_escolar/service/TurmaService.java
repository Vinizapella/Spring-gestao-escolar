package com.weg.gestao_escolar.service;

import com.weg.gestao_escolar.dto.turma.TurmaRequestDto;
import com.weg.gestao_escolar.dto.turma.TurmaResponseDto;
import com.weg.gestao_escolar.mapper.TurmaMapper;
import com.weg.gestao_escolar.model.Turma;
import com.weg.gestao_escolar.repository.TurmaRepository;
import com.weg.gestao_escolar.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final TurmaMapper turmaMapper;
    private final CursoRepository cursoRepository;

    public TurmaService(TurmaRepository turmaRepository, TurmaMapper turmaMapper, CursoRepository cursoRepository) {
        this.turmaRepository = turmaRepository;
        this.turmaMapper = turmaMapper;
        this.cursoRepository = cursoRepository;
    }

    public TurmaResponseDto salvar(TurmaRequestDto dto) {
        try {
            Turma turma = turmaMapper.toEntity(dto);
            Turma turmaSalva = turmaRepository.criarTurma(turma);

            String nomeCurso = cursoRepository.buscaId(dto.cursoId()).getNome();
            String nomeProfessor = "Professor Teste";
            List<String> nomesAlunos = new ArrayList<>();

            return turmaMapper.toResponse(turmaSalva, nomeCurso, nomeProfessor, nomesAlunos);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar turma: " + e.getMessage());
        }
    }

    public List<TurmaResponseDto> listar() {
        try {
            List<Turma> turmas = turmaRepository.listarTurmas();
            List<TurmaResponseDto> listaDto = new ArrayList<>();

            for (Turma t : turmas) {
                listaDto.add(turmaMapper.toResponse(t, "Curso ", "Professor ", List.of("Aluno ", "Aluno ")));
            }
            return listaDto;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar turmas: " + e.getMessage());
        }
    }

    public TurmaResponseDto buscarPorId(int id) {
        try {
            Turma t = turmaRepository.listarPorId(id);
            if (t == null) throw new RuntimeException("Turma não encontrada");
            return turmaMapper.toResponse(t, "Curso ", "Professor ", List.of());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deletar(int id) {
        try {
            turmaRepository.deletaTurma(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
package com.weg.gestao_escolar.service;

import com.weg.gestao_escolar.dto.aula.AulaRequestDto;
import com.weg.gestao_escolar.dto.aula.AulaResponseDto;
import com.weg.gestao_escolar.mapper.AulaMapper;
import com.weg.gestao_escolar.model.Aula;
import com.weg.gestao_escolar.model.Turma;
import com.weg.gestao_escolar.repository.AulaRepository;
import com.weg.gestao_escolar.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AulaService {

    private final AulaRepository aulaRepository;
    private final TurmaRepository turmaRepository;
    private final AulaMapper aulaMapper;

    public AulaService(AulaRepository aulaRepository, TurmaRepository turmaRepository, AulaMapper aulaMapper) {
        this.aulaRepository = aulaRepository;
        this.turmaRepository = turmaRepository;
        this.aulaMapper = aulaMapper;
    }

    public AulaResponseDto salvarAula(AulaRequestDto dto) {
        try {
            Turma turma = turmaRepository.listarPorId(dto.turmaId());
            if (turma == null) {
                throw new RuntimeException("Turma não encontrada para o ID: " + dto.turmaId());
            }

            Aula aula = aulaMapper.toEntity(dto);
            Aula aulaSalva = aulaRepository.criarAula(aula);
            return aulaMapper.toResponse(aulaSalva, turma.getNome());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar aula " + e.getMessage());
        }
    }

    public List<AulaResponseDto> listarAulas() {
        try {
            List<Aula> aulasNoBanco = aulaRepository.listarAulas();
            List<AulaResponseDto> listaDto = new ArrayList<>();

            for (Aula a : aulasNoBanco) {
                Turma turma = turmaRepository.listarPorId(a.getTurmaId());
                String nomeTurma = (turma != null) ? turma.getNome() : "Turma não encontrada";
                listaDto.add(aulaMapper.toResponse(a, nomeTurma));
            }
            return listaDto;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar aulas: " + e.getMessage());
        }
    }

    public AulaResponseDto buscarPorId(int id) {
        try {
            Aula aula = aulaRepository.listarPorId(id);
            if (aula == null) {
                throw new RuntimeException("Aula não encontrada");
            }
            Turma turma = turmaRepository.listarPorId(aula.getTurmaId());
            return aulaMapper.toResponse(aula, turma.getNome());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public AulaResponseDto atualizar(AulaRequestDto dto, int id) {
        try {
            Turma turma = turmaRepository.listarPorId(dto.turmaId());
            if (turma == null) throw new RuntimeException("Turma inválida");

            Aula aula = aulaMapper.toEntity(dto);
            aula.setId(id);
            aulaRepository.atualizaAula(aula);
            return aulaMapper.toResponse(aula, turma.getNome());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deletar(int id) {
        try {
            aulaRepository.deletaAula(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
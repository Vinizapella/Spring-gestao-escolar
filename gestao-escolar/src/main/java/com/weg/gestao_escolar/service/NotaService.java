package com.weg.gestao_escolar.service;

import com.weg.gestao_escolar.dto.nota.NotaRequestDto;
import com.weg.gestao_escolar.dto.nota.NotaResponseDto;
import com.weg.gestao_escolar.mapper.NotaMapper;
import com.weg.gestao_escolar.model.Aluno;
import com.weg.gestao_escolar.model.Aula;
import com.weg.gestao_escolar.model.Nota;
import com.weg.gestao_escolar.repository.AlunoRepository;
import com.weg.gestao_escolar.repository.AulaRepository;
import com.weg.gestao_escolar.repository.NotaRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotaService {

    private final NotaRepository notaRepository;
    private final AlunoRepository alunoRepository;
    private final AulaRepository aulaRepository;
    private final NotaMapper notaMapper;

    public NotaService(NotaRepository notaRepository, AlunoRepository alunoRepository,
                       AulaRepository aulaRepository, NotaMapper notaMapper) {
        this.notaRepository = notaRepository;
        this.alunoRepository = alunoRepository;
        this.aulaRepository = aulaRepository;
        this.notaMapper = notaMapper;
    }

    public NotaResponseDto salvarNota(NotaRequestDto dto) {
        try {
            Aluno aluno = alunoRepository.listarPorId(dto.alunoId());
            Aula aula = aulaRepository.listarPorId(dto.aulaId());

            if (aluno == null || aula == null) {
                throw new RuntimeException("Aluno ou Aula não encontrados");
            }

            Nota nota = notaMapper.toEntity(dto);
            Nota notaSalva = notaRepository.criarNota(nota);

            return notaMapper.toResponse(notaSalva, aluno.getNome(), aula.getAssunto());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar nota: " + e.getMessage());
        }
    }

    public List<NotaResponseDto> listarNotas() {
        try {
            List<Nota> notasBanco = notaRepository.listarNotas();
            List<NotaResponseDto> listaDto = new ArrayList<>();

            for (Nota n : notasBanco) {
                Aluno aluno = alunoRepository.listarPorId(n.getAluno_id());
                Aula aula = aulaRepository.listarPorId(n.getAula_id());

                String nome = (aluno != null) ? aluno.getNome() : "Desconhecido";
                String assunto = (aula != null) ? aula.getAssunto() : "Desconhecido";

                listaDto.add(notaMapper.toResponse(n, nome, assunto));
            }
            return listaDto;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar notas: " + e.getMessage());
        }
    }

    public NotaResponseDto buscarPorId(int id) {
        try {
            Nota nota = notaRepository.listarPorId(id);
            if (nota == null) throw new RuntimeException("Nota não encontrada.");

            Aluno aluno = alunoRepository.listarPorId(nota.getAluno_id());
            Aula aula = aulaRepository.listarPorId(nota.getAula_id());

            return notaMapper.toResponse(nota, aluno.getNome(), aula.getAssunto());
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deletarNota(int id) {
        try {
            notaRepository.deletaNota(id);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
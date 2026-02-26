package com.weg.gestao_escolar.service;

import com.weg.gestao_escolar.dto.aluno.AlunoRequestDto;
import com.weg.gestao_escolar.dto.aluno.AlunoResponseDto;
import com.weg.gestao_escolar.mapper.AlunoMapper;
import com.weg.gestao_escolar.model.Aluno;
import com.weg.gestao_escolar.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    private final AlunoMapper alunoMapper;
    private final AlunoRepository alunoRepository;

    public AlunoService(
            AlunoMapper alunoMapper,
            AlunoRepository alunoRepository
    ){
        this.alunoMapper = alunoMapper;
        this.alunoRepository = alunoRepository;
    }

    public AlunoResponseDto salvarAluno(AlunoRequestDto alunoRequestDto){
        try {
            Aluno aluno= alunoMapper.toEntity(alunoRequestDto);
            Aluno alunoSalvo = alunoRepository.criarAluno(aluno);
            AlunoResponseDto alunoResponseDto = alunoMapper.toResponse(alunoSalvo);
            return alunoResponseDto;
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<AlunoResponseDto>listarAlunos(){
        try {
            List<Aluno>alunosDoBanco = alunoRepository.listarAlunos();
            List<AlunoResponseDto> listaDto = new ArrayList<>();
            for (Aluno a : alunosDoBanco){
                AlunoResponseDto dto = alunoMapper.toResponse(a);
                listaDto.add(dto);
            }
            return listaDto;
        }catch (SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

}

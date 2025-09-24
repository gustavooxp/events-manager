package com.senai.eventsmanager.service;

import com.senai.eventsmanager.dto.InscricaoCreateDTO;
import com.senai.eventsmanager.entity.Inscricao;
import com.senai.eventsmanager.repository.InscricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InscricaoService {
    @Autowired
    InscricaoRepository repository;
    //procurar uma inscrição pelo seu Id
    public InscricaoCreateDTO findById(UUID id){
        Inscricao inscricao = repository.findById(id).orElseThrow();
        InscricaoCreateDTO inscricaoDto = convertToDto(inscricao);
        return inscricaoDto;
    }
    //método para salvar uma inscrição
    public InscricaoCreateDTO save(InscricaoCreateDTO inscricaoDto){
        Inscricao inscricao = convertToEntity(inscricaoDto);
        inscricao.setDataHora(LocalDateTime.now());
        inscricao = repository.save(inscricao);
        return convertToDto(inscricao);
    }
    //método para atualizar uma inscrição
    public InscricaoCreateDTO update(UUID id, InscricaoCreateDTO inscricaoDto){
        Inscricao inscricao = convertToEntity(inscricaoDto);
        inscricao.setId(id);
        inscricao = repository.save(inscricao);
        return convertToDto(inscricao);
    }
    //método para deletar uma inscição
    public void deleteById(UUID id){
        repository.deleteById(id);
    }
    //método para listar todas as inscrições
    public List<InscricaoCreateDTO> findAll(){
        List<Inscricao> inscricoes = repository.findAll();
        List<InscricaoCreateDTO> inscricoesDto = new ArrayList<>();
        for(Inscricao inscricao : inscricoes){
            inscricoesDto.add(convertToDto(inscricao));
        }
        return inscricoesDto;
    }

    //método para converter uma inscrição para DTO
    public InscricaoCreateDTO convertToDto(Inscricao inscricao){
        InscricaoCreateDTO inscricaoDto = new InscricaoCreateDTO();
        inscricaoDto.setUsuario(inscricao.getUsuario());
        inscricaoDto.setEvento(inscricao.getEvento());
        inscricaoDto.setDataHora(inscricao.getDataHora());
        inscricaoDto.setStatusPagamento(inscricao.getStatusPagamento());
        return inscricaoDto;
    }
    //método para converter um DTO para entidade
    public Inscricao convertToEntity(InscricaoCreateDTO inscricaoDto){
        Inscricao inscricao = new Inscricao();
        inscricao.setUsuario(inscricaoDto.getUsuario());
        inscricao.setEvento(inscricaoDto.getEvento());
        inscricao.setDataHora(inscricaoDto.getDataHora());
        inscricao.setStatusPagamento(inscricaoDto.getStatusPagamento());
        return inscricao;
    }
}
package com.senai.eventsmanager.service;

import com.senai.eventsmanager.dto.EventoDTO;
import com.senai.eventsmanager.dto.InscricaoDTO;
import com.senai.eventsmanager.entity.Evento;
import com.senai.eventsmanager.entity.Inscricao;
import com.senai.eventsmanager.entity.Usuario;
import com.senai.eventsmanager.repository.EventoRepository;
import com.senai.eventsmanager.repository.InscricaoRepository;
import com.senai.eventsmanager.repository.UsuarioRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InscricaoService {
    @Autowired
    InscricaoRepository repository;

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    // método para converter uma inscrição para DTO
    public InscricaoDTO toDto(Inscricao inscricao) {
        InscricaoDTO dto = new InscricaoDTO();
        BeanUtils.copyProperties(inscricao, dto);
        return dto;
    }

    // método para converter um DTO para entidade
    public Inscricao toEntity(InscricaoDTO dto) {
        Inscricao inscricao = new Inscricao();
        BeanUtils.copyProperties(dto, inscricao);
        return inscricao;
    }

    // procurar uma inscrição pelo seu Id
    public InscricaoDTO findById(Long id) {
        Inscricao inscricao = repository.findById(id).orElseThrow();
        InscricaoDTO inscricaoDto = toDto(inscricao);
        return inscricaoDto;
    }

    // método para salvar uma inscrição
    public InscricaoDTO save(InscricaoDTO dto) {
    Evento evento = eventoRepository.findById(dto.getEventoId())
            .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

    Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    Inscricao inscricao = new Inscricao();
    inscricao.setEvento(evento);
    inscricao.setUsuario(usuario);

    inscricao = repository.save(inscricao);

    dto.setId(inscricao.getId());
    return dto;
}


    // método para atualizar uma inscrição
    public InscricaoDTO update(Long id, InscricaoDTO inscricaoDto) {
        Inscricao inscricao = toEntity(inscricaoDto);
        inscricao.setId(id);
        inscricao = repository.save(inscricao);
        return toDto(inscricao);
    }

    // método para deletar uma inscição
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // método para listar todas as inscrições
    public List<InscricaoDTO> findAll() {
        List<Inscricao> inscricoes = repository.findAll();
        List<InscricaoDTO> inscricoesDto = new ArrayList<>();
        for (Inscricao inscricao : inscricoes) {
            inscricoesDto.add(toDto(inscricao));
        }
        return inscricoesDto;
    }

}
package com.senai.eventsmanager.service;

import com.senai.eventsmanager.dto.EventoCreateDTO;
import com.senai.eventsmanager.entity.Evento;
import com.senai.eventsmanager.entity.EventoEnum;
import com.senai.eventsmanager.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public EventoCreateDTO findById(UUID id) {
        //retorna um entidade Evento
        Evento evento = eventoRepository.findById(id).orElseThrow();
        //método para converter um evento entity em eventoCreateDto
        EventoCreateDTO eventoCreateDTO = convertToDto(evento);
        return eventoCreateDTO;
    }
    //método para salvar um evento
    public EventoCreateDTO save(EventoCreateDTO eventoDto){
        Evento evento = convertToEntity(eventoDto);
        evento = eventoRepository.save(evento);
        return convertToDto(evento);
    }

    //método para atualizar um evento
    public EventoCreateDTO update(UUID id, EventoCreateDTO eventoDto){
        Evento evento = convertToEntity(eventoDto);
        evento.setId(id);
        evento = eventoRepository.save(evento);
        return convertToDto(evento);
    }
    //método para deletar um evento
    public void deleteById(UUID id){
        eventoRepository.deleteById(id);
    }
    //método para listar todos os eventos
    public List<EventoCreateDTO> findAll(){
        List<Evento> eventos = eventoRepository.findAll();
        //criar lista de eventoCreateDTO
        List<EventoCreateDTO> eventosDTO = new ArrayList<>();
        //para cada evento na lista de eventos, converter para Dto e add na lista dto
        for(Evento evento : eventos){
            eventosDTO.add(convertToDto(evento));
        }
        return eventosDTO;
    }

    public EventoCreateDTO convertToDto(Evento evento){
        EventoCreateDTO eventoCreateDTO = new EventoCreateDTO();
        //pegar os dados de evento e passar para o eventoCreateDto
        eventoCreateDTO.setNome(evento.getNome());
        eventoCreateDTO.setDescricao(evento.getDescricao());
        eventoCreateDTO.setTipo(evento.getTipo());
        eventoCreateDTO.setLocal(evento.getLocal());
        eventoCreateDTO.setData_inicio(evento.getData_inicio());
        eventoCreateDTO.setData_final(evento.getData_final());
        eventoCreateDTO.setLinkEvento(evento.getLinkEvento());
        eventoCreateDTO.setLinkImagem(evento.getLinkImagem());
        return eventoCreateDTO;
    }
    public Evento convertToEntity(EventoCreateDTO eventoCreateDTO){
        Evento evento = new Evento();
        evento.setNome(eventoCreateDTO.getNome());
        evento.setDescricao(eventoCreateDTO.getDescricao());
        evento.setTipo(eventoCreateDTO.getTipo());
        evento.setLocal(eventoCreateDTO.getLocal());
        evento.setData_inicio(eventoCreateDTO.getData_inicio());
        evento.setData_final(eventoCreateDTO.getData_final());
        evento.setLinkEvento(eventoCreateDTO.getLinkEvento());
        evento.setLinkImagem(eventoCreateDTO.getLinkImagem());
        return evento;
    }
}
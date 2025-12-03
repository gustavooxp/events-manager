package com.senai.eventsmanager.service;

import com.senai.eventsmanager.dto.EventoDTO;
import com.senai.eventsmanager.entity.Evento;
import com.senai.eventsmanager.enums.EventoEnum;
import com.senai.eventsmanager.repository.EventoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public EventoDTO toDto(Evento evento) {
        EventoDTO dto = new EventoDTO();
        BeanUtils.copyProperties(evento, dto);
        return dto;
    }


    public Evento toEntity(EventoDTO dto) {
        Evento evento = new Evento();
        BeanUtils.copyProperties(dto, evento);
        return evento;

    }

    public List<EventoDTO> calendario(String inicio, String dataFinal) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

        LocalDateTime inicioFormatado = LocalDate.parse(inicio, formatter).atStartOfDay();
        LocalDateTime finalFormatado = LocalDate.parse(dataFinal, formatter).atStartOfDay();

        List<Evento> eventos = eventoRepository.calendario(inicioFormatado, finalFormatado);
        List<EventoDTO> eventosDTO = new ArrayList<>();

        for (Evento evento : eventos) {
            eventosDTO.add(toDto(evento));
        }
        return eventosDTO;

    }

    public EventoDTO findById(Long id) {
        Evento evento = eventoRepository.findById(id).orElseThrow();
        EventoDTO eventoDTO = toDto(evento);
        return eventoDTO;
    }

    public EventoDTO save(EventoDTO eventoDto) {
        Evento evento = toEntity(eventoDto);
        evento = eventoRepository.save(evento);
        return toDto(evento);
    }

    public EventoDTO update(Long id, EventoDTO eventoDto) {
        Evento evento = toEntity(eventoDto);
        evento.setId(id);
        evento = eventoRepository.save(evento);
        return toDto(evento);
    }

    public void deleteById(Long id) {
        eventoRepository.deleteById(id);
    }

    public List<EventoDTO> findAll() {
        List<Evento> eventos = eventoRepository.findAll();
        List<EventoDTO> eventosDTO = new ArrayList<>();
        for (Evento evento : eventos) {
            eventosDTO.add(toDto(evento));
        }
        return eventosDTO;
    }

    public List<EventoDTO> findByTipo(EventoEnum tipo) {
        List<Evento> eventos = eventoRepository.findByTipo(tipo);

        List<EventoDTO> eventosDTO = new ArrayList<>();
        for (Evento evento : eventos) {
            eventosDTO.add(toDto(evento));
        }
        return eventosDTO;
    }
}
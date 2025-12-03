package com.senai.eventsmanager.controller;

import com.senai.eventsmanager.dto.EventoDTO;
import com.senai.eventsmanager.dto.UsuarioDTO;
import com.senai.eventsmanager.enums.EventoEnum;
import com.senai.eventsmanager.enums.UsuarioEnum;
import com.senai.eventsmanager.service.EventoService;

import com.senai.eventsmanager.service.UsuarioService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/evento")

public class EventoController {
    @Autowired
    EventoService service;

    @Autowired
    private UsuarioService usuarioService;

    // pegar um evento pelo seu id
    @GetMapping("/{id}")
    public EventoDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    // pegar todos os eventos entre duas datas
    @GetMapping("/calendario/{dataInicio}/{dataFinal}")
    public List<EventoDTO> calendario(@PathVariable String dataInicio, @PathVariable String dataFinal) {
        return service.calendario(dataInicio, dataFinal);
    }

    // pegar todos os eventos por tipo
    @GetMapping("/filtro/{tipo}")
    public List<EventoDTO> findByTipo(@PathVariable("tipo") EventoEnum tipo) {
        return service.findByTipo(tipo);
    }

    // pegar todos os eventos
    @GetMapping
    public List<EventoDTO> findAll() {
        return service.findAll();
    }

    // salvar um evento
    @PostMapping
    public EventoDTO save(
            @Valid @RequestBody EventoDTO eventoDto) {
        return service.save(eventoDto);
    }

    // atualizar um evento
    @PutMapping("/{id}")
    public EventoDTO update(
            @PathVariable("id") Long id,
            @RequestBody EventoDTO eventoCreateDTO) {
        return service.update(id, eventoCreateDTO);
    }

    // deletar um evento pelo seu id
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {

        service.deleteById(id);
    }

}
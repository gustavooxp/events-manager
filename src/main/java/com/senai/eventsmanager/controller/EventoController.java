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

    @GetMapping("/{id}")
    public EventoDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping("/calendario/{dataInicio}/{dataFinal}")
    public List<EventoDTO> calendario(@PathVariable String dataInicio, @PathVariable String dataFinal) {
        return service.calendario(dataInicio, dataFinal);
    }

    @GetMapping("/filtro/{tipo}")
    public List<EventoDTO> findByTipo(@PathVariable("tipo") EventoEnum tipo) {
        return service.findByTipo(tipo);
    }

    @GetMapping
    public List<EventoDTO> findAll() {
        return service.findAll();
    }

    @PostMapping
    public EventoDTO save(
            @Valid @RequestBody EventoDTO eventoDto) {
        return service.save(eventoDto);
    }

    @PutMapping("/{id}")
    public EventoDTO update(
            @PathVariable("id") Long id,
            @RequestBody EventoDTO eventoCreateDTO) {
        return service.update(id, eventoCreateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {

        service.deleteById(id);
    }

}
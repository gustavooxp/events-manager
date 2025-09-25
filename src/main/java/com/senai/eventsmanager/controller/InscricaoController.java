package com.senai.eventsmanager.controller;

import com.senai.eventsmanager.dto.InscricaoDTO;
import com.senai.eventsmanager.entity.Inscricao;
import com.senai.eventsmanager.service.InscricaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inscricao")
public class InscricaoController {
    InscricaoService service;

    // pegar um inscricao pelo seu id
    @GetMapping("/{id}")
    public InscricaoDTO findById(@PathVariable("id") UUID id) {
        return service.findById(id);
    }

    // pegar todas as inscricoes
    @GetMapping
    public List<InscricaoDTO> findAll() {
        return service.findAll();
    }

    // salvar uma inscricao
    @PostMapping
    public InscricaoDTO save(
            @RequestBody InscricaoDTO inscricaoCreateDTO) {
        return service.save(inscricaoCreateDTO);
    }

    // atualizar uma inscricao
    @PutMapping("/{id}")
    public InscricaoDTO update(
            @PathVariable("id") UUID id,
            @RequestBody InscricaoDTO inscricaoCreateDTO) {
        return service.update(id, inscricaoCreateDTO);
    }

    // deletar um inscricao pelo seu id
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") UUID id) {
        service.deleteById(id);
    }

}
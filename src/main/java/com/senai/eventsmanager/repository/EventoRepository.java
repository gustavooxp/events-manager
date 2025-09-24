package com.senai.eventsmanager.repository;

import com.senai.eventsmanager.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EventoRepository extends
        JpaRepository<Evento, UUID> {
    //save, delete, update, findAll , findById

}

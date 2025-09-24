package com.senai.eventsmanager.repository;

import com.senai.eventsmanager.entity.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, UUID> {
//    save, delete, update, findAll, findById
}

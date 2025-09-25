package com.senai.eventsmanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.senai.eventsmanager.enums.UsuarioEnum;

@Entity
@Table(name = "usuario")
@Getter 
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, length = 100)
    private String senha;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 15)
    private String cpf;

    @Column(nullable = false, length = 15)
    private String telefone;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private UsuarioEnum tipo;

    @Column(nullable = false)
    private Date dataNascimento;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedBy
    @Column(nullable = true)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "usuario")
    private List<Inscricao> inscricoes;


}

package com.senai.eventsmanager.dto;

import com.senai.eventsmanager.entity.UsuarioEnum;
import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.Date;

public class UsuarioCreateDTO {
    private String email;
    private String senha;
    private String nome;
    private String cpf;
    private String telefone;
    private UsuarioEnum tipo;//usuario, organizador, adm
    private Date dataNascimento;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    //Construtores

    public UsuarioCreateDTO() {
    }

    public UsuarioCreateDTO(String email, String senha, String nome, String cpf, String telefone, UsuarioEnum tipo, Date dataNascimento) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.tipo = tipo;
        this.dataNascimento = dataNascimento;
    }

    //getters e setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public UsuarioEnum getTipo() {
        return tipo;
    }

    public void setTipo(UsuarioEnum tipo) {
        this.tipo = tipo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

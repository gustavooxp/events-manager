package com.senai.eventsmanager.service;

import com.senai.eventsmanager.dto.UsuarioDTO;
import com.senai.eventsmanager.entity.Usuario;
import com.senai.eventsmanager.enums.UsuarioEnum;
import com.senai.eventsmanager.repository.UsuarioRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder; 

    // método para converter uma inscrição para DTO
    public UsuarioDTO toDto(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        BeanUtils.copyProperties(usuario, dto);
        return dto;
    }

    // método para converter um DTO para entidade
    public Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(dto, usuario);
        return usuario;
    }

    // método para buscar um usuário pelo id
    public UsuarioDTO findById(Long id) {
        Usuario usuario = repository.findById(id).orElseThrow();
        return toDto(usuario);
    }

    // método para salvar um usuário
    public UsuarioDTO save(UsuarioDTO usuarioDto) {
        Usuario usuario = toEntity(usuarioDto);
        //CRIPTOGRAGAR SENHA
        String senhaCriptografada = passwordEncoder.encode(usuarioDto.getSenha());
        usuario.setSenha(senhaCriptografada);
        usuario = repository.save(usuario);
        return toDto(usuario);
    }

    // método para atualizar um usuário
    public UsuarioDTO update(Long id, UsuarioDTO usuarioDto) {
        Usuario usuario = toEntity(usuarioDto);
        usuario.setId(id);
        usuario = repository.save(usuario);
        return toDto(usuario);
    }

    // método para deletar um usuário
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    // método para listar todos os usuários
    public List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = repository.findAll();
        List<UsuarioDTO> usuariosDto = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuariosDto.add(toDto(usuario));
        }
        return usuariosDto;
    }

    // metodo para listar usuario por tipo
    public List<UsuarioDTO> findByTipo(UsuarioEnum tipo) {
        List<Usuario> usuarios = repository.findByTipo(tipo);

        List<UsuarioDTO> usuarioDtos = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuarioDtos.add(toDto(usuario));
        }
        return usuarioDtos;
    }
}
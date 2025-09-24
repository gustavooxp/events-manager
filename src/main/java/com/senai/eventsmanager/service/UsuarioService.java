package com.senai.eventsmanager.service;

import com.senai.eventsmanager.dto.InscricaoCreateDTO;
import com.senai.eventsmanager.dto.UsuarioCreateDTO;
import com.senai.eventsmanager.entity.Inscricao;
import com.senai.eventsmanager.entity.Usuario;
import com.senai.eventsmanager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository repository;

    //método para buscar um usuário pelo id
    public UsuarioCreateDTO findById(UUID id) {
        Usuario usuario = repository.findById(id).orElseThrow();
        return convertToDto(usuario);
    }
    //método para salvar um usuário
    public UsuarioCreateDTO save(UsuarioCreateDTO usuarioDto) {
        Usuario usuario = convertToEntity(usuarioDto);
        usuario.setCreatedAt(LocalDateTime.now());
        usuario.setUpdatedAt(LocalDateTime.now());
        usuario = repository.save(usuario);
        return convertToDto(usuario);
    }
    //método para atualizar um usuário
    public UsuarioCreateDTO update(UUID id, UsuarioCreateDTO usuarioDto) {
        Usuario usuario = convertToEntity(usuarioDto);
        usuario.setId(id);
        usuario = repository.save(usuario);
        return convertToDto(usuario);
    }
    //método para deletar um usuário
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
    //método para listar todos os usuários
    public List<UsuarioCreateDTO> findAll() {
        List<Usuario> usuarios = repository.findAll();
        List<UsuarioCreateDTO> usuariosDto = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuariosDto.add(convertToDto(usuario));
        }
        return usuariosDto;
    }
    //método para converter uma inscrição para DTO
    public UsuarioCreateDTO convertToDto(Usuario usuario) {
        UsuarioCreateDTO usuarioCreateDTO = new UsuarioCreateDTO();
        usuarioCreateDTO.setCpf(usuario.getCpf());
        usuarioCreateDTO.setEmail(usuario.getEmail());
        usuarioCreateDTO.setNome(usuario.getNome());
        usuarioCreateDTO.setSenha(usuario.getSenha());
        usuarioCreateDTO.setTelefone(usuario.getTelefone());
        usuarioCreateDTO.setDataNascimento(usuario.getDataNascimento());
        usuarioCreateDTO.setTipo(usuario.getTipo());
        return usuarioCreateDTO;
    }
    //método para converter um DTO para entidade
    public Usuario convertToEntity(UsuarioCreateDTO usuarioDto){
        Usuario usuario = new Usuario();
        usuario.setCpf(usuarioDto.getCpf());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setNome(usuarioDto.getNome());
        usuario.setSenha(usuarioDto.getSenha());
        usuario.setTelefone(usuarioDto.getTelefone());
        usuario.setDataNascimento(usuarioDto.getDataNascimento());
        usuario.setTipo(usuarioDto.getTipo());
        return usuario;
    }


}
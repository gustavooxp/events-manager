package com.senai.eventsmanager.service;

import com.senai.eventsmanager.config.SecurityConfig;
import com.senai.eventsmanager.dto.EventoUsuarioDTO;
import com.senai.eventsmanager.dto.InscricaoDTO;
import com.senai.eventsmanager.dto.UsuarioDTO;
import com.senai.eventsmanager.entity.Inscricao;
import com.senai.eventsmanager.entity.Usuario;
import com.senai.eventsmanager.enums.UsuarioEnum;
import com.senai.eventsmanager.repository.UsuarioRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private InscricaoService inscricaoService;

    @Autowired
    SecurityConfig securityConfig;
    public UsuarioDTO toDto(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        BeanUtils.copyProperties(usuario, dto);
        return dto;
    }

    public Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(dto, usuario);
        return usuario;
    }

    public UsuarioDTO findById(Long id) {
        Usuario usuario = repository.findById(id).orElseThrow();
        return toDto(usuario);
    }

    public UsuarioDTO save(UsuarioDTO usuarioDto) {
        Usuario usuario = toEntity(usuarioDto);
        String senhaCriptografada = passwordEncoder.encode(usuarioDto.getSenha());
        usuario.setSenha(senhaCriptografada);
        usuario = repository.save(usuario);
        return toDto(usuario);
    }

    public UsuarioDTO update(Long id, UsuarioDTO usuarioDto) {
        Usuario usuario = toEntity(usuarioDto);
        String senhaCriptografada = passwordEncoder.encode(usuarioDto.getSenha());
        usuario.setSenha(senhaCriptografada);
        usuario.setId(id);
        usuario = repository.save(usuario);
        return toDto(usuario);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = repository.findAll();
        List<UsuarioDTO> usuariosDto = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            UsuarioDTO dto = toDto(usuario);
            List<EventoUsuarioDTO> eventos = new ArrayList<>();

            for (Inscricao inscricao: usuario.getInscricoes()){
                EventoUsuarioDTO evento = new EventoUsuarioDTO();
                evento.setNome(inscricao.getEvento().getNome());
                evento.setDataInicio(inscricao.getEvento().getDataInicio());

                eventos.add(evento);
            }

            dto.setEventos(eventos);

            usuariosDto.add(dto);
        }
        return usuariosDto;
    }

    public List<UsuarioDTO> findByTipo(UsuarioEnum tipo) {
        List<Usuario> usuarios = repository.findByTipo(tipo);

        List<UsuarioDTO> usuarioDtos = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            usuarioDtos.add(toDto(usuario));
        }
        return usuarioDtos;
    }

    public Usuario autenticar(String email, String senhaDigitada) {

        Usuario usuario = repository.findByEmail(email);

        if (usuario != null && passwordEncoder.matches(senhaDigitada, usuario.getSenha())) {
            return usuario;
        }

        return null;
    }

    public Usuario findUsuarioComInscricoes(Long id) {
    return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
}


}

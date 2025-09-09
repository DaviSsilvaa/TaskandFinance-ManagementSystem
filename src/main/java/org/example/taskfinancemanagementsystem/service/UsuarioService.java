package org.example.taskfinancemanagementsystem.service;

import jakarta.transaction.Transactional;
import org.example.taskfinancemanagementsystem.dto.UsuarioDTO;
import org.example.taskfinancemanagementsystem.model.Usuario;
import org.example.taskfinancemanagementsystem.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> {
                    UsuarioDTO dto = new UsuarioDTO();
                    dto.setNome(usuario.getNome());
                    dto.setEmail(usuario.getEmail());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public UsuarioDTO getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        return dto;
    }

    public void saveUsuario(Usuario usuario) {
        String encodedPassword = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encodedPassword);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public UsuarioDTO createUsuario(UsuarioDTO usuarioDTO) {
        System.out.println("Recebendo dados: " + usuarioDTO);

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));

        try {
            Usuario savedUsuario = usuarioRepository.save(usuario);
            System.out.println("Usuário salvo: " + savedUsuario);
            UsuarioDTO savedUsuarioDTO = new UsuarioDTO();
            savedUsuarioDTO.setNome(savedUsuario.getNome());
            savedUsuarioDTO.setEmail(savedUsuario.getEmail());
            savedUsuarioDTO.setSenha(null);

            return savedUsuarioDTO;
        } catch (Exception e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
            throw e;
        }
    }

}


package org.example.taskfinancemanagementsystem.controller;

import org.example.taskfinancemanagementsystem.dto.UsuarioDTO;
import org.example.taskfinancemanagementsystem.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioDTO getUsuario(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id);
    }
}


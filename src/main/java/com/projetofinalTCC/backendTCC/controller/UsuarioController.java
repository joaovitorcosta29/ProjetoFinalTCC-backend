package com.projetofinalTCC.backendTCC.controller;

import com.projetofinalTCC.backendTCC.model.UsuarioDTO;
import com.projetofinalTCC.backendTCC.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios") 
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody UsuarioDTO usuario) {
        try {
            usuarioService.registrar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody UsuarioDTO loginDados) {
        UsuarioDTO usuario = usuarioService.logar(loginDados.getEmail(), loginDados.getSenha());
        return ResponseEntity.ok(usuario);
    }
}
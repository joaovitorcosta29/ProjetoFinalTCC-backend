package com.projetofinalTCC.backendTCC.service;

import com.projetofinalTCC.backendTCC.model.UsuarioDTO;
import com.projetofinalTCC.backendTCC.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void registrar(UsuarioDTO usuario) {
        int resultado = usuarioRepository.registrar(usuario);
        if (resultado == 0) {
            throw new RuntimeException("Erro ao registrar usuário no banco de dados.");
        }
    }

    public UsuarioDTO logar(String email, String senha) {
        UsuarioDTO usuario = usuarioRepository.Logar(email, senha);
        if (usuario == null) {
            throw new RuntimeException("E-mail ou senha inválidos.");
        }
        return usuario;
    }
}
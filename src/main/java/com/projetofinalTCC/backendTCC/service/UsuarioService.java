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
        if (usuario.getNome() == null || usuario.getNome().trim().length() < 10) {
            throw new RuntimeException("O nome deve ter no mínimo 10 caracteres. Digite seu nome completo.");
        }

        if (usuario.getSenha() == null || usuario.getSenha().length() < 8) {
            throw new RuntimeException("A senha deve ter no mínimo 8 caracteres.");
        }

        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new RuntimeException("O e-mail é obrigatório.");
        }

        UsuarioDTO existente = usuarioRepository.buscarPorEmail(usuario.getEmail());
        if (existente != null) {
            throw new RuntimeException("Já existe uma conta cadastrada com este e-mail.");
        }

        // Cadastro público só pode criar contas de MOTORISTA ou GESTOR_FROTA, nunca ADMIN
        if (usuario.getCargo() != UsuarioDTO.Cargo.GESTOR_FROTA) {
            usuario.setCargo(UsuarioDTO.Cargo.MOTORISTA);
        }

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
package com.projetofinalTCC.backendTCC.repository;

import com.projetofinalTCC.backendTCC.model.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;

@Repository 
public class UsuarioRepository {
    
    public int registrar(UsuarioDTO usuario) {
        int linhasAfetadas = 0;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement("INSERT INTO tb_usuario (nome, email, senha, cargo) values(?,?,?,?)");

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            
            String cargoString = "MOTORISTA";
            if (usuario.getCargo() != null) {
                if (usuario.getCargo() == UsuarioDTO.Cargo.ADMIN) {
                    cargoString = "ADMIN";
                } else if (usuario.getCargo() == UsuarioDTO.Cargo.GESTOR_FROTA) {
                    cargoString = "GESTOR_FROTA";
                } else {
                    cargoString = "MOTORISTA";
                }
            }
            stmt.setString(4, cargoString);

            linhasAfetadas = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return linhasAfetadas;
    }

    public UsuarioDTO Logar(String email, String senha) {
        UsuarioDTO usuario = null;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = conn.prepareStatement("SELECT * FROM tb_usuario WHERE email = ? and senha = ?");
            
            stmt.setString(1, email);
            stmt.setString(2, senha);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                usuario = new UsuarioDTO();
                usuario.setIdUsuario(rs.getLong("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                
                String cargoBanco = rs.getString("cargo");
                
                // CORREÇÃO: Tratamento correto para todos os cargos possíveis
                if ("ADMIN".equals(cargoBanco)) {
                    usuario.setCargo(UsuarioDTO.Cargo.ADMIN);
                } else if ("GESTOR_FROTA".equals(cargoBanco)) {
                    usuario.setCargo(UsuarioDTO.Cargo.GESTOR_FROTA);
                } else {
                    usuario.setCargo(UsuarioDTO.Cargo.MOTORISTA);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
}
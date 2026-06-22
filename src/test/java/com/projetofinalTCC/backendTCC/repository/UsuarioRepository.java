/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projetofinalTCC.backendTCC.repository;

import com.projetofinalTCC.backendTCC.model.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aluno
 */
@Repository 
public class UsuarioRepository {
    public int registrar(UsuarioDTO usuario) {
        int linhasAfetadas = 0;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement("INSERT INTO tb_usuario (nome, email,senha) values(?,?,?)");

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());

            linhasAfetadas = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public UsuarioDTO Logar(String email, String senha) {
        UsuarioDTO usuario = new UsuarioDTO();
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = conn.prepareStatement("SELECT * FROM tb_usuario WHERE email = ? and senha = ?");
            
            stmt.setString(1, email);
            stmt.setString(2, senha);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                usuario.setIdUsuario(rs.getLong("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return usuario;
    }
}

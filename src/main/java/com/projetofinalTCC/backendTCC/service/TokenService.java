package com.projetofinalTCC.backendTCC.service;

import com.projetofinalTCC.backendTCC.model.UsuarioDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey; 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    
    public SecretKey getKeySign() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    public String gerarToken(UsuarioDTO user) {
        if(
            user.getIdUsuario() == null ||
            user.getIdUsuario() == 0 || 
            user.getNome() == null || user.getNome().equals("") ||
            user.getEmail() == null || user.getEmail().equals("") ||
            user.getCargo() == null
        ) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), 
            "Um ou mais campos faltantes");
        }
        
        return Jwts.builder()
                .subject(user.getNome())
                .claim("id_usuario", user.getIdUsuario())
                .claim("nome", user.getNome())
                .claim("cargo", user.getCargo().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3000000))
                .signWith(this.getKeySign())
                .compact();
    }
    
    public UsuarioDTO extrairClaim(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(this.getKeySign())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        
        UsuarioDTO user = new UsuarioDTO();
        user.setIdUsuario(claims.get("id_usuario", Long.class));
        user.setNome(claims.get("nome", String.class));
        
        String cargoStr = claims.get("cargo", String.class);
        if (cargoStr != null) {
            user.setCargo(UsuarioDTO.Cargo.valueOf(cargoStr));
        }
        
        return user;
    }
    
    public boolean validarToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getKeySign())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
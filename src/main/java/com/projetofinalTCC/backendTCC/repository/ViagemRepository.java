package com.projetofinalTCC.backendTCC.repository;

import com.projetofinalTCC.backendTCC.model.ViagemDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ViagemRepository {

    public int registrar(ViagemDTO viagem) {
        int linhasAfetadas = 0;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            
            stmt = conn.prepareStatement("INSERT INTO tb_viagem (id_usuario, id_veiculo, destino, km_inicial, status_viagem, alerta_manutencao) VALUES (?, ?, ?, ?, ?, ?)");

            stmt.setLong(1, viagem.getIdUsuario());
            stmt.setLong(2, viagem.getIdVeiculo());
            stmt.setString(3, viagem.getDestino());
            stmt.setDouble(4, viagem.getKmInicial());
            
            // Define valores padrão caso venham nulos
            String status = viagem.getStatusViagem() != null ? viagem.getStatusViagem().name() : "EM_ANDAMENTO";
            String alerta = viagem.getAlertaManutencao() != null ? viagem.getAlertaManutencao().name() : "OK";
            
            stmt.setString(5, status);
            stmt.setString(6, alerta);

            linhasAfetadas = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return linhasAfetadas;
    }

    public List<ViagemDTO> listarTodas() {
        List<ViagemDTO> viagens = new ArrayList<>();
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = conn.prepareStatement("SELECT * FROM tb_viagem");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ViagemDTO viagem = new ViagemDTO();
                viagem.setIdViagem(rs.getLong("id_viagem"));
                viagem.setIdUsuario(rs.getLong("id_usuario"));
                viagem.setIdVeiculo(rs.getLong("id_veiculo"));
                viagem.setDestino(rs.getString("destino"));
                viagem.setKmInicial(rs.getDouble("km_inicial"));
                viagem.setKmFinal(rs.getDouble("km_final")); // Pode vir nulo se a viagem não acabou
                
                String status = rs.getString("status_viagem");
                if (status != null) {
                    viagem.setStatusViagem(ViagemDTO.StatusViagem.valueOf(status));
                }
                
                String alerta = rs.getString("alerta_manutencao");
                if (alerta != null) {
                    viagem.setAlertaManutencao(ViagemDTO.AlertaManutencao.valueOf(alerta));
                }

                viagens.add(viagem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return viagens;
    }
}
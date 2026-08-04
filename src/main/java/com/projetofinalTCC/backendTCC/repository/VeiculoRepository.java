package com.projetofinalTCC.backendTCC.repository;

import com.projetofinalTCC.backendTCC.model.VeiculoDTO;
import com.projetofinalTCC.backendTCC.model.VeiculoDTO.StatusVeiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class VeiculoRepository {

    public int registrar(VeiculoDTO veiculo) {
        int linhasAfetadas = 0;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO tb_veiculos (placa, modelo, ano_fabricacao, km_atual, km_ultima_manutencao, status) VALUES (?, ?, ?, ?, ?, ?)"
            );

            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAnoFabricacao());
            stmt.setDouble(4, veiculo.getKmAtual());
            stmt.setDouble(5, veiculo.getKmUltimaManutencao());

            stmt.setString(6, veiculo.getStatus() != null ? veiculo.getStatus().name() : null);

            linhasAfetadas = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return linhasAfetadas;
    }

    public List<VeiculoDTO> listarTodos() {
        List<VeiculoDTO> veiculos = new ArrayList<>();
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tb_veiculos");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                VeiculoDTO veiculo = new VeiculoDTO();
                veiculo.setIdVeiculo(rs.getLong("id_veiculo"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setAnoFabricacao(rs.getInt("ano_fabricacao"));
                veiculo.setKmAtual(rs.getDouble("km_atual"));
                veiculo.setKmUltimaManutencao(rs.getDouble("km_ultima_manutencao"));

                String statusStr = rs.getString("status");
                if (statusStr != null) {
                    veiculo.setStatus(StatusVeiculo.valueOf(statusStr));
                }

                veiculos.add(veiculo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veiculos;
    }

    public VeiculoDTO buscarPorId(Long idVeiculo) {
        VeiculoDTO veiculo = null;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tb_veiculos WHERE id_veiculo = ?");
            stmt.setLong(1, idVeiculo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                veiculo = new VeiculoDTO();
                veiculo.setIdVeiculo(rs.getLong("id_veiculo"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setAnoFabricacao(rs.getInt("ano_fabricacao"));
                veiculo.setKmAtual(rs.getDouble("km_atual"));
                veiculo.setKmUltimaManutencao(rs.getDouble("km_ultima_manutencao"));

                String statusStr = rs.getString("status");
                if (statusStr != null) {
                    veiculo.setStatus(StatusVeiculo.valueOf(statusStr));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veiculo;
    }

    public int atualizarStatus(Long idVeiculo, StatusVeiculo novoStatus) {
        int linhasAfetadas = 0;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("UPDATE tb_veiculos SET status = ? WHERE id_veiculo = ?");

            stmt.setString(1, novoStatus != null ? novoStatus.name() : null);
            stmt.setLong(2, idVeiculo);

            linhasAfetadas = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return linhasAfetadas;
    }

    public int editarVeiculo(VeiculoDTO veiculo) {
        int linhasAfetadas = 0;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("UPDATE tb_veiculos SET placa = ?, modelo = ?, ano_fabricacao = ?, km_atual = ?, km_ultima_manutencao = ?, status = ? WHERE id_veiculo = ?");

            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAnoFabricacao());
            stmt.setDouble(4, veiculo.getKmAtual());
            stmt.setDouble(5, veiculo.getKmUltimaManutencao());
            stmt.setString(6, veiculo.getStatus() != null ? veiculo.getStatus().name() : null);
            stmt.setLong(7, veiculo.getIdVeiculo());

            linhasAfetadas = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return linhasAfetadas;
    }
    
}

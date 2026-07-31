/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projetofinalTCC.backendTCC.repository;

import com.projetofinalTCC.backendTCC.model.ManutencaoDTO;
import com.projetofinalTCC.backendTCC.model.ManutencaoDTO.StatusManutencao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joaov
 */

@Repository
public class ManutencaoRepository {

    public int registrar(ManutencaoDTO manutencao) {
        int linhasAfetadas = 0;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO tb_manutencoes (id_veiculo, descricao, status_manutencao) VALUES (?, ?, ?)"
            );

            stmt.setInt(1, manutencao.getIdVeiculo());
            stmt.setString(2, manutencao.getDescricao());
            stmt.setString(3, manutencao.getStatusManutencao() != null ? manutencao.getStatusManutencao().name() : StatusManutencao.PENDENTE.name());

            linhasAfetadas = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return linhasAfetadas;
    }

    public List<ManutencaoDTO> listarTodas() {
        List<ManutencaoDTO> manutencoes = new ArrayList<>();
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tb_manutencoes");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ManutencaoDTO manutencao = new ManutencaoDTO();
                manutencao.setIdManutencao(rs.getInt("id_manutencao"));
                manutencao.setIdVeiculo(rs.getInt("id_veiculo"));
                manutencao.setDescricao(rs.getString("descricao"));

                String statusStr = rs.getString("status_manutencao");
                if (statusStr != null) {
                    manutencao.setStatusManutencao(StatusManutencao.valueOf(statusStr));
                }

                manutencoes.add(manutencao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manutencoes;
    }

    public ManutencaoDTO buscarPorId(Integer idManutencao) {
        ManutencaoDTO manutencao = null;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tb_manutencoes WHERE id_manutencao = ?");
            stmt.setInt(1, idManutencao);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                manutencao = new ManutencaoDTO();
                manutencao.setIdManutencao(rs.getInt("id_manutencao"));
                manutencao.setIdVeiculo(rs.getInt("id_veiculo"));
                manutencao.setDescricao(rs.getString("descricao"));

                String statusStr = rs.getString("status_manutencao");
                if (statusStr != null) {
                    manutencao.setStatusManutencao(StatusManutencao.valueOf(statusStr));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manutencao;
    }

    public List<ManutencaoDTO> listarPorVeiculo(Integer idVeiculo) {
        List<ManutencaoDTO> manutencoes = new ArrayList<>();
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tb_manutencoes WHERE id_veiculo = ?");
            stmt.setInt(1, idVeiculo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ManutencaoDTO manutencao = new ManutencaoDTO();
                manutencao.setIdManutencao(rs.getInt("id_manutencao"));
                manutencao.setIdVeiculo(rs.getInt("id_veiculo"));
                manutencao.setDescricao(rs.getString("descricao"));

                String statusStr = rs.getString("status_manutencao");
                if (statusStr != null) {
                    manutencao.setStatusManutencao(StatusManutencao.valueOf(statusStr));
                }

                manutencoes.add(manutencao);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manutencoes;
    }

    public int atualizarStatus(Integer idManutencao, StatusManutencao novoStatus) {
        int linhasAfetadas = 0;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("UPDATE tb_manutencoes SET status_manutencao = ? WHERE id_manutencao = ?");

            stmt.setString(1, novoStatus != null ? novoStatus.name() : null);
            stmt.setInt(2, idManutencao);

            linhasAfetadas = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return linhasAfetadas;
    }

    public int editarManutencao(ManutencaoDTO manutencao) {
        int linhasAfetadas = 0;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE tb_manutencoes SET id_veiculo = ?, descricao = ?, status_manutencao = ? WHERE id_manutencao = ?"
            );

            stmt.setInt(1, manutencao.getIdVeiculo());
            stmt.setString(2, manutencao.getDescricao());
            stmt.setString(3, manutencao.getStatusManutencao() != null ? manutencao.getStatusManutencao().name() : null);
            stmt.setInt(4, manutencao.getIdManutencao());

            linhasAfetadas = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return linhasAfetadas;
    }

    public int deletar(Integer idManutencao) {
        int linhasAfetadas = 0;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM tb_manutencoes WHERE id_manutencao = ?");
            stmt.setInt(1, idManutencao);

            linhasAfetadas = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return linhasAfetadas;
    }
}

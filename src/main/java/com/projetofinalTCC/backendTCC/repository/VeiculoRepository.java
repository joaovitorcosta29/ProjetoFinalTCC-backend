package com.projetofinalTCC.backendTCC.repository;

import com.projetofinalTCC.backendTCC.model.VeiculoDTO;
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
            PreparedStatement stmt = null;
            
            // Ajuste os nomes das colunas conforme o seu banco de dados (tb_veiculo)
            stmt = conn.prepareStatement("INSERT INTO tb_veiculo (modelo, placa, ano) VALUES (?, ?, ?)");

            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getPlaca());
            stmt.setInt(3, veiculo.getAno());

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
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = conn.prepareStatement("SELECT * FROM tb_veiculo");
            rs = stmt.executeQuery();

            while (rs.next()) {
                VeiculoDTO veiculo = new VeiculoDTO();
                // Ajuste os métodos de set e nomes de coluna com base na sua VeiculoDTO
                veiculo.setIdVeiculo(rs.getLong("id_veiculo"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setAno(rs.getInt("ano"));
                
                veiculos.add(veiculo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veiculos;
    }
}
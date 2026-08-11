package com.projetofinalTCC.backendTCC.repository;

import com.projetofinalTCC.backendTCC.model.ViagemDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ViagemRepository {

    public int registrar(ViagemDTO viagem) {
        int linhasAfetadas = 0;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO tb_viagens (id_usuario, id_veiculo, cidade_destino, estado_destino, km_inicial, status_viagem) VALUES (?, ?, ?, ?, ?, ?)"
            );

            if (viagem.getIdUsuario() != null) {
                stmt.setInt(1, viagem.getIdUsuario());
            } else {
                stmt.setNull(1, Types.INTEGER);
            }

            stmt.setInt(2, viagem.getIdVeiculo());
            stmt.setString(3, viagem.getCidadeDestino());

            String estadoStr = viagem.getEstadoDestino() != null ? viagem.getEstadoDestino().name() : null;
            stmt.setString(4, estadoStr);

            if (viagem.getKmInicial() != null) {
                stmt.setDouble(5, viagem.getKmInicial());
            } else {
                stmt.setNull(5, Types.DOUBLE);
            }

            // Status da viagem padrão deve ser EM_ANDAMENTO quando iniciada
            String status = viagem.getStatusViagem() != null ? viagem.getStatusViagem().name() : "EM_ANDAMENTO";
            stmt.setString(6, status);

            linhasAfetadas = stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            System.err.println("Erro SQL ao registrar viagem: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao salvar no banco de dados: " + e.getMessage(), e);
        }
        return linhasAfetadas;
    }

    public Double buscarKmAtualDoVeiculo(int idVeiculo) {
        Double kmAtual = null;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("SELECT km_atual FROM tb_veiculos WHERE id_veiculo = ?");
            stmt.setInt(1, idVeiculo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                kmAtual = rs.getDouble("km_atual");
                if (rs.wasNull()) {
                    kmAtual = null;
                }
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar KM do veículo: " + e.getMessage(), e);
        }
        return kmAtual;
    }

    public List<ViagemDTO> listarTodas() {
        List<ViagemDTO> viagens = new ArrayList<>();
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tb_viagens");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ViagemDTO viagem = mapearViagem(rs);
                viagens.add(viagem);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar viagens: " + e.getMessage(), e);
        }
        return viagens;
    }

    public ViagemDTO buscarPorId(Long idViagem) {
        ViagemDTO viagem = null;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tb_viagens WHERE id_viagem = ?");
            stmt.setLong(1, idViagem);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                viagem = mapearViagem(rs);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar viagem por ID: " + e.getMessage(), e);
        }
        return viagem;
    }

    public int finalizarViagem(Long idViagem, Double kmFinal) {
        int linhasAfetadas = 0;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE tb_viagens SET km_final = ?, status_viagem = 'FINALIZADA' WHERE id_viagem = ?"
            );

            if (kmFinal != null) {
                stmt.setDouble(1, kmFinal);
            } else {
                stmt.setNull(1, Types.DOUBLE);
            }

            stmt.setLong(2, idViagem);

            linhasAfetadas = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao finalizar viagem: " + e.getMessage(), e);
        }
        return linhasAfetadas;
    }

    public int atualizarVeiculoKmEStatus(int idVeiculo, Double kmFinal) {
        int linhasAfetadas = 0;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE tb_veiculos SET km_atual = ?, status = 'DISPONIVEL' WHERE id_veiculo = ?"
            );

            if (kmFinal != null) {
                stmt.setDouble(1, kmFinal);
            } else {
                stmt.setNull(1, Types.DOUBLE);
            }

            stmt.setInt(2, idVeiculo);

            linhasAfetadas = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar veículo: " + e.getMessage(), e);
        }
        return linhasAfetadas;
    }

    private ViagemDTO mapearViagem(ResultSet rs) throws SQLException {
        ViagemDTO viagem = new ViagemDTO();
        viagem.setIdViagem(rs.getLong("id_viagem"));

        int idUsuario = rs.getInt("id_usuario");
        if (!rs.wasNull()) {
            viagem.setIdUsuario(idUsuario);
        }

        viagem.setIdVeiculo(rs.getInt("id_veiculo"));
        viagem.setCidadeDestino(rs.getString("cidade_destino"));

        String estadoStr = rs.getString("estado_destino");
        if (estadoStr != null && !estadoStr.isBlank()) {
            try {
                viagem.setEstadoDestino(ViagemDTO.Estado.valueOf(estadoStr.trim()));
            } catch (Exception ignored) {
            }
        }

        viagem.setKmInicial(rs.getDouble("km_inicial"));
        if (rs.wasNull()) {
            viagem.setKmInicial(null);
        }

        viagem.setKmFinal(rs.getDouble("km_final"));
        if (rs.wasNull()) {
            viagem.setKmFinal(null);
        }

        String status = rs.getString("status_viagem");
        if (status != null && !status.isBlank()) {
            try {
                viagem.setStatusViagem(ViagemDTO.StatusViagem.valueOf(status.trim()));
            } catch (Exception ignored) {
            }
        }

        return viagem;
    }

    public String buscarStatusDoVeiculo(int idVeiculo) {
        String status = null;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("SELECT status FROM tb_veiculos WHERE id_veiculo = ?");
            stmt.setInt(1, idVeiculo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                status = rs.getString("status");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao buscar status do veículo: " + e.getMessage(), e);
        }
        return status;
    }

    public int atribuirMotoristaEViagem(Long idViagem, Integer idUsuario) {
        int linhasAfetadas = 0;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE tb_viagens SET id_usuario = ? WHERE id_viagem = ?"
            );
            stmt.setInt(1, idUsuario);
            stmt.setLong(2, idViagem);

            linhasAfetadas = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atribuir motorista à viagem: " + e.getMessage(), e);
        }
        return linhasAfetadas;
    }

    public int atualizarStatusVeiculo(int idVeiculo, String status) {
        int linhasAfetadas = 0;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE tb_veiculos SET status = ? WHERE id_veiculo = ?"
            );
            stmt.setString(1, status);
            stmt.setInt(2, idVeiculo);

            linhasAfetadas = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar status do veículo: " + e.getMessage(), e);
        }
        return linhasAfetadas;
    }

    public int editarViagem(ViagemDTO viagem) {
        int linhasAfetadas = 0;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE tb_viagens SET id_usuario = ?, id_veiculo = ?, cidade_destino = ?, estado_destino = ?, "
                    + "km_inicial = ?, km_final = ?, status_viagem = ? WHERE id_viagem = ?"
            );

            if (viagem.getIdUsuario() != null) {
                stmt.setInt(1, viagem.getIdUsuario());
            } else {
                stmt.setNull(1, Types.INTEGER);
            }

            stmt.setInt(2, viagem.getIdVeiculo());
            stmt.setString(3, viagem.getCidadeDestino());

            String estadoStr = viagem.getEstadoDestino() != null ? viagem.getEstadoDestino().name() : null;
            stmt.setString(4, estadoStr);

            if (viagem.getKmInicial() != null) {
                stmt.setDouble(5, viagem.getKmInicial());
            } else {
                stmt.setNull(5, Types.DOUBLE);
            }

            if (viagem.getKmFinal() != null) {
                stmt.setDouble(6, viagem.getKmFinal());
            } else {
                stmt.setNull(6, Types.DOUBLE);
            }

            String statusStr = viagem.getStatusViagem() != null ? viagem.getStatusViagem().name() : null;
            stmt.setString(7, statusStr);

            stmt.setLong(8, viagem.getIdViagem());

            linhasAfetadas = stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar viagem: " + e.getMessage(), e);
        }
        return linhasAfetadas;
    }
}
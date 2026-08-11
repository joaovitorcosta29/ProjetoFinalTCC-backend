package com.projetofinalTCC.backendTCC.service;

import com.projetofinalTCC.backendTCC.model.VeiculoDTO;
import com.projetofinalTCC.backendTCC.model.VeiculoDTO.StatusVeiculo;
import com.projetofinalTCC.backendTCC.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public void cadastrar(VeiculoDTO veiculo) {
        if (veiculo.getPlaca() == null || veiculo.getPlaca().length() != 8) {
            throw new RuntimeException("A placa deve ter exatamente 8 caracteres, no formato ABC-1234.");
        }

        int resultado = veiculoRepository.registrar(veiculo);
        if (resultado == 0) {
            throw new RuntimeException("Erro ao cadastrar o veículo no banco de dados.");
        }
    }

    public List<VeiculoDTO> listarTodos() {
        return veiculoRepository.listarTodos();
    }

    public VeiculoDTO buscarPorId(Long idVeiculo) {
        VeiculoDTO veiculo = veiculoRepository.buscarPorId(idVeiculo);
        if (veiculo == null) {
            throw new RuntimeException("Veículo não encontrado.");
        }
        return veiculo;
    }

    public void alterarStatus(Long idVeiculo, StatusVeiculo status) {
        int resultado = veiculoRepository.atualizarStatus(idVeiculo, status);
        if (resultado == 0) {
            throw new RuntimeException("Erro ao atualizar o status do veículo no banco de dados.");
        }
    }

    public void editarVeiculo(VeiculoDTO veiculo) {
        if (veiculo.getIdVeiculo() == null) {
            throw new IllegalArgumentException("ID do veículo não fornecido.");
        }

        VeiculoDTO existente = veiculoRepository.buscarPorId(veiculo.getIdVeiculo());
        if (existente == null) {
            throw new RuntimeException("Veículo não encontrado.");
        }

        if (existente.getStatus() == StatusVeiculo.EM_USO) {
            throw new RuntimeException("Não é possível editar um veículo que está em uso.");
        }

        if (veiculo.getPlaca() == null || veiculo.getPlaca().length() != 8) {
            throw new RuntimeException("A placa deve ter exatamente 8 caracteres, no formato ABC-1234.");
        }

        int linhas = veiculoRepository.editarVeiculo(veiculo);
        if (linhas == 0) {
            throw new RuntimeException("Não foi possível atualizar o veículo.");
        }
    }

}
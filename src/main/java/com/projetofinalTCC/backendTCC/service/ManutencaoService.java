/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projetofinalTCC.backendTCC.service;

import com.projetofinalTCC.backendTCC.model.ManutencaoDTO;
import com.projetofinalTCC.backendTCC.model.ManutencaoDTO.StatusManutencao;
import com.projetofinalTCC.backendTCC.model.VeiculoDTO;
import com.projetofinalTCC.backendTCC.repository.ManutencaoRepository;
import com.projetofinalTCC.backendTCC.repository.VeiculoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author joaov
 */

@Service
public class ManutencaoService {

    @Autowired
    private ManutencaoRepository repository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public String registrar(ManutencaoDTO manutencao) {
        boolean possuiManutencaoAberta = repository.listarPorVeiculo(manutencao.getIdVeiculo()).stream()
                .anyMatch(m -> m.getStatusManutencao() == StatusManutencao.PENDENTE
                        || m.getStatusManutencao() == StatusManutencao.EM_ANDAMENTO);

        if (possuiManutencaoAberta) {
            throw new RuntimeException("Este veículo já possui uma manutenção pendente ou em andamento.");
        }

        manutencao.setStatusManutencao(StatusManutencao.PENDENTE);
        
        int resultado = repository.registrar(manutencao);
        if (resultado > 0) {
            return "Manutenção cadastrada com sucesso!";
        }
        return "Erro ao cadastrar manutenção.";
    }

    public List<ManutencaoDTO> listarTodas() {
        return repository.listarTodas();
    }

    public ManutencaoDTO buscarPorId(Integer id) {
        return repository.buscarPorId(id);
    }

    public List<ManutencaoDTO> listarPorVeiculo(Integer idVeiculo) {
        return repository.listarPorVeiculo(idVeiculo);
    }

    public String atualizarStatus(Integer id, StatusManutencao novoStatus) {
        ManutencaoDTO existente = repository.buscarPorId(id);
        if (existente == null) {
            throw new RuntimeException("Manutenção não encontrada.");
        }

        if (existente.getStatusManutencao() == StatusManutencao.CONCLUIDA
                || existente.getStatusManutencao() == StatusManutencao.CANCELADA) {
            throw new RuntimeException("Não é possível alterar o status de uma manutenção já concluída ou cancelada.");
        }

        int resultado = repository.atualizarStatus(id, novoStatus);
        if (resultado == 0) {
            throw new RuntimeException("Erro ao atualizar status da manutenção.");
        }

        if (novoStatus == StatusManutencao.CONCLUIDA) {
            VeiculoDTO veiculo = veiculoRepository.buscarPorId(existente.getIdVeiculo().longValue());
            if (veiculo != null && veiculo.getKmAtual() != null) {
                veiculoRepository.atualizarKmUltimaManutencao(veiculo.getIdVeiculo(), veiculo.getKmAtual());
                veiculoRepository.recalcularAlertaManutencao(veiculo.getIdVeiculo());
            }
        }

        return "Status da manutenção atualizado com sucesso!";
    }

    public String editarManutencao(ManutencaoDTO manutencao) {
        ManutencaoDTO existente = repository.buscarPorId(manutencao.getIdManutencao());
        if (existente == null) {
            throw new RuntimeException("Manutenção não encontrada.");
        }

        if (existente.getStatusManutencao() != null && existente.getStatusManutencao() != StatusManutencao.PENDENTE) {
            throw new RuntimeException("Só é possível editar manutenções que ainda estão pendentes.");
        }

        int resultado = repository.editarManutencao(manutencao);
        if (resultado > 0) {
            return "Manutenção atualizada com sucesso!";
        }
        return "Erro ao atualizar manutenção.";
    }

    public String deletar(Integer id) {
        int resultado = repository.deletar(id);
        if (resultado > 0) {
            return "Manutenção deletada com sucesso!";
        }
        return "Erro ao deletar manutenção.";
    }
}
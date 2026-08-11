/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projetofinalTCC.backendTCC.service;

import com.projetofinalTCC.backendTCC.model.ManutencaoDTO;
import com.projetofinalTCC.backendTCC.model.ManutencaoDTO.StatusManutencao;
import com.projetofinalTCC.backendTCC.repository.ManutencaoRepository;
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

    public String registrar(ManutencaoDTO manutencao) {
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
        int resultado = repository.atualizarStatus(id, novoStatus);
        if (resultado > 0) {
            return "Status da manutenção atualizado com sucesso!";
        }
        return "Erro ao atualizar status da manutenção.";
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
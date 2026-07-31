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

    public int registrar(ManutencaoDTO manutencao) {
        if (manutencao.getStatusManutencao() == null) {
            manutencao.setStatusManutencao(StatusManutencao.PENDENTE);
        }
        return repository.registrar(manutencao);
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

    public int atualizarStatus(Integer id, StatusManutencao novoStatus) {
        return repository.atualizarStatus(id, novoStatus);
    }

    public int editarManutencao(ManutencaoDTO manutencao) {
        return repository.editarManutencao(manutencao);
    }

    public int deletar(Integer id) {
        return repository.deletar(id);
    }
}

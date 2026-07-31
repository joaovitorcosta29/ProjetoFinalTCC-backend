/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projetofinalTCC.backendTCC.controller;

import com.projetofinalTCC.backendTCC.model.ManutencaoDTO;
import com.projetofinalTCC.backendTCC.model.ManutencaoDTO.StatusManutencao;
import com.projetofinalTCC.backendTCC.service.ManutencaoService; 
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author joaov
 */

@RestController
@RequestMapping("/api/manutencoes")
public class ManutencaoController {

    @Autowired
    private ManutencaoService service;

    @PostMapping
    public String cadastrarManutencao(@RequestBody ManutencaoDTO manutencao) {
        int resultado = service.registrar(manutencao);
        if (resultado > 0) {
            return "Manutenção cadastrada com sucesso!";
        }
        return "Erro ao cadastrar manutenção.";
    }

    @GetMapping
    public List<ManutencaoDTO> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ManutencaoDTO buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/veiculo/{idVeiculo}")
    public List<ManutencaoDTO> listarPorVeiculo(@PathVariable Integer idVeiculo) {
        return service.listarPorVeiculo(idVeiculo);
    }

    @PutMapping("/{id}")
    public String editarManutencao(@PathVariable Integer id, @RequestBody ManutencaoDTO manutencao) {
        manutencao.setIdManutencao(id);
        int resultado = service.editarManutencao(manutencao);
        if (resultado > 0) {
            return "Manutenção atualizada com sucesso!";
        }
        return "Erro ao atualizar manutenção.";
    }

    @PatchMapping("/{id}/status")
    public String atualizarStatus(@PathVariable Integer id, @RequestParam StatusManutencao novoStatus) {
        int resultado = service.atualizarStatus(id, novoStatus);
        if (resultado > 0) {
            return "Status da manutenção atualizado com sucesso!";
        }
        return "Erro ao atualizar status da manutenção.";
    }

    @DeleteMapping("/{id}")
    public String deletarManutencao(@PathVariable Integer id) {
        int resultado = service.deletar(id);
        if (resultado > 0) {
            return "Manutenção deletada com sucesso!";
        }
        return "Erro ao deletar manutenção.";
    }
}

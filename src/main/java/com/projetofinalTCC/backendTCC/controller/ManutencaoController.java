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
    private ManutencaoService Manutencaoservice;

    @PostMapping
    public String cadastrarManutencao(@RequestBody ManutencaoDTO manutencao) {
        return Manutencaoservice.registrar(manutencao);
    }

    @GetMapping
    public List<ManutencaoDTO> listarTodas() {
        return Manutencaoservice.listarTodas();
    }

    @GetMapping("/{id}")
    public ManutencaoDTO buscarPorId(@PathVariable Integer id) {
        return Manutencaoservice.buscarPorId(id);
    }

    @GetMapping("/veiculo/{idVeiculo}")
    public List<ManutencaoDTO> listarPorVeiculo(@PathVariable Integer idVeiculo) {
        return Manutencaoservice.listarPorVeiculo(idVeiculo);
    }

    @PutMapping("/{id}")
    public String editarManutencao(@PathVariable Integer id, @RequestBody ManutencaoDTO manutencao) {
        manutencao.setIdManutencao(id);
        return Manutencaoservice.editarManutencao(manutencao);
    }

    @PatchMapping("/{id}/status")
    public String atualizarStatus(@PathVariable Integer id, @RequestParam StatusManutencao novoStatus) {
        return Manutencaoservice.atualizarStatus(id, novoStatus);
    }

    @DeleteMapping("/{id}")
    public String deletarManutencao(@PathVariable Integer id) {
        return Manutencaoservice.deletar(id);
    }
}
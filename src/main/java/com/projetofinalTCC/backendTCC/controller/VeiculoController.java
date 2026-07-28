package com.projetofinalTCC.backendTCC.controller;

import com.projetofinalTCC.backendTCC.model.VeiculoDTO;
import com.projetofinalTCC.backendTCC.model.VeiculoDTO.StatusVeiculo;
import com.projetofinalTCC.backendTCC.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody VeiculoDTO veiculo) {
        try {
            veiculoService.cadastrar(veiculo);
            return ResponseEntity.ok("Veículo cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<VeiculoDTO>> listar() {
        List<VeiculoDTO> veiculos = veiculoService.listarTodos();
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            VeiculoDTO veiculo = veiculoService.buscarPorId(id);
            return ResponseEntity.ok(veiculo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/status")
    public ResponseEntity<String> alterarStatus(@RequestBody VeiculoDTO dto) {
        try {
            veiculoService.alterarStatus(dto.getIdVeiculo(), dto.getStatus());
            return ResponseEntity.ok("Status atualizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

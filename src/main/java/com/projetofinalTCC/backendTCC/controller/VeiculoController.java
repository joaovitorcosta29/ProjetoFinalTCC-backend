package com.projetofinalTCC.backendTCC.controller;

import com.projetofinalTCC.backendTCC.model.VeiculoDTO;
import com.projetofinalTCC.backendTCC.service.VeiculoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody VeiculoDTO veiculo) {
        veiculoService.registrar(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body("Veículo registrado com sucesso");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<VeiculoDTO>> listar() {
        List<VeiculoDTO> veiculos = veiculoService.listarTodos();
        return ResponseEntity.ok(veiculos);
    }
}
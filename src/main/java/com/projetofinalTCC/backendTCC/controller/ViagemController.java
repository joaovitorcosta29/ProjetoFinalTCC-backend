package com.projetofinalTCC.backendTCC.controller;

import com.projetofinalTCC.backendTCC.model.ViagemDTO;
import com.projetofinalTCC.backendTCC.service.ViagemService;
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
@RequestMapping("/viagens")
public class ViagemController {

    @Autowired
    private ViagemService viagemService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody ViagemDTO viagem) {
        viagemService.registrar(viagem);
        return ResponseEntity.status(HttpStatus.CREATED).body("Viagem registrada com sucesso");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ViagemDTO>> listar() {
        List<ViagemDTO> viagens = viagemService.listarTodas();
        return ResponseEntity.ok(viagens);
    }
}
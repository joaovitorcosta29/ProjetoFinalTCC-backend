package com.projetofinalTCC.backendTCC.controller;

import com.projetofinalTCC.backendTCC.model.FinalizarViagemDTO;
import com.projetofinalTCC.backendTCC.model.ViagemDTO;
import com.projetofinalTCC.backendTCC.service.ViagemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/finalizar")
    public ResponseEntity<String> finalizar(@RequestBody FinalizarViagemDTO dto) {
        viagemService.finalizarViagem(dto.getIdViagem(), dto.getKmFinal());
        return ResponseEntity.ok("Viagem finalizada com sucesso");
    }

    @PutMapping("/atualizar")
    public ResponseEntity<String> atualizarViagem(@RequestBody ViagemDTO viagem) {
        try {
            viagemService.editarViagem(viagem);
            return ResponseEntity.ok("Viagem atualizada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/assumir")
    public ResponseEntity<String> assumirViagem(
            @RequestParam Long idViagem,
            @RequestParam Integer idUsuario) {

        viagemService.assumirViagem(idViagem, idUsuario);
        return ResponseEntity.ok("Viagem vinculada ao motorista com sucesso!");
    }

    @PostMapping("/cancelar")
    public ResponseEntity<String> cancelarViagem(
            @RequestParam Long idViagem,
            @RequestParam Integer idUsuario) {
        try {
            viagemService.cancelarViagem(idViagem, idUsuario);
            return ResponseEntity.ok("Viagem cancelada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
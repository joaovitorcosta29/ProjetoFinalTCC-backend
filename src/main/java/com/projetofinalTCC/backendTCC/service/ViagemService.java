package com.projetofinalTCC.backendTCC.service;

import com.projetofinalTCC.backendTCC.model.ViagemDTO;
import com.projetofinalTCC.backendTCC.repository.ViagemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViagemService {

    @Autowired
    private ViagemRepository viagemRepository;

    public void registrar(ViagemDTO viagem) {
        int resultado = viagemRepository.registrar(viagem);
        if (resultado == 0) {
            throw new RuntimeException("Erro ao registrar viagem no banco de dados.");
        }
    }

    public List<ViagemDTO> listarTodas() {
        return viagemRepository.listarTodas();
    }
}
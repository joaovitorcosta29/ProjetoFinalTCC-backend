package com.projetofinalTCC.backendTCC.service;

import com.projetofinalTCC.backendTCC.model.VeiculoDTO;
import com.projetofinalTCC.backendTCC.repository.VeiculoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public void registrar(VeiculoDTO veiculo) {
        int resultado = veiculoRepository.registrar(veiculo);
        if (resultado == 0) {
            throw new RuntimeException("Erro ao registrar veículo no banco de dados.");
        }
    }

    public List<VeiculoDTO> listarTodos() {
        return veiculoRepository.listarTodos();
    }
}
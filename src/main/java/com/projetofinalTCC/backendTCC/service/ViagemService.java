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

    public void finalizarViagem(Long idViagem, Double kmFinal) {
        ViagemDTO viagem = viagemRepository.buscarPorId(idViagem);
        
        if (viagem == null) {
            throw new RuntimeException("Viagem não encontrada para o ID: " + idViagem);
        }

        if (kmFinal == null || kmFinal < viagem.getKmInicial()) {
            throw new RuntimeException("O KM Final (" + kmFinal + ") não pode ser menor que o KM Inicial (" + viagem.getKmInicial() + ").");
        }

        int linhasViagem = viagemRepository.finalizarViagem(idViagem, kmFinal);
        if (linhasViagem == 0) {
            throw new RuntimeException("Falha ao atualizar o status da viagem no banco de dados.");
        }

        int linhasVeiculo = viagemRepository.atualizarVeiculoKmEStatus(viagem.getIdVeiculo(), kmFinal);
        if (linhasVeiculo == 0) {
            throw new RuntimeException("Falha ao atualizar o status e KM do veículo no banco de dados.");
        }
    }
}
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
        // 1. CORREÇÃO: Bloqueia cadastro se o veículo já estiver em uso
        String statusVeiculo = viagemRepository.buscarStatusDoVeiculo(viagem.getIdVeiculo());
        if ("EM_USO".equalsIgnoreCase(statusVeiculo)) {
            throw new RuntimeException("Este veículo já está em uma viagem em andamento.");
        }

        // 2. Pega a quilometragem atual para o início da viagem
        Double kmAtualDoVeiculo = viagemRepository.buscarKmAtualDoVeiculo(viagem.getIdVeiculo());
        if (kmAtualDoVeiculo == null) {
            throw new RuntimeException("Veículo não encontrado ou sem quilometragem cadastrada.");
        }

        viagem.setKmInicial(kmAtualDoVeiculo);

        int resultado = viagemRepository.registrar(viagem);
        if (resultado == 0) {
            throw new RuntimeException("Erro ao registrar viagem no banco de dados.");
        }
        
        // 3. CORREÇÃO: Altera o veículo para EM_USO (em vez de atualizarVeiculoKmEStatus)
        viagemRepository.atualizarStatusVeiculo(viagem.getIdVeiculo(), "EM_USO");
    }

    // NOVO MÉTODO: O motorista seleciona a viagem e grava o id_usuario dela
    public void assumirViagem(Long idViagem, Integer idUsuario) {
        ViagemDTO viagem = viagemRepository.buscarPorId(idViagem);
        
        if (viagem == null) {
            throw new RuntimeException("Viagem não encontrada para o ID: " + idViagem);
        }

        if ("FINALIZADA".equalsIgnoreCase(String.valueOf(viagem.getStatusViagem()))) {
            throw new RuntimeException("Esta viagem já foi finalizada.");
        }

        int linhas = viagemRepository.atribuirMotoristaEViagem(idViagem, idUsuario);
        if (linhas == 0) {
            throw new RuntimeException("Falha ao associar o motorista à viagem.");
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

        // CORREÇÃO: Garante que a viagem tem motorista antes de finalizar
        if (viagem.getIdUsuario() == null) {
            throw new RuntimeException("A viagem precisa ser assumida por um motorista antes de ser finalizada.");
        }

        if (kmFinal == null || (viagem.getKmInicial() != null && kmFinal < viagem.getKmInicial())) {
            throw new RuntimeException("O KM Final (" + kmFinal + ") não pode ser menor que o KM Inicial (" + viagem.getKmInicial() + ").");
        }

        int linhasViagem = viagemRepository.finalizarViagem(idViagem, kmFinal);
        if (linhasViagem == 0) {
            throw new RuntimeException("Falha ao atualizar o status da viagem no banco de dados.");
        }

        // Devolve o veículo para DISPONIVEL e atualiza a KM com a informada no fim
        int linhasVeiculo = viagemRepository.atualizarVeiculoKmEStatus(viagem.getIdVeiculo(), kmFinal);
        if (linhasVeiculo == 0) {
            throw new RuntimeException("Falha ao atualizar o status e KM do veículo no banco de dados.");
        }
    }

    public void editarViagem(ViagemDTO viagem) {
        if (viagem.getIdViagem() == null) {
            throw new IllegalArgumentException("ID da viagem não fornecido.");
        }

        ViagemDTO existente = viagemRepository.buscarPorId(viagem.getIdViagem());
        if (existente == null) {
            throw new RuntimeException("Viagem não encontrada para o ID: " + viagem.getIdViagem());
        }

        int linhas = viagemRepository.editarViagem(viagem);
        if (linhas == 0) {
            throw new RuntimeException("Não foi possível atualizar a viagem.");
        }
    }
}
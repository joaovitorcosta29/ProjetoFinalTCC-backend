package com.projetofinalTCC.backendTCC.service;

import com.projetofinalTCC.backendTCC.model.VeiculoDTO;
import com.projetofinalTCC.backendTCC.model.ViagemDTO;
import com.projetofinalTCC.backendTCC.repository.VeiculoRepository;
import com.projetofinalTCC.backendTCC.repository.ViagemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViagemService {

    @Autowired
    private ViagemRepository viagemRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public void registrar(ViagemDTO viagem) {
        Double kmAtualDoVeiculo = viagemRepository.buscarKmAtualDoVeiculo(viagem.getIdVeiculo());
        if (kmAtualDoVeiculo == null) {
            throw new RuntimeException("Veículo não encontrado ou sem quilometragem cadastrada.");
        }

        viagem.setKmInicial(kmAtualDoVeiculo);

        int resultado = viagemRepository.registrar(viagem);
        if (resultado == 0) {
            throw new RuntimeException("Erro ao registrar viagem no banco de dados.");
        }
    }

    public void assumirViagem(Long idViagem, Integer idUsuario) {
        ViagemDTO viagem = viagemRepository.buscarPorId(idViagem);
        
        if (viagem == null) {
            throw new RuntimeException("Viagem não encontrada para o ID: " + idViagem);
        }

        if ("FINALIZADA".equalsIgnoreCase(String.valueOf(viagem.getStatusViagem()))) {
            throw new RuntimeException("Esta viagem já foi finalizada.");
        }

        if (viagem.getIdUsuario() != null) {
            throw new RuntimeException("Esta viagem já foi assumida por outro motorista.");
        }

        ViagemDTO viagemEmAndamento = viagemRepository.buscarViagemEmAndamentoDoUsuario(idUsuario);
        if (viagemEmAndamento != null) {
            throw new RuntimeException("Você já possui uma viagem em andamento. Finalize-a antes de assumir outra.");
        }

        int linhas = viagemRepository.atribuirMotoristaEViagem(idViagem, idUsuario);
        if (linhas == 0) {
            throw new RuntimeException("Falha ao associar o motorista à viagem.");
        }

        viagemRepository.atualizarStatusViagem(idViagem, "EM_ANDAMENTO");
        viagemRepository.atualizarStatusVeiculo(viagem.getIdVeiculo(), "EM_USO");
    }

    public List<ViagemDTO> listarTodas() {
        return viagemRepository.listarTodas();
    }

    public void finalizarViagem(Long idViagem, Double kmFinal) {
        ViagemDTO viagem = viagemRepository.buscarPorId(idViagem);
        
        if (viagem == null) {
            throw new RuntimeException("Viagem não encontrada para o ID: " + idViagem);
        }

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

        int linhasVeiculo = viagemRepository.atualizarVeiculoKmEStatus(viagem.getIdVeiculo(), kmFinal);
        if (linhasVeiculo == 0) {
            throw new RuntimeException("Falha ao atualizar o status e KM do veículo no banco de dados.");
        }

        veiculoRepository.recalcularAlertaManutencao(viagem.getIdVeiculo().longValue());
    }

    public void editarViagem(ViagemDTO viagem) {
        if (viagem.getIdViagem() == null) {
            throw new IllegalArgumentException("ID da viagem não fornecido.");
        }

        ViagemDTO existente = viagemRepository.buscarPorId(viagem.getIdViagem());
        if (existente == null) {
            throw new RuntimeException("Viagem não encontrada para o ID: " + viagem.getIdViagem());
        }

        if (existente.getStatusViagem() != null && existente.getStatusViagem() != ViagemDTO.StatusViagem.DISPONIVEL) {
            throw new RuntimeException("Só é possível editar viagens que ainda não foram assumidas por um motorista.");
        }

        if (viagem.getIdVeiculo() == null) {
            throw new RuntimeException("Selecione um veículo para a viagem.");
        }

        VeiculoDTO veiculoSelecionado = veiculoRepository.buscarPorId(viagem.getIdVeiculo().longValue());
        if (veiculoSelecionado == null) {
            throw new RuntimeException("Veículo selecionado não encontrado.");
        }

        viagem.setIdUsuario(existente.getIdUsuario());
        viagem.setStatusViagem(existente.getStatusViagem());
        viagem.setKmFinal(existente.getKmFinal());
        viagem.setKmInicial(veiculoSelecionado.getKmAtual());

        int linhas = viagemRepository.editarViagem(viagem);
        if (linhas == 0) {
            throw new RuntimeException("Não foi possível atualizar a viagem.");
        }
    }

    public void cancelarViagem(Long idViagem, Integer idUsuario) {
        ViagemDTO viagem = viagemRepository.buscarPorId(idViagem);

        if (viagem == null) {
            throw new RuntimeException("Viagem não encontrada para o ID: " + idViagem);
        }

        if (viagem.getStatusViagem() == ViagemDTO.StatusViagem.FINALIZADA) {
            throw new RuntimeException("Não é possível cancelar uma viagem já finalizada.");
        }

        if (viagem.getIdUsuario() == null || !viagem.getIdUsuario().equals(idUsuario)) {
            throw new RuntimeException("Você só pode cancelar uma viagem que você mesmo assumiu.");
        }

        viagemRepository.desvincularMotorista(idViagem);
        viagemRepository.atualizarStatusViagem(idViagem, "DISPONIVEL");
        viagemRepository.atualizarStatusVeiculo(viagem.getIdVeiculo(), "DISPONIVEL");
    }
}
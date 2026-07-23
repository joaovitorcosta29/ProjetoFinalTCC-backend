package com.projetofinalTCC.backendTCC.model;

public class ViagemDTO {
    
    private Long idViagem;
    private Long idUsuario;
    private Long idVeiculo;
    private String destino;
    private Double kmInicial;
    private Double kmFinal;
    private StatusViagem statusViagem;
    private AlertaManutencao alertaManutencao;

    public enum StatusViagem {
        EM_ANDAMENTO, FINALIZADA
    }        
    
    public enum AlertaManutencao {
        OK, REVISAO_NECESSARIA
    }  

    public Long getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(Long idViagem) {
        this.idViagem = idViagem;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Double getKmInicial() {
        return kmInicial;
    }

    public void setKmInicial(Double kmInicial) {
        this.kmInicial = kmInicial;
    }

    public Double getKmFinal() {
        return kmFinal;
    }

    public void setKmFinal(Double kmFinal) {
        this.kmFinal = kmFinal;
    }

    public StatusViagem getStatusViagem() {
        return statusViagem;
    }

    public void setStatusViagem(StatusViagem statusViagem) {
        this.statusViagem = statusViagem;
    }

    public AlertaManutencao getAlertaManutencao() {
        return alertaManutencao;
    }

    public void setAlertaManutencao(AlertaManutencao alertaManutencao) {
        this.alertaManutencao = alertaManutencao;
    }
}
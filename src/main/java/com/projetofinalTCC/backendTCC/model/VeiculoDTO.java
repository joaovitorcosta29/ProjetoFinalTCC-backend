/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.projetofinalTCC.backendTCC.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Aluno
 */
@Entity
@Table(name = "tb_veiculos")
public class VeiculoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_veiculo")
    private Long idVeiculo;

    private String placa;
    private String modelo;

    @Column(name = "ano_fabricacao")
    private int anoFabricacao;

    @Column(name = "km_atual")
    private Double kmAtual;

    @Column(name = "km_ultima_manutencao")
    private Double kmUltimaManutencao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Status {
        DISPONIVEL, EM_USO, EM_MANUTENCAO
    }

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public Double getKmAtual() {
        return kmAtual;
    }

    public void setKmAtual(Double kmAtual) {
        this.kmAtual = kmAtual;
    }

    public Double getKmUltimaManutencao() {
        return kmUltimaManutencao;
    }

    public void setKmUltimaManutencao(Double kmUltimaManutencao) {
        this.kmUltimaManutencao = kmUltimaManutencao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    
}

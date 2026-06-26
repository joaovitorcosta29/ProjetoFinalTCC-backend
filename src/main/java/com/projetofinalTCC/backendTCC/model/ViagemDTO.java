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
@Table(name = "tb_viagens")
public class ViagemDTO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_viagem")
    private Long idViagem;
    
    @Column(name = "id_usuario")
    private Long idUsuario;
    
    @Column(name = "id_veiculo")
    private Long idVeiculo;
    
    private String destino;
    
    @Column(name = "km_inicial")
    private Double kmInicial;
    
    @Column(name = "km_final")
    private Double kmFinal;
      
    @Enumerated(EnumType.STRING)
    @Column(name = "status_viagem", nullable = false)
    private StatusViagem statusViagem;

    public enum StatusViagem {
        EM_ANDAMENTO, FINALIZADA
    }        
    
    @Enumerated(EnumType.STRING)
    @Column(name = "alerta_manutencao", nullable = false)
    private AlertaManutencao alertaManutencao;

    public enum AlertaManutencao {
        OK, REVISAO_NECESSARIA
    }  
}

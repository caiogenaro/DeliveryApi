package com.caiofood.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "restaurante")
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="nome_Restaurante")
    private String nomeRestaurante;

    @Column(name ="taxa_frete")
    private BigDecimal taxaFrete;

    @Column(name ="ativo")
    private Boolean ativo;

    @Column(name ="aberto")
    private Boolean aberto;

//    @Column(name ="data_Cadastro")
//    private LocalDateTime dataCadastro;
//
//    @Column(name ="data_Atualizacao")
//    private LocalDateTime dataAtualizacao;

    @ManyToOne
    @JoinColumn(name ="cozinha_id")
    private Cozinha cozinha;



}

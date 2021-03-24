package com.caiofood.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;


@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "cozinha")
public class Cozinha {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name ="nome_cozinha")
    private String nome;



}

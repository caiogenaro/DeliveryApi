package com.delivery.api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteDto implements Serializable {
    private static final long serialVersionUID = -7578940996801564274L;

    private Long idRestaurante;

    @NotNull(message = "Nome do Restaurante é Obrigatorio")
    @Length(min = 3, max = 250, message = "Não é permido nomes menores que 3 caracteres ou maiores que 250")
    private String nomeRestarante;

    private Long idTipoCozinha;


    @NotNull(message = "Nome da Cozinha é Obrigatorio")
    @Length(min = 3, max = 250, message = "Não é permido nomes menores que 3 caracteres ou maiores que 250")
    private String nomeCozinha;


    private Boolean restauranteAtivo;
    private BigDecimal restauranteTaxaFrete;


}

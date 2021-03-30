package com.caiofood.api.model.parser;

import com.caiofood.api.dto.RestauranteDto;
import com.caiofood.api.model.Cozinha;
import com.caiofood.api.model.Restaurante;

import java.util.*;

public class RestauranteParser {


    public static Restaurante toEntity(RestauranteDto dto) {
        Restaurante entidade = new Restaurante();
        entidade.setCozinha(new Cozinha());

        entidade.setId(dto.getIdRestaurante());
        entidade.getCozinha().setId(dto.getIdTipoCozinha());
        entidade.setNomeRestaurante(dto.getNomeRestarante());
        entidade.getCozinha().setNome(dto.getNomeCozinha());
        entidade.setAtivo(dto.getRestauranteAtivo());
        entidade.setTaxaFrete(dto.getRestauranteTaxaFrete());

        return entidade;
    }


    public static RestauranteDto toDTO(Restaurante entidade) {
        RestauranteDto dto = new RestauranteDto();

        if(entidade.getCozinha() !=null){
            dto.setIdTipoCozinha(entidade.getCozinha().getId());
            dto.setNomeCozinha(entidade.getCozinha().getNome());
        }

        dto.setIdRestaurante(entidade.getId());
        dto.setNomeRestarante(entidade.getNomeRestaurante());
        dto.setRestauranteAtivo(entidade.getAtivo());
        dto.setRestauranteTaxaFrete(entidade.getTaxaFrete());

        return dto;
    }

    public static List<RestauranteDto> toDTOList(List<Restaurante> listaRestaurante) {
        List<RestauranteDto> dtoList = new ArrayList<>();
        
        for (Restaurante restaurante : listaRestaurante) {

            dtoList.add(toDTO(restaurante));
        }
        return dtoList;

    }
}

package com.delivery.api.service;

import com.delivery.api.dto.RestauranteDto;
import com.delivery.api.exception.EntidadeEmUsoException;
import com.delivery.api.exception.EntidadeNãoEncontradaException;
import com.delivery.api.model.Restaurante;
import com.delivery.api.model.parser.RestauranteParser;
import com.delivery.api.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;



    public Restaurante salvar(Restaurante restaurante){
        return restauranteRepository.salvar(restaurante);
    }

    public void excluir(Long restauranteId){
        try{
            restauranteRepository.remover(restauranteId);
        }catch (EmptyResultDataAccessException e) {
            throw new EntidadeNãoEncontradaException(
                    String.format("Não existe cadastro de cozinha com codigo %d", restauranteId));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("Restaurante de codigo %d não pode ser removida, pois está em uso", restauranteId));
        }
    }

    public RestauranteDto buscar(Long restauranteId) {
        return RestauranteParser.toDTO(restauranteRepository.buscar(restauranteId));
    }

    public List<RestauranteDto> listar() {
        return RestauranteParser.toDTOList(restauranteRepository.listar());
    }
}

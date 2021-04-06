package com.delivery.api.service;

import com.delivery.api.exception.EntidadeEmUsoException;
import com.delivery.api.exception.EntidadeNãoEncontradaException;
import com.delivery.api.model.Cozinha;
import com.delivery.api.repository.CozinhaRepository;
import com.delivery.api.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha){
        return cozinhaRepository.salvar(cozinha);
    }

    public void excluir(Long cozinhaId){
        try{
            cozinhaRepository.remover(cozinhaId);
        }catch (EmptyResultDataAccessException e) {
            throw new EntidadeNãoEncontradaException(
                    String.format("Não existe cadastro de cozinha com codigo %d", cozinhaId));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de codigo %d não pode ser removida, pois está em uso", cozinhaId));
        }
    }
}

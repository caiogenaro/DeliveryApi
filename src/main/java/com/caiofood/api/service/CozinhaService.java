package com.caiofood.api.service;

import com.caiofood.api.exception.EntidadeEmUsoException;
import com.caiofood.api.exception.EntidadeNãoEncontradaException;
import com.caiofood.api.model.Cozinha;
import com.caiofood.api.repository.CozinhaRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

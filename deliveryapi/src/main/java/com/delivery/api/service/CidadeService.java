package com.delivery.api.service;

import com.delivery.api.exception.EntidadeEmUsoException;
import com.delivery.api.exception.EntidadeNãoEncontradaException;
import com.delivery.api.model.Cidade;
import com.delivery.api.model.Estado;
import com.delivery.api.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade salvar(Cidade cidade){return cidadeRepository.save(cidade);
    }

    public void excluir(Long cidadeId){
        try{
            cidadeRepository.deleteById(cidadeId);
        }catch (EmptyResultDataAccessException e) {
            throw new EntidadeNãoEncontradaException(
                    String.format("Não existe cadastro de cidade com codigo %d", cidadeId));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("Cidade de codigo %d não pode ser removido, pois está em uso", cidadeId));
        }
    }
}

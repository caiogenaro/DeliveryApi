package com.delivery.api.service;

import com.delivery.api.exception.EntidadeEmUsoException;
import com.delivery.api.exception.EntidadeNãoEncontradaException;
import com.delivery.api.model.Cidade;
import com.delivery.api.model.Cozinha;
import com.delivery.api.model.Estado;
import com.delivery.api.repository.CidadeRepository;
import com.delivery.api.repository.CozinhaRepository;
import com.delivery.api.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository

    public Estado salvar(Estado estado){
        Long cidadeId = estado.getCidade().getId();
        Cidade cidade = cidadeRepository.findById(cidadeId)
                .orElseThrow(() -> new EntidadeNãoEncontradaException(String.format("Nao existe cadastro de cidade com o codigo %d", cidadeId)));
        return estadoRepository.save(estado);
    }

    public void excluir(Long estadoId){
        try{
            estadoRepository.deleteById(estadoId);
        }catch (EmptyResultDataAccessException e) {
            throw new EntidadeNãoEncontradaException(
                    String.format("Não existe cadastro de cozinha com codigo %d", estadoId));
        }catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("Estado de codigo %d não pode ser removido, pois está em uso", estadoId));
        }
    }
}

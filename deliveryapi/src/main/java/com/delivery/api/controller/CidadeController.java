package com.delivery.api.controller;


import com.delivery.api.exception.EntidadeEmUsoException;
import com.delivery.api.exception.EntidadeNãoEncontradaException;
import com.delivery.api.model.Cidade;
import com.delivery.api.repository.CidadeRepository;
import com.delivery.api.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cidade")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public List<Cidade> listar(){
        return cidadeRepository.findAll();
    }

    @GetMapping(value="/{cidadeId}")
    public ResponseEntity<Cidade> buscarId(@PathVariable Long cidadeId){
        Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);

        if(cidade.isPresent()){
            return ResponseEntity.ok(cidade.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade){
        Cidade cidadeSalva = cidadeService.salvar(cidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalva);
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade){
        Optional<Cidade> cidadeAtual = cidadeRepository.findById(cidadeId);
        if(cidadeAtual.isPresent()){
            BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");
            Cidade cidadeSalva = cidadeService.salvar(cidadeAtual.get());
            return ResponseEntity.ok(cidadeSalva);
        }
        if(cidadeAtual.isEmpty()){
            throw new EntidadeNãoEncontradaException(
                    String.format("Não existe Cidade com o id %d", cidadeId));
        }
         return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<Cidade> remover(@PathVariable Long cidadeId){
        try{
            cidadeService.excluir(cidadeId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNãoEncontradaException e){
            return ResponseEntity.notFound().build();
        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }



}

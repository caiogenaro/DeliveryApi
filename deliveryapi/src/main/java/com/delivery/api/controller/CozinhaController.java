package com.delivery.api.controller;

import com.delivery.api.exception.EntidadeEmUsoException;
import com.delivery.api.exception.EntidadeNãoEncontradaException;
import com.delivery.api.model.Cozinha;
import com.delivery.api.repository.CozinhaRepository;
import com.delivery.api.repository.RestauranteRepository;
import com.delivery.api.service.CozinhaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value ="/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping
    public List<Cozinha> listar(){
        return cozinhaRepository.listar();
    }

    @GetMapping(value = "/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId){
        Cozinha cozinha =  cozinhaRepository.buscar(cozinhaId);
        if(cozinha != null){
        return ResponseEntity.ok(cozinha);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/listar-por-nome")
    public List<Cozinha> listarPorNome(@RequestParam("nome") String nome){
        return cozinhaRepository.listarPorNome(nome);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha){
        return cozinhaService.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha){
        Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);
        if(cozinhaAtual != null){
        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id"); //Ignora o Id na ultima propriedade
        cozinhaService.salvar(cozinhaAtual);
        return ResponseEntity.ok(cozinhaAtual);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cozinhaid}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaid){
        try{
            cozinhaService.excluir(cozinhaid);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNãoEncontradaException e){
            return ResponseEntity.notFound().build();
        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PatchMapping("/{cozinhaId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long cozinhaId, @RequestBody Map<String, Object> campos){
        Cozinha cozinhaAtual = cozinhaRepository.buscar(cozinhaId);
        if(cozinhaAtual == null){
            return  ResponseEntity.notFound().build();
        }
        merge(campos, cozinhaAtual);
        return atualizar(cozinhaId, cozinhaAtual);
    }

    private void merge(@RequestBody Map<String, Object> camposOrigem, Cozinha cozinhaDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Cozinha cozinhaOrigem = objectMapper.convertValue(camposOrigem, Cozinha.class);
        camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Cozinha.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, cozinhaOrigem);

            ReflectionUtils.setField(field, cozinhaDestino, novoValor);
        });
    }


}

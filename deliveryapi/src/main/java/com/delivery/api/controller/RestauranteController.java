package com.delivery.api.controller;

import com.delivery.api.dto.RestauranteDto;
import com.delivery.api.exception.EntidadeEmUsoException;
import com.delivery.api.exception.EntidadeNãoEncontradaException;
import com.delivery.api.model.Cozinha;
import com.delivery.api.model.Restaurante;
import com.delivery.api.repository.CozinhaRepository;
import com.delivery.api.repository.RestauranteRepository;
import com.delivery.api.service.CozinhaService;
import com.delivery.api.service.RestauranteService;
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
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public List<RestauranteDto> listar(){
        return restauranteService.listar();
    }

    @GetMapping(value = "/{restauranteId}")
    public ResponseEntity<RestauranteDto> buscar(@PathVariable Long restauranteId){
        RestauranteDto restaurante =  restauranteService.buscar(restauranteId);
        if(restaurante != null){
            return ResponseEntity.ok(restaurante);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/listar-por-nome")
    public List<Restaurante> listarPorNome(@RequestParam("nome") String nome){
        return restauranteRepository.listarPorNome(nome);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@RequestBody Restaurante restaurante){
        return restauranteService.salvar(restaurante);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante){
        Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);
        if(restauranteAtual != null){
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id"); //Ignora o Id na ultima propriedade
            restauranteService.salvar(restauranteAtual);
            return ResponseEntity.ok(restauranteAtual);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> remover(@PathVariable Long restauranteId){
        try{
            restauranteService.excluir(restauranteId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNãoEncontradaException e){
            return ResponseEntity.notFound().build();
        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos){
        Restaurante restauranteAtual = restauranteRepository.buscar(restauranteId);
        if(restauranteAtual == null){
            return  ResponseEntity.notFound().build();
        }
        merge(campos, restauranteAtual);
        return atualizar(restauranteId, restauranteAtual);
    }

    private void merge(@RequestBody Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class);
        camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Cozinha.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }

}

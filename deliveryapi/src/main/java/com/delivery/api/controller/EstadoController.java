package com.delivery.api.controller;


import com.delivery.api.exception.EntidadeEmUsoException;
import com.delivery.api.exception.EntidadeNãoEncontradaException;
import com.delivery.api.model.Estado;
import com.delivery.api.repository.EstadoRepository;
import com.delivery.api.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/estado")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public List<Estado> listar(){
        return estadoRepository.findAll();
    }

    @GetMapping(value="/{estadoId}")
    public ResponseEntity<Estado> buscarId(@PathVariable Long estadoId){
        Optional<Estado> estado = estadoRepository.findById(estadoId);

        if(estado.isPresent()){
            return ResponseEntity.ok(estado.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Estado> salvar(@RequestBody Estado estado){
        Estado estadoSalvo = estadoService.salvar(estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoSalvo);
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado){
        Optional<Estado> estadoAtual = estadoRepository.findById(estadoId);
        if(estadoAtual.isPresent()){
            BeanUtils.copyProperties(estado, estadoAtual.get(), "id");
            Estado estadoSalvo = estadoService.salvar(estadoAtual.get());
            return ResponseEntity.ok(estadoSalvo);
        }
        if(estadoAtual.isEmpty()){
            throw new EntidadeNãoEncontradaException(
                    String.format("Não existe estado com o id %d", estadoId));
        }
         return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<Estado> remover(@PathVariable Long estadoId){
        try{
            estadoService.excluir(estadoId);
            return ResponseEntity.noContent().build();
        }catch (EntidadeNãoEncontradaException e){
            return ResponseEntity.notFound().build();
        }catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }



}

package com.caiofood.api.repository;

import com.caiofood.api.model.Cozinha;
import java.util.List;



public interface CozinhaRepository {

    List<Cozinha> listar();
    Cozinha buscar(Long id);
    Cozinha salvar(Cozinha cozinha);
    void remover(Long id);

}

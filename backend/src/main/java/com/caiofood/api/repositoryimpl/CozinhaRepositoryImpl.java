package com.caiofood.api.repositoryimpl;

import com.caiofood.api.model.Cozinha;
import com.caiofood.api.repository.CozinhaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cozinha> listar(){
        return entityManager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

    @Override
    public Cozinha buscar(Long id){
        return entityManager.find(Cozinha.class, id);
    }

    @Transactional
    @Override
    public Cozinha salvar(Cozinha cozinha){
        return entityManager.merge(cozinha);
    }

    @Transactional
    @Override
    public void remover(Long id){
        Cozinha cozinha = buscar(id);
        if(cozinha == null){
            throw new EmptyResultDataAccessException(1);
        }
        entityManager.remove(cozinha);
    }

    @Override
    public List<Cozinha> listarPorNome(String nome) {
        return entityManager.createQuery("from Cozinha where nome = :nome", Cozinha.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();

    }


}

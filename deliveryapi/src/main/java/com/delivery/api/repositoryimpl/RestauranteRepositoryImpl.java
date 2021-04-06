package com.delivery.api.repositoryimpl;

import com.delivery.api.model.Restaurante;
import com.delivery.api.repository.RestauranteRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurante> listar(){
        return entityManager.createQuery("from Restaurante", Restaurante.class).getResultList();
    }

    @Override
    public Restaurante buscar(Long id){
        return entityManager.find(Restaurante.class, id);
    }

    @Transactional
    @Override
    public Restaurante salvar(Restaurante restaurante){
        return entityManager.merge(restaurante);
    }

    @Transactional
    @Override
    public void remover(Long id){
        Restaurante restaurante = buscar(id);
        if(restaurante == null){
            throw new EmptyResultDataAccessException(1);
        }
        entityManager.remove(restaurante);
    }

    @Override
    public List<Restaurante> listarPorNome(String nome) {
        return entityManager.createQuery("from Restaurante where nome like :nome", Restaurante.class)
                .setParameter("nome", "%"+nome+"%")
                .getResultList();

    }
}

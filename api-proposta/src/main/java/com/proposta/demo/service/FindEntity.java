package com.proposta.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.module.ResolutionException;
import java.util.Optional;

@Service
public class FindEntity {

    @PersistenceContext
    private EntityManager manager;

                //CDD 1
    public <T> T findById(Class<T> entity, Object id){
        T entityFindedOrNot = (T) manager.find(entity, id);
        Optional<T> optionalEntity = Optional.ofNullable(entityFindedOrNot);

        String msg = String.format("Não foi encontrado(a) '%s' com o id %s", entity.getSimpleName(), id.toString());
                                                            //1
        return optionalEntity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, msg));
    }

}
//PONTOS CDD: 2
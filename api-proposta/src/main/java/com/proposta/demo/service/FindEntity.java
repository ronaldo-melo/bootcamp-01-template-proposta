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
                //CDD 1
    public static <T> ResponseEntity<T> findEntityById(Class<T> entity, Long id, EntityManager manager){
        T entityFindedOrNot = (T) manager.find(entity, id);
        Optional<T> optionalEntity = Optional.ofNullable(entityFindedOrNot);
                                                            //1
        return ResponseEntity.ok(optionalEntity.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

}
//PONTOS CDD: 2
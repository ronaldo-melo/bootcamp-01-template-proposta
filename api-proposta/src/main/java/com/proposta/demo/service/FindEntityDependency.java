package com.proposta.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import java.util.Optional;

public class FindEntityDependency {
                                                // CDD 1
    public static <T> T findEntityDepedenceDyId(Class<T> entity, Object id, EntityManager manager){

        T entityFinded = (T) manager.find(entity, id);

        return Optional.ofNullable(entityFinded)
                                // CDD 2
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Não existe " + entity.getSimpleName() + " com o id " + id));
    }
    //PONTOS CDD: 2
}
package com.proposta.demo.Repository;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ExecutorTransacao {

    @PersistenceContext
    private EntityManager manager;

    public <T> T salvaEcomita(T t){
        manager.persist(t);
        return t;
    }

    @Transactional
    public <T> T atualizaEComita(T t){
        return manager.merge(t);
    }

}

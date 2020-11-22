package com.proposta.demo.service;

import com.proposta.demo.model.Bloqueio;
import com.proposta.demo.model.Cartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class SalvaNovoBloqueio {

    @Autowired
    private FindEntity findEntity;

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public Long salvar(HttpServletRequest request, UUID id){

        String userAgent = request.getHeader("User-Agent");
        String ipDaRequisicao = request.getRemoteAddr();
        Cartao cartao = findEntity.findById(Cartao.class, id);
        Bloqueio novoBloqueio = new Bloqueio(userAgent, ipDaRequisicao, cartao); //CCD: 2
        Long idNovoBloqueio = manager.merge(novoBloqueio).getId();
        cartao.adicionarBloqueio(novoBloqueio);
        manager.merge(cartao);

        return idNovoBloqueio;
    }

}
//PONTOS CCD: 2
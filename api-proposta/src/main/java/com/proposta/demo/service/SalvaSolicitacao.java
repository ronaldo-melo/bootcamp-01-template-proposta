package com.proposta.demo.service;

import com.proposta.demo.model.Cartao;
import com.proposta.demo.model.Solicitacao;
import com.proposta.demo.service.FindEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class SalvaSolicitacao {

    private String userAgent;
    private String ipDoClient;
    private Cartao cartao;

    @Transactional
    public Long salvar(UUID idCartao, HttpServletRequest httpServletRequest, EntityManager manager){
        userAgent = httpServletRequest.getHeader("User-Agent");
        ipDoClient = httpServletRequest.getRemoteAddr();
        cartao = FindEntity.findEntityById(Cartao.class, idCartao, manager);

        Solicitacao novaSolicitacao = new Solicitacao(ipDoClient, userAgent, cartao);
        return manager.merge(novaSolicitacao).getId();
    }

}

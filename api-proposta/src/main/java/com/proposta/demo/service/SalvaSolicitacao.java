package com.proposta.demo.service.model;

import com.proposta.demo.model.Cartao;
import com.proposta.demo.model.Solicitacao;
import com.proposta.demo.service.FindEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class SalvaSolicitacao {

    @Transactional
    public Long salvar(UUID idCartao, SolicitacaoRequest request, HttpServletRequest httpServletRequest, EntityManager manager){
        Cartao cartao = FindEntity.findEntityById(Cartao.class, idCartao, manager);
        String userAgente = httpServletRequest.getHeader("User-Agent");
        String ipDoClient = httpServletRequest.getRemoteAddr();
        
        Solicitacao novaSolicitacao = new Solicitacao();
    }

}

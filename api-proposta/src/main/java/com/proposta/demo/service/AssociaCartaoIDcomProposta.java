package com.proposta.demo.service;

import com.proposta.demo.model.Cartao;
import com.proposta.demo.model.Proposta;
import com.proposta.demo.model.enums.StatusAvaliacaoProposta;
import com.proposta.demo.request.CartaoRequest;
import com.proposta.demo.util.CartaoRequestToModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class AssociaCartaoIDcomProposta {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private RecuperaCartao recuperaCartao; //CDD 1

    private Logger logger = LoggerFactory.getLogger(AssociaCartaoIDcomProposta.class);

    @Scheduled(fixedDelay = 5 * 1000L)
    @Transactional
    public void associa(){
                                                                                          //CDD 2
       manager.createQuery("SELECT p FROM Proposta p WHERE p.estadoProposta =:value", Proposta.class)
                                                        //3
                    .setParameter("value", StatusAvaliacaoProposta.ELEGIVEL)
                    .getResultList()
               .forEach(p -> {      //CDD 4

                /* 1. Identificador da proposta será utilizado como base para consulta do cartão no sistema legado "accounts". */

                                    //CDD 5
                ResponseEntity<CartaoRequest> request = recuperaCartao.recuperarPorIdProposta(p.getId());

                /* 2. Quando o sistema de accounts(cartões) retornar sucesso (status code na faixa 200)
                o número de cartão deve ser atrelado a proposta (id que é retornado no resultado da consulta).*/

               if(request.getStatusCode().is2xxSuccessful()){ //CDD 6

                   /*2.1 Atrelar o número do cartão gerado a proposta.
                   A proposta quando aprovada deve ser relacionada com um número do cartão (o id).*/

                   Cartao cartao = CartaoRequestToModel.toModel(request.getBody(), manager);
                   cartao = manager.merge(cartao);
                   logger.info("Vinculou o cartão com a proposta do documento {}", p.getDocumento());
                   p.vinculaCartao(cartao);
                   manager.merge(p);
               }

               /* 3. Quando o sistema de accounts(cartões) retornar falha (status code na faixa 400 ou 500) não
               atualizar o estado da proposta, pois ainda não foi processado, aguardar próxima iteração.*/

               else if (request.getStatusCode().is4xxClientError() || request.getStatusCode().is5xxServerError()){ //CDD 7
                   throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Processando dados da proposta" +
                           ". Por favor, Aguarde.");
               }

       } );
        //CDD = 7
    }

}
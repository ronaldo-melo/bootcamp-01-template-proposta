package com.proposta.demo.service;

import com.proposta.demo.service.model.bloqueio.ResultadoBloqueio;
import com.proposta.demo.service.model.bloqueio.SolicitacaoBloqueio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.UUID;

@Service
@Validated
public class BloqueioCartaoResultado {

    private final RestTemplate restTemplate = new RestTemplate();

    private ResponseEntity<ResultadoBloqueio> request; //CDD 1

    @Value("${spring.application.name}")
    private String nomeDaAplicacao;

    public ResponseEntity<ResultadoBloqueio> tentaBloqueio(UUID id){  //CDD 2

         try {  //CDD 3
            SolicitacaoBloqueio solicitacaoBloqueio = new SolicitacaoBloqueio();
            solicitacaoBloqueio.setSistemaResponsavel(nomeDaAplicacao);
            String url = "http://localhost:8888/api/cartoes/" + id.toString() + "/bloqueios";
            request = restTemplate.postForEntity(url, solicitacaoBloqueio, ResultadoBloqueio.class);

         } catch (HttpClientErrorException h) { //CDD 4
             //Quando o retorno do sistema bancário retornar erro (status code na faixa 400 ou 500) não devemos alterar o estado do cartão.
             throw new ResponseStatusException(h.getStatusCode(), "Falha ao bloquear o cartão!");
         }
         catch (RestClientException r){  //CDD 5
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao consultar o sistema externo!");
         }

        return new ResponseEntity<>(request.getBody(), request.getStatusCode());
    }
    //PONTOS CDD: 6
}

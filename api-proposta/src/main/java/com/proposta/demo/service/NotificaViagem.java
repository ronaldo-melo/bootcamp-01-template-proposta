package com.proposta.demo.service;

import com.proposta.demo.service.model.viagem.SolicitacaoAvisoViagem;
import com.proposta.demo.service.model.viagem.ResultadoAvisoViagem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.UUID;

@Service
public class NotificaViagem {

    private RestTemplate template = new RestTemplate();
                            //CDD 1
    public void notificar(SolicitacaoAvisoViagem avisoViagem, UUID idCartao){

        String url = "http://localhost:8888/api/cartoes/" + idCartao.toString() + "/avisos";
        ResponseEntity<?> response = template.postForEntity(URI.create(url), avisoViagem, ResultadoAvisoViagem.class);  //CDD 2

        if (response.getStatusCodeValue() == 400 || response.getStatusCodeValue() == 500) //CDD 3
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Não pode salvar aviso de viagem no sistema");

    }

}//PONTOS CDD: 3

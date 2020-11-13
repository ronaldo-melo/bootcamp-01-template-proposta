package com.proposta.demo.service;

import com.proposta.demo.request.CartaoRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Service
@Validated
public class RecuperaCartao {

    private final RestTemplate restTemplate = new RestTemplate();

    private ResponseEntity<CartaoRequest> request;
                            //CDD 1
    public ResponseEntity<CartaoRequest> getCartaoPorIdProposta(Long idProposta) {

        try {  //CDD 2

            UriComponents uri = UriComponentsBuilder.newInstance()
                    .scheme("http")
                    .host("localhost:8888")
                    .path("/api/cartoes")
                    .queryParam("idProposta", idProposta)
                    .build();
                                                                    //CDD 3
            request = restTemplate.getForEntity(uri.toUriString(), CartaoRequest.class);

        } catch (RestClientException r){  //CDD 4
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>(request.getBody(), request.getStatusCode());
    }

    public ResponseEntity<CartaoRequest> getCartaoPorId(UUID idCartao){

        String url = "http://localhost:8888/api/cartoes";

        try {  //CDD 5
            request = restTemplate.getForEntity(url + "/" + idCartao.toString(), CartaoRequest.class);
        } catch (RestClientException r){  //CDD
            // 6
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>(request.getBody(), request.getStatusCode());
    }

}
//PONTOS CDD: 6
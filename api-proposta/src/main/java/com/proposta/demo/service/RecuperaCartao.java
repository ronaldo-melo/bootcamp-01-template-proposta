package com.proposta.demo.service;

import com.proposta.demo.request.CartaoRequest;
import com.proposta.demo.response.CartaoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Validated
public class RecuperaCartao {

    private final RestTemplate restTemplate = new RestTemplate();

    //consulta o cartao pelo id da
    public ResponseEntity<CartaoResponse> getCartao (Long idProposta) {

        //return new CartaoResponse(cartaoIntegracao.getCartao().getBody().toModel());
        UriComponents uri = UriComponentsBuilder.newInstance()
                    .scheme("http")
                    .host("localhost:8888")
                    .path("/api/cartoes")
                    .queryParam("idProposta", idProposta)
                .build();

        ResponseEntity<CartaoRequest> entity = restTemplate.getForEntity(uri.toUriString(), CartaoRequest.class);

        CartaoRequest request = entity.getBody();

        return new ResponseEntity<>(request.toModel().toResponse(), entity.getStatusCode());
    }


    public ResponseEntity<CartaoResponse> getCartaoPorId(String idCartao){

        String url = "http://localhost:8888/api/cartoes" ;

        ResponseEntity<CartaoRequest> request = restTemplate.getForEntity(url + "/" + idCartao, CartaoRequest.class);
        
        return new ResponseEntity<>(request.getBody().toModel().toResponse(), request.getStatusCode());

    }

}

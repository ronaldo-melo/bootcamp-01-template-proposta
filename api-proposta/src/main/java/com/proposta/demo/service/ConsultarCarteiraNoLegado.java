package com.proposta.demo.service;

import com.proposta.demo.service.model.carteiradigital.ResultadoCarteira;
import com.proposta.demo.service.model.carteiradigital.SolicitacaoInclusaoCarteira;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class ConsultarCarteiraNoLegado {

    private RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<ResultadoCarteira> resultado; //CDD 1
                                                          //CDD 2
    public ResultadoCarteira consultar(SolicitacaoInclusaoCarteira solicitacao, UUID idCartao){

        try { //CDD 3

            String url = "http://localhost:8888/api/cartoes/" + idCartao.toString() + "/carteiras";
            ResponseEntity<ResultadoCarteira> resultado = restTemplate.postForEntity(url, solicitacao, ResultadoCarteira.class);
            if (resultado.getStatusCode().is4xxClientError() || resultado.getStatusCode().is5xxServerError()) { //CDD 4
                String msg = String.format("Erro ao vincular cartão a carteira digital informada!");
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, msg);
            }

            return resultado.getBody();

        } catch (HttpClientErrorException h) { //CDD 5
            throw new ResponseStatusException(h.getStatusCode(), "Falha ao consulta! ");
        }
        catch (RestClientException r){  //CDD 6
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Falha na comunicação com o sistema externo!");
        }

    }

}

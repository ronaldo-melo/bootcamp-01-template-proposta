package com.proposta.demo.service;

import com.proposta.demo.service.model.ResultadoAnalise;
import com.proposta.demo.service.model.SolicitacaoAnalise;
import com.proposta.demo.model.enums.StatusAvaliacaoProposta;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

@Service
@Validated
public class AvaliaProposta {

    public ResultadoAnalise retornarAnalise(SolicitacaoAnalise solicitacaoAnalise){

        String url = "http://localhost:9999/api/solicitacao";

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<SolicitacaoAnalise> request = new HttpEntity<>(solicitacaoAnalise);

        ResultadoAnalise resultadoAnalise = restTemplate.postForEntity(url, request, ResultadoAnalise.class).getBody();

        return resultadoAnalise;
    }

    public StatusAvaliacaoProposta getStatusAvaliacao(SolicitacaoAnalise solicitacaoAnalise){

        return this.retornarAnalise(solicitacaoAnalise).getStatusAvaliacao();

    }

}

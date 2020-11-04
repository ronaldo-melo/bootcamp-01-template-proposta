package com.proposta.demo.service;

import com.proposta.demo.infrastructure.Integracoes;
import com.proposta.demo.model.ResultadoAnalise;
import com.proposta.demo.model.SolicitacaoAnalise;
import com.proposta.demo.model.enums.StatusAvaliacaoProposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@Validated
public class AvaliaProposta {

    @Autowired
    private Integracoes integracoes;

    public ResultadoAnalise retornarAnalise(SolicitacaoAnalise solicitacaoAnalise){

        ResultadoAnalise analise = integracoes.avalia(solicitacaoAnalise).getBody();

        return analise;
    }

    public StatusAvaliacaoProposta getStatusAvaliacao(SolicitacaoAnalise solicitacaoAnalise){

        return this.retornarAnalise(solicitacaoAnalise).getStatusAvaliacao();

    }

}

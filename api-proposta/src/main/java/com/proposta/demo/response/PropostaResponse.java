package com.proposta.demo.response;

import com.proposta.demo.model.Proposta;
import com.proposta.demo.model.enums.StatusAvaliacaoProposta;

public class PropostaResponse {

    private String nome;

    private String email;

    private StatusAvaliacaoProposta estadoProposta;

    public PropostaResponse(Proposta proposta){
        this.nome = proposta.getNome();
        this.email = proposta.getEmail();
        this.estadoProposta = proposta.getStatusAvaliacaoProposta();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public StatusAvaliacaoProposta getEstadoProposta() {
        return estadoProposta;
    }
}

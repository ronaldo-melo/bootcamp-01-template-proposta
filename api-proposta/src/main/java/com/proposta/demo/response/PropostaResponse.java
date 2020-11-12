package com.proposta.demo.response;

import com.proposta.demo.model.Cartao;
import com.proposta.demo.model.Proposta;
import com.proposta.demo.model.enums.StatusAvaliacaoProposta;

public class PropostaResponse {

    private String nome;

    private String email;

    private StatusAvaliacaoProposta estadoProposta;

    private CartaoResponse cartaoResponse;

    public PropostaResponse(Proposta proposta){
        this.nome = proposta.getNome();
        this.email = proposta.getEmail();
        this.estadoProposta = proposta.getEstadoProposta();
        this.cartaoResponse = new CartaoResponse(proposta.getCartao());
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

    public CartaoResponse getCartao() {
        return cartaoResponse;
    }
}

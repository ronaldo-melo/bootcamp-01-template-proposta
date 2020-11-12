package com.proposta.demo.response;

import com.proposta.demo.model.Cartao;

import java.time.format.DateTimeFormatter;

public class CartaoResponse {

    private String id;

    public CartaoResponse(Cartao cartao) {
        this.id = cartao.getIdCartao().toString();
    }

    public String getNumeroDoCartao() {
        return id;
    }

}
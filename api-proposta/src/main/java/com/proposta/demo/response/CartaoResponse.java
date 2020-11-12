package com.proposta.demo.response;

import com.proposta.demo.model.Cartao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CartaoResponse {

    private String id;

    private String emitidoEm;

    public CartaoResponse(Cartao cartao) {
        this.id = cartao.getIdCartao().toString();
        String timeColonPattern = "dd/MM/yyy hh:mm";
        DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);

        this.emitidoEm = timeColonFormatter.format(cartao.getEmitidoEm());
    }

    public String getCartao() {
        return id;
    }

    public String getEmitidoEm() {
        return emitidoEm;
    }
}
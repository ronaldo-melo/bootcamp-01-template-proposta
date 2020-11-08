package com.proposta.demo.response;

import com.proposta.demo.model.Cartao;

import java.time.format.DateTimeFormatter;

public class CartaoResponse {

    private String titular;

    private String emitidoEm;

    private String id;

    private Long idProposta;

    public CartaoResponse(Cartao cartao) {
        this.titular = cartao.getTitular();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        this.emitidoEm = cartao.getEmitidoEm().format(formatter);
        this.id = cartao.getIdCartao().toString();
        this.idProposta = cartao.getIdProposta();
    }

    public String getTitular() {
        return titular;
    }

    public String getEmitidoEm() {
        return emitidoEm;
    }

    public String getId() {
        return id;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
package com.proposta.demo.response;

import com.proposta.demo.model.Bloqueio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BloqueioResponse {

    private String bloqueadoEm;
    private String userAgent;
    private String ipDaRequisicao;
    private CartaoResponse cartaoResponse;

    public BloqueioResponse(Bloqueio bloqueio) {
        String timeColonPattern = "dd/MM/yyy hh:mm";
        DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);

        this.bloqueadoEm = timeColonFormatter.format(bloqueio.getBloqueadoEm());
        this.userAgent = bloqueio.getUserAgent();
        this.ipDaRequisicao = bloqueio.getIpDaRequisicao();
        this.cartaoResponse = new CartaoResponse(bloqueio.getCartao());
    }

    public String getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getIpDaRequisicao() {
        return ipDaRequisicao;
    }

    public CartaoResponse getCartao() {
        return cartaoResponse;
    }
}

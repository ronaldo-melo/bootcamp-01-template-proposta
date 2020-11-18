package com.proposta.demo.response;

import com.proposta.demo.model.Solicitacao;

import java.time.LocalDateTime;

public class SolicitacaoResponse {

    private LocalDateTime instanteDaSolicitacao;
    private String ipDoCliente;
    private String userAgent;
    private CartaoResponse cartao;

    public SolicitacaoResponse(Solicitacao solicitacao) {
       this.instanteDaSolicitacao = solicitacao.getInstanteDaSolicitacao();
       this.ipDoCliente = solicitacao.getIpDoCliente();
       this.ipDoCliente = solicitacao.getIpDoCliente();
       this.cartao = solicitacao.getCartao().toResponse();
    }

    public LocalDateTime getInstanteDaSolicitacao() {
        return instanteDaSolicitacao;
    }

    public String getIpDoCliente() {
        return ipDoCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public CartaoResponse getCartao() {
        return cartao;
    }
}

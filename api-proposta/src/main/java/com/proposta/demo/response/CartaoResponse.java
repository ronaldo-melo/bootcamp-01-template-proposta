package com.proposta.demo.response;

import com.proposta.demo.model.Cartao;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;

public class CartaoResponse {

    private String id;

    private String emitidoEm;

    public CartaoResponse(Cartao cartao) {
        try {
            this.id = cartao.getIdCartao().toString();
            String timeColonPattern = "dd/MM/yyy hh:mm";
            DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);

            this.emitidoEm = timeColonFormatter.format(cartao.getEmitidoEm());
        } catch (NullPointerException n){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aguarde alguns segundos até vinculação entre Cartão e Proposta ser concluída no sistema");
        }
    }

    public String getCartao() {
        return id;
    }

    public String getEmitidoEm() {
        return emitidoEm;
    }
}
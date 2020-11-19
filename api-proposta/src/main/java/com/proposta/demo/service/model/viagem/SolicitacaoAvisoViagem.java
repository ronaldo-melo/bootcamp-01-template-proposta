package com.proposta.demo.service.model.viagem;

import java.time.LocalDate;

public class SolicitacaoAvisoViagem {

    private LocalDate validoAte;

    private String destino;

    public SolicitacaoAvisoViagem(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}

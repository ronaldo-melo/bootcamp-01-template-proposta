package com.proposta.demo.service.model.bloqueio;

import org.springframework.beans.factory.annotation.Value;

public class SolicitacaoBloqueio {

    private String sistemaResponsavel;

    public SolicitacaoBloqueio() {

    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public void setSistemaResponsavel(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }
}

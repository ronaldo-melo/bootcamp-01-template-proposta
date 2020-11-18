package com.proposta.demo.request;

import org.springframework.beans.factory.annotation.Value;

public class SolicitacaoBloqueio {

    @Value("${spring.application.name}")
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

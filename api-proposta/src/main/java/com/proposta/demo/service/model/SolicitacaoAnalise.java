package com.proposta.demo.service.model;

import com.proposta.demo.model.Proposta;

import javax.validation.constraints.NotBlank;

public class SolicitacaoAnalise {

    @NotBlank
    private String documento;

    @NotBlank
    private String nome;

    @NotBlank
    private String idProposta;

    @Deprecated
    public SolicitacaoAnalise(){

    }

    public SolicitacaoAnalise(Proposta proposta){
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId().toString();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }

}

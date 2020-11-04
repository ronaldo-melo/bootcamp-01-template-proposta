package com.proposta.demo.model;

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

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdProposta(String idProposta) {
        this.idProposta = idProposta;
    }
}

package com.proposta.demo.model;

import com.proposta.demo.model.enums.ResultadoSolicitacao;
import com.proposta.demo.model.enums.StatusAvaliacaoProposta;
import com.proposta.demo.validator.DocumentoFormatoValido;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ResultadoAnalise {

    @NotBlank
    @DocumentoFormatoValido
    private String documento;

    @NotEmpty
    private String nome;

    @NotNull
    private ResultadoSolicitacao resultadoSolicitacao;

    @NotBlank
    private String idProposta;

    public ResultadoAnalise(@NotBlank String documento, @NotEmpty String nome,
                            @NotNull ResultadoSolicitacao resultadoAnalise,
                            @NotBlank String idProposta) {

        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoAnalise;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    private ResultadoSolicitacao getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public StatusAvaliacaoProposta getStatusAvaliacao(){
        return this.getResultadoSolicitacao().getStatusAvaliacao();
    }
}

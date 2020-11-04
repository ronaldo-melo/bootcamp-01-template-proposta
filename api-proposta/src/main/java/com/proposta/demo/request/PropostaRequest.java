package com.proposta.demo.request;

import com.proposta.demo.model.Proposta;

import com.proposta.demo.validator.DocumentoFormatoValido;
import com.proposta.demo.validator.DocumentoUnico;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaRequest {

    @NotBlank
    @DocumentoFormatoValido
    @DocumentoUnico
    private String documento;

    @NotBlank @NotNull
    private String nome;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String endereco;

    @Positive
    private BigDecimal salario;

    public PropostaRequest(@NotBlank @DocumentoFormatoValido @DocumentoUnico String documento,
                           @NotBlank @NotNull String nome, @Email @NotBlank String email,
                           @NotBlank String endereco, @Positive BigDecimal salario) {
        this.documento = documento;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }
            //1
    public Proposta toModel(){
        return new Proposta(this.documento, this.nome, this.email, this.endereco, this.salario);
    }

}

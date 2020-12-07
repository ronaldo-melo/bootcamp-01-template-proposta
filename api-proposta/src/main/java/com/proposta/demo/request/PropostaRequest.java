package com.proposta.demo.request;

import com.proposta.demo.model.Proposta;

import com.proposta.demo.service.EncodeDocumento;
import com.proposta.demo.validator.DocumentoFormatoValido;
import com.proposta.demo.validator.UnicoValor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaRequest {

    @NotBlank
    @DocumentoFormatoValido
    @UnicoValor(valorDoAtributo = "documento", entity = Proposta.class)
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

    public PropostaRequest(@NotBlank @DocumentoFormatoValido String documento,
                           @NotBlank @NotNull String nome, @Email @NotBlank String email,
                           @NotBlank String endereco, @Positive BigDecimal salario) {
        this.documento = documento;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }
            //1
    public Proposta toModel(EncodeDocumento encodeDocumento){
        return new Proposta(encodeDocumento.encode(documento), this.nome, this.email, this.endereco, this.salario);
    }

}

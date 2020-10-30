package com.proposta.demo.request;

import com.proposta.demo.model.Proposta;

import com.proposta.demo.validator.CpfCnpj;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaRequest {

    @NotBlank
    @CpfCnpj
    private String cpfOuCnpj;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String endereco;

    @Positive
    private BigDecimal salario;

    public PropostaRequest(@NotBlank @CpfCnpj String cpfOuCnpj, @Email @NotBlank String email,
                           @NotBlank String endereco, @Positive BigDecimal salario) {
        this.cpfOuCnpj = cpfOuCnpj;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Proposta toModel(){
        return new Proposta(this.cpfOuCnpj, this.email, this.endereco, this.salario);
    }

}

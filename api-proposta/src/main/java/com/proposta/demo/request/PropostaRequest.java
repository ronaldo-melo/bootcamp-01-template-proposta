package com.proposta.demo.request;

import com.proposta.demo.model.Proposta;

import com.proposta.demo.validator.CpfCnpj;
import com.proposta.demo.validator.CpfCnpjUnico;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaRequest {

    @NotBlank
    @CpfCnpj
    @CpfCnpjUnico
    private String cpfOuCnpj;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String endereco;

    @Positive
    private BigDecimal salario;

    public PropostaRequest(@NotBlank @CpfCnpj @CpfCnpjUnico String cpfOuCnpj, @Email @NotBlank String email,
                           @NotBlank String endereco, @Positive BigDecimal salario) {
        this.cpfOuCnpj = cpfOuCnpj;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }
            //1
    public Proposta toModel(){
        return new Proposta(this.cpfOuCnpj, this.email, this.endereco, this.salario);
    }

}

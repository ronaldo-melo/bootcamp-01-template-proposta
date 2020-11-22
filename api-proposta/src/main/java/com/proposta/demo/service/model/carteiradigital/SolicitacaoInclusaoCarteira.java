package com.proposta.demo.service.model.carteiradigital;

import com.proposta.demo.model.enums.TipoCarteira;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SolicitacaoInclusaoCarteira {

    private @NotBlank String email;
    private @NotBlank String tipoCarteira;

    public SolicitacaoInclusaoCarteira(@NotNull String email, @NotBlank String tipoCarteira) {
        this.email = email;
        this.tipoCarteira = tipoCarteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return tipoCarteira;
    }
}

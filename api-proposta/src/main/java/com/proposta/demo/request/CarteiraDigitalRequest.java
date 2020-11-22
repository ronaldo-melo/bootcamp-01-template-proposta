package com.proposta.demo.request;

import com.proposta.demo.validator.CarteiraDigitalValida;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraDigitalRequest {

    @NotBlank
    @Email
    private String email;

    @NotNull
    @CarteiraDigitalValida
    private String tipoCarteira;

    public CarteiraDigitalRequest(@NotBlank String email, @NotNull String tipoCarteira) {
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

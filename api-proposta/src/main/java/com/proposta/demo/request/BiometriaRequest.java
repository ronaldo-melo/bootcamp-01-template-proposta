package com.proposta.demo.request;

import com.proposta.demo.model.Biometria;
import com.proposta.demo.validator.UnicoValor;
import com.proposta.demo.validator.ValorBase64;

import javax.validation.constraints.NotBlank;

public class BiometriaRequest {

    @NotBlank
    @UnicoValor(entity = Biometria.class, valorDoAtributo = "fingerPrint")
    @ValorBase64
    private String fingerPrint;

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

}
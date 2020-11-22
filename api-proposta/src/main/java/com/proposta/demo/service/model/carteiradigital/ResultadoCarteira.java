package com.proposta.demo.service.model.carteiradigital;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ResultadoCarteira {

    @NotNull
    private Resultado resultado;

    @NotNull
    private String id;

    public ResultadoCarteira() {

    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

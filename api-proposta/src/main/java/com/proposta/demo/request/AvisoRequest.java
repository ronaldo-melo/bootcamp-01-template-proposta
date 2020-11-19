package com.proposta.demo.request;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class AvisoRequest {

    @NotBlank
    private String destino;

    @Future
    private LocalDate terminoaDaViagem;

    public AvisoRequest(@NotBlank String destino, @Future LocalDate terminoaDaViagem) {
        this.destino = destino;
        this.terminoaDaViagem = terminoaDaViagem;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getTerminoaDaViagem() {
        return terminoaDaViagem;
    }
}

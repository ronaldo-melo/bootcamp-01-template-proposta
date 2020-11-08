package com.proposta.demo.model.cartao;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Embeddable
public class AvisoViagem {

    @NotNull
    private LocalDate validoAte;

    @NotBlank
    private String destino;

    public AvisoViagem(@NotNull LocalDate validoAte, @NotBlank String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

}

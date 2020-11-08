package com.proposta.demo.model;

import org.bouncycastle.pqc.crypto.newhope.NHOtherInfoGenerator;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.OffsetDateTime;
import java.util.Objects;

@Embeddable
public class Vencimento {

    @NotBlank
    private String vencimentoId;

    @NotNull
    @Positive
    private Byte dia;

    @NotNull
    @FutureOrPresent
    private OffsetDateTime dataDeCriacaoVencimento;

    @Deprecated
    public Vencimento(){

    }

    public Vencimento(@NotBlank String vencimentoId, @NotNull @Positive Byte dia,
                      @NotNull @FutureOrPresent OffsetDateTime dataDeCriacaoVencimento) {

        this.vencimentoId = vencimentoId;
        this.dia = dia;
        this.dataDeCriacaoVencimento = dataDeCriacaoVencimento;
    }

}

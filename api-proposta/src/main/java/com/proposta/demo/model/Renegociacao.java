package com.proposta.demo.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

@Embeddable
public class Renegociacao {

    @NotNull
    private String renegociacaoId;

    @NotNull
    @Positive
    private Integer quantidade;

    @Positive
    @NotNull
    private BigDecimal valor;

    @NotNull
    @FutureOrPresent
    private OffsetDateTime dataDeCriacaoRenegociacao;

    @Deprecated
    public Renegociacao(){

    }

    public Renegociacao(@NotNull String renegociacaoId, @NotNull @Positive Integer quantidade, @Positive @NotNull BigDecimal valor,
                        @NotNull @FutureOrPresent OffsetDateTime dataDeCriacaoRenegociacao) {

        this.renegociacaoId = renegociacaoId;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacaoRenegociacao = dataDeCriacaoRenegociacao;
    }

}

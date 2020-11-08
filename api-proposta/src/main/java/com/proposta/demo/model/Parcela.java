package com.proposta.demo.model.cartao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Parcela {

    @NotBlank
    @Id
    private String id;

    @NotBlank
    @Positive
    private Integer quantidade;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Parcela(){

    }

    public Parcela(@NotBlank String id, @NotBlank @Positive Integer quantidade, @NotNull @Positive BigDecimal valor,
                   @NotNull Cartao cartao) {

        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.cartao = cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parcela parcela = (Parcela) o;
        return Objects.equals(id, parcela.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

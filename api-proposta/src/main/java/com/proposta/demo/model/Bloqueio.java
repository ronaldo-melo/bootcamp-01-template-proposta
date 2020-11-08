package com.proposta.demo.model.cartao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.awt.print.Book;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
public class Bloqueio {

    @NotEmpty
    @Id
    private String id;

    @NotNull
    private OffsetDateTime bloqueadoEm;

    @NotBlank
    private String sistemaResponsavel;

    @NotNull
    private Boolean ativo;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Bloqueio(){

    }


    public Bloqueio(@NotNull OffsetDateTime bloqueadoEm, @NotBlank String sistemaResponsavel,
                    @NotNull Cartao cartao, @NotNull Boolean ativo) {

        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.cartao = cartao;
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bloqueio bloqueio = (Bloqueio) o;
        return Objects.equals(id, bloqueio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

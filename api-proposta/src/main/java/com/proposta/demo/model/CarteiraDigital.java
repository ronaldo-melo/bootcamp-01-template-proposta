package com.proposta.demo.model.cartao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
public class CarteiraDigital {

    @NotBlank
    @Id
    private String id;

    @NotBlank
    private String email;

    @NotNull
    @FutureOrPresent
    private OffsetDateTime associadoEm;

    @NotBlank
    private String emissor;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public CarteiraDigital() {
    }

    public CarteiraDigital(@NotBlank String id, @NotBlank String email, @NotNull @FutureOrPresent OffsetDateTime associadoEm,
                           @NotBlank String emissor, @NotNull Cartao cartao) {

        this.id = id;
        this.email = email;
        this.associadoEm = associadoEm;
        this.emissor = emissor;
        this.cartao = cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarteiraDigital carteiraDigital = (CarteiraDigital) o;
        return Objects.equals(id, carteiraDigital.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

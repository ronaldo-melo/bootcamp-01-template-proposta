package com.proposta.demo.model;

import com.proposta.demo.model.enums.TipoCarteira;
import com.proposta.demo.response.CarteiraDigitalResponse;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class CarteiraDigital {

    @Id
    @Column(unique = true)
    private UUID id;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private LocalDateTime associadoEm;

    @NotNull
    @Enumerated(value = EnumType.ORDINAL)
    private TipoCarteira tipoCarteira;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public CarteiraDigital() {
    }

    public CarteiraDigital(@NotBlank UUID id, @NotBlank String email, @NotNull LocalDateTime associadoEm,
                           @NotBlank TipoCarteira tipoCarteira, @NotNull Cartao cartao) {

        this.id = id;
        this.email = email;
        this.associadoEm = associadoEm;
        this.tipoCarteira = tipoCarteira;
        this.cartao = cartao;
    }

    public UUID getId() {
        return id;
    }

    public TipoCarteira getTipoCarteira() {
        return tipoCarteira;
    }

    public String getEmail() {
        return email;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public CarteiraDigitalResponse toResponse(){
        return new CarteiraDigitalResponse(this);
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

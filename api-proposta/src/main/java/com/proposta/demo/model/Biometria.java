package com.proposta.demo.model;

import org.springframework.util.Base64Utils;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime horaDaCriacao;

    @NotBlank
    @Column(unique = true)
    private String fingerPrint;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Biometria() {

    }

    public Biometria(@NotBlank String fingerPrint, @NotNull Cartao cartao) {
        this.horaDaCriacao = LocalDateTime.now();
        this.fingerPrint = Base64Utils.encodeToString(fingerPrint.getBytes());
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public Cartao getCartao() {
        return cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biometria biometria = (Biometria) o;
        return Objects.equals(id, biometria.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

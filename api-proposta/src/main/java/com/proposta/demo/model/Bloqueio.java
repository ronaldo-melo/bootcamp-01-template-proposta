package com.proposta.demo.model;

import com.proposta.demo.response.BloqueioResponse;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
public class Bloqueio {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    private LocalDateTime bloqueadoEm;

    @NotBlank
    private String userAgent; //userAgent

    @NotBlank
    private String ipDaRequisicao;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Bloqueio(){

    }

    public Bloqueio(@NotBlank String userAgent,
                    @NotBlank String ipDaRequisicao, @NotNull Cartao cartao) {

        this.bloqueadoEm = LocalDateTime.now();
        this.userAgent = userAgent;
        this.cartao = cartao;
        this.ipDaRequisicao = ipDaRequisicao;
    }

    public Long getId() {
        return id;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getIpDaRequisicao() {
        return ipDaRequisicao;
    }

    public BloqueioResponse toResponse(){
        return new BloqueioResponse(this);
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

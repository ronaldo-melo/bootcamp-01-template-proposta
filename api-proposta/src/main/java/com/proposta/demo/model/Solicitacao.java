package com.proposta.demo.model;

import com.proposta.demo.response.SolicitacaoResponse;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @FutureOrPresent
    private LocalDateTime instanteDaSolicitacao;

    @NotBlank
    private String ipDoCliente;

    @NotBlank
    private String userAgent;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Solicitacao(){

    }

    public Solicitacao(@NotBlank String ipDoCliente, @NotBlank String userAgent, @NotNull Cartao cartao) {
        this.ipDoCliente = ipDoCliente;
        this.userAgent = userAgent;
        this.cartao = cartao;

        this.instanteDaSolicitacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getInstanteDaSolicitacao() {
        return instanteDaSolicitacao;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getIpDoCliente() {
        return ipDoCliente;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public SolicitacaoResponse toResponse(){
        return new SolicitacaoResponse(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solicitacao that = (Solicitacao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

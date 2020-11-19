package com.proposta.demo.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class AvisoViagem {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotBlank
    private String destino;

    @Future
    private LocalDate terminoaDaViagem;

    @NotNull
    private LocalDateTime instateDoAviso;

    @NotEmpty
    private String ipDoCliente;

    @NotEmpty
    private String userAgent;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public AvisoViagem(){

    }

    public AvisoViagem(@NotBlank String destino, @Future LocalDate terminoaDaViagem,
                       @NotEmpty String ipDoCliente, @NotEmpty String userAgent,
                       @NotNull Cartao cartao) {

        this.destino = destino;
        this.terminoaDaViagem = terminoaDaViagem;
        this.ipDoCliente = ipDoCliente;
        this.userAgent = userAgent;
        this.cartao = cartao;

        this.instateDoAviso = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getTerminoaDaViagem() {
        return terminoaDaViagem;
    }

    public LocalDateTime getInstateDoAviso() {
        return instateDoAviso;
    }

    public Cartao getCartao() {
        return cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvisoViagem that = (AvisoViagem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

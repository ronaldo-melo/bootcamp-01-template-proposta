package com.proposta.demo.model;

import com.proposta.demo.response.CartaoResponse;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Cartao {

    @Id
    @NotBlank
    @NotNull
    private UUID id;

    @NotNull
    @FutureOrPresent
    private LocalDateTime emitidoEm;

    @NotBlank
    private String titular;

    @OneToMany(mappedBy = "cartao", fetch = FetchType.LAZY)
    List<Bloqueio> bloqueios;

    @OneToMany(mappedBy = "cartao", fetch = FetchType.LAZY)
    private List<AvisoViagem> avisoViagems;

    @OneToMany(mappedBy = "cartao", fetch = FetchType.LAZY)
    private List<CarteiraDigital> carteiras;

    @OneToMany(mappedBy = "cartao", fetch = FetchType.LAZY)
    private List<Parcela> parcelas;

    @PositiveOrZero
    private BigDecimal limite;

    @Embedded
    private Renegociacao renegociacao;

    @Embedded
    private Vencimento vencimento;

    @NotNull
    private Long idProposta;

    @Deprecated
    public Cartao (){

    }

    public Cartao(@NotNull @NotBlank UUID id, @NotNull @FutureOrPresent LocalDateTime emitidoEm, @NotBlank String titular,
                  @NotBlank Long idProposta) {

        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.idProposta = idProposta;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public UUID getIdCartao() {
        return id;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    @Transient
    public CartaoResponse toResponse(){
        return new CartaoResponse(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cartao cartao = (Cartao) o;
        return Objects.equals(id, cartao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

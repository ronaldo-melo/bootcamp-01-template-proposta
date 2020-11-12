package com.proposta.demo.model;

import com.proposta.demo.response.CartaoResponse;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
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
    @NotNull
    private UUID id;

    @NotNull
    private LocalDateTime emitidoEm;

    @OneToMany(mappedBy = "cartao", fetch = FetchType.LAZY)
    List<Bloqueio> bloqueios; //CDD 1

    @OneToMany(mappedBy = "cartao", fetch = FetchType.LAZY)
    private List<AvisoViagem> avisoViagems; //CDD 2

    @OneToMany(mappedBy = "cartao", fetch = FetchType.LAZY)
    private List<CarteiraDigital> carteiras; //CDD 3

    @OneToMany(mappedBy = "cartao", fetch = FetchType.LAZY)
    private List<Parcela> parcelas; //CDD 4

    @PositiveOrZero
    private BigDecimal limite;

    @Embedded
    private Renegociacao renegociacao; //CDD 5

    @Embedded
    private Vencimento vencimento; //CDD 6

    @NotNull
    @OneToOne
    private Proposta proposta; //CDD 7

    @Deprecated
    public Cartao (){

    }

    public Cartao(@NotNull UUID id, @NotNull @FutureOrPresent LocalDateTime emitidoEm
                  , Proposta proposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.proposta = proposta;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public UUID getIdCartao() {
        return id;
    }

    public Long getIdProposta() {
        return proposta.getId();
    }

    public String getTitular() {
        return proposta.getNome();
    }

    public CartaoResponse toResponse(){
        return new CartaoResponse(this);
    }

    public Proposta getProposta() {
        return proposta;
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
//PONTOS CDD: 7
package com.proposta.demo.model;

import com.proposta.demo.model.enums.EstadoBloqueio;
import com.proposta.demo.response.CartaoResponse;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    List<Bloqueio> bloqueios = new ArrayList<>(); //CDD 1

    @OneToMany(mappedBy = "cartao", fetch = FetchType.LAZY)
    private List<AvisoViagem> avisoViagems; //CDD 2

    @OneToMany(mappedBy = "cartao", fetch = FetchType.LAZY)
    private List<CarteiraDigital> carteiras; //CDD 3

    @OneToMany(mappedBy = "cartao", fetch = FetchType.LAZY)
    private List<Parcela> parcelas; //CDD 4

    @OneToMany(mappedBy = "cartao", fetch = FetchType.LAZY)
    private List<Biometria> biometrias = new ArrayList<>(); //CDD 5

    @PositiveOrZero
    private BigDecimal limite;

    @Embedded
    private Renegociacao renegociacao; //CDD 6

    @Embedded
    private Vencimento vencimento; //CDD 7

    @NotNull
    @OneToOne
    private Proposta proposta; //CDD 8

    @NotNull
    @Enumerated(value = EnumType.ORDINAL)
    private EstadoBloqueio estadoBloqueio;

    @Deprecated
    public Cartao (){

    }

    public Cartao(@NotNull UUID id, @NotNull @FutureOrPresent LocalDateTime emitidoEm
                  , Proposta proposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.proposta = proposta;

        this.estadoBloqueio = EstadoBloqueio.FALHA;
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

    public List<Biometria> getBiometrias() {
        return biometrias;
    }

    public void adicionarBiometria(@NotEmpty Biometria biometria){
        biometrias.add(biometria);
    }

    public void adicionarBloqueio(Bloqueio bloqueio){
        bloqueios.add(bloqueio);
    }

    public void bloquearCartao(){
        this.estadoBloqueio = EstadoBloqueio.BLOQUEADO;
    }

    public List<CarteiraDigital> getCarteiras() {
        return carteiras;
    }

    public void adicionarCarteira(CarteiraDigital carteiraDigital) {
        this.carteiras.add(carteiraDigital);
    }

    public EstadoBloqueio estadoAtualBloqueio() {
        return estadoBloqueio;
    }

    public void adicinarAvisoViagem(AvisoViagem novoAvisoViagem){
        avisoViagems.add(novoAvisoViagem);
    }

    public List<AvisoViagem> getAvisoViagems() {
        return avisoViagems;
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
//PONTOS CDD: 8
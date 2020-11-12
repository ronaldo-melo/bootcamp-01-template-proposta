package com.proposta.demo.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.proposta.demo.model.Cartao;
import com.proposta.demo.model.Proposta;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public class CartaoRequestApiLegado {

    @NotNull
    private UUID id;

    @NotNull
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime emitidoEm;

    @NotBlank
    @NotNull
    private String titular;

    @NotBlank
    @NotNull
    private Long idProposta;

    public CartaoRequestApiLegado() {

    }

    public void setId(@NotBlank UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public void setEmitidoEm(@NotNull @JsonDeserialize(using = LocalDateTimeDeserializer.class) LocalDateTime emitidoEm) {
        this.emitidoEm = emitidoEm;
    }

    public Cartao toModel(EntityManager manager){

        Proposta proposta = manager.find(Proposta.class, getIdProposta());

        return new Cartao(getId(), getEmitidoEm(), proposta);
    }

}

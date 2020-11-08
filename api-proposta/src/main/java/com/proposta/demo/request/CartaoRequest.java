package com.proposta.demo.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.proposta.demo.model.Cartao;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.UUID;

public class CartaoRequest {

    //@UnicoValor
    @NotBlank
    @NotNull
    private String id;

    @NotNull
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime emitidoEm;

    @NotBlank
    @NotNull
    private String titular;

    @NotBlank
    @NotNull
    private Long idProposta;

    public CartaoRequest() {

    }

    public void setId(@NotBlank String id) {
        this.id = id;
    }

    public String getId() {
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

    public void setTitular(@NotBlank String titular) {
        this.titular = titular;
    }

    public void setIdProposta(@NotNull @Positive Long idProposta) {
        this.idProposta = idProposta;
    }

    public Cartao toModel(){
        return new Cartao(UUID.fromString(this.id), this.emitidoEm, this.titular, this.idProposta);
    }
}

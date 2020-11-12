package com.proposta.demo.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

public class CartaoRequest {

    @NotBlank
    @NotNull
    private String id;

    @NotNull
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime emitidoEm;

    @NotBlank
    @NotNull
    private String titular;

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

    public void setEmitidoEm(@NotNull @JsonDeserialize(using = LocalDateTimeDeserializer.class) LocalDateTime emitidoEm) {
        this.emitidoEm = emitidoEm;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(Long idProposta) {
        this.idProposta = idProposta;
    }
}

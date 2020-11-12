package com.proposta.demo.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.proposta.demo.model.Cartao;
import com.proposta.demo.model.Proposta;
import com.proposta.demo.service.FindEntityDependency;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.UUID;

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

    @NotBlank
    @NotNull
    private Proposta proposta;

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

    public Proposta getProposta() {
        return proposta;
    }

    public void setEmitidoEm(@NotNull @JsonDeserialize(using = LocalDateTimeDeserializer.class) LocalDateTime emitidoEm) {
        this.emitidoEm = emitidoEm;
    }

    public Cartao toModel(){
        return new Cartao(UUID.fromString(this.id), this.emitidoEm, this.titular,
                proposta);
    }
}

package com.proposta.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class AvisoViagem {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotNull
    private LocalDate validoAte;

    @NotBlank
    private String destino;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    public AvisoViagem(@NotNull Cartao cartao, @NotNull LocalDate validoAte, @NotBlank String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

}

package com.proposta.demo.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "proposta")
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    private String cpfOuCnp;

    @Email
    @NotBlank
    @NotNull
    private String email;

    @NotBlank
    @NotNull
    private String endereco;

    @Positive
    private BigDecimal salario;

    @Deprecated
    public Proposta() {

    }

    public Proposta(@NotBlank String cpfOuCnp, @Email @NotBlank String email, @NotBlank String endereco,
                    @Positive BigDecimal salario) {
        this.cpfOuCnp = cpfOuCnp;
        this.email = email;
        this.endereco = endereco;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proposta proposta = (Proposta) o;
        return Objects.equals(id, proposta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

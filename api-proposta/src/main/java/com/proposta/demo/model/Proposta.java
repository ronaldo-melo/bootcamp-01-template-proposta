package com.proposta.demo.model;

import com.proposta.demo.model.enums.StatusAvaliacaoProposta;
import com.proposta.demo.response.PropostaResponse;
import com.proposta.demo.validator.DocumentoFormatoValido;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    @NotNull
    @DocumentoFormatoValido
    private String documento;

    @NotBlank
    @NotNull
    private String nome;

    @Email
    @NotBlank
    @NotNull
    private String email;

    @NotBlank
    @NotNull
    private String endereco;

    @Positive
    private BigDecimal salario;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "estado_proposta")
    private StatusAvaliacaoProposta estadoProposta;

    @OneToOne(mappedBy = "proposta")
    private Cartao cartao;

    @Deprecated
    public Proposta() {

    }

    public Proposta(@NotBlank @DocumentoFormatoValido String documento, @NotBlank @NotNull String nome,
                    @Email @NotBlank String email, @NotBlank String endereco,
                    @Positive BigDecimal salario) {

        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.estadoProposta = StatusAvaliacaoProposta.NAO_ELEGIVEL;
    }

    public Proposta(@NotBlank @DocumentoFormatoValido String documento, @NotBlank @NotNull String nome,
                    @Email @NotBlank String email, @NotBlank String endereco,
                    @Positive BigDecimal salario, Cartao cartao) {

        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.estadoProposta = StatusAvaliacaoProposta.NAO_ELEGIVEL;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void vinculaCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public StatusAvaliacaoProposta getEstadoProposta() {
        return estadoProposta;
    }

    public PropostaResponse toResponse(){
        return new PropostaResponse(this);
    }

    public void atualizaStatus(StatusAvaliacaoProposta statusAvaliacaoProposta){
        Assert.isTrue(this.estadoProposta.equals(StatusAvaliacaoProposta.NAO_ELEGIVEL), "uma vez que a proposta é elegível não pode mais trocar");
        this.estadoProposta = statusAvaliacaoProposta;
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

    public SolicitacaoAnalise obterDadosParaAnalise(){
        return new SolicitacaoAnalise(this);
    }
}
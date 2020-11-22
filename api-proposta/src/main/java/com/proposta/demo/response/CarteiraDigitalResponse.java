package com.proposta.demo.response;

import com.proposta.demo.model.Cartao;
import com.proposta.demo.model.CarteiraDigital;
import com.proposta.demo.model.enums.TipoCarteira;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public class CarteiraDigitalResponse {

    private String email;

    private TipoCarteira tipoCarteira;

    private CartaoResponse cartaoResponse;

    public CarteiraDigitalResponse(CarteiraDigital carteiraDigital){
        this.email = carteiraDigital.getEmail();
        this.tipoCarteira = carteiraDigital.getTipoCarteira();
        this.cartaoResponse = new CartaoResponse(carteiraDigital.getCartao());
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getTipoCarteira() {
        return tipoCarteira;
    }

    public CartaoResponse getCartaoAssociado() {
        return cartaoResponse;
    }
}

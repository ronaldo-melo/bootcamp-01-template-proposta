package com.proposta.demo.service;

import com.proposta.demo.model.Cartao;
import com.proposta.demo.model.enums.TipoCarteira;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class ConsultaCarteiraNoBanco {

    public void consultar(Cartao cartao, TipoCarteira tipoCarteira){
        if(cartao.getCarteiras().stream().anyMatch(c -> c.getTipoCarteira().equals(tipoCarteira))){
            String mensagemDeErro = "Não pode adicionar vincular esta carteira ao cartão, pois o cartão já foi vinculado anteriormente!";
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, mensagemDeErro);
        }
    }

}

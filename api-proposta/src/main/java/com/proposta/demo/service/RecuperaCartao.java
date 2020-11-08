package com.proposta.demo.service;

import com.proposta.demo.infrastructure.CartaoIntegracao;
import com.proposta.demo.model.cartao.Cartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class RecuperarCartao {

    @Autowired
    private CartaoIntegracao cartaoIntegracao;

    public Cartao recuperarPorIdProposta(Long id){
        return cartaoIntegracao.getCartao(id).getBody();
    }

}

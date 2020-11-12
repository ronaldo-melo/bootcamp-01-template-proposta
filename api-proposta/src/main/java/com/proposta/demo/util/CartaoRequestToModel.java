package com.proposta.demo.util;

import com.proposta.demo.model.Cartao;
import com.proposta.demo.model.Proposta;
import com.proposta.demo.request.CartaoRequest;
import com.proposta.demo.service.FindEntityDependency;

import javax.persistence.EntityManager;
import java.util.UUID;

public class CartaoRequestToModel {
                  //CDD 1           //CDD 2
    public static Cartao toModel(CartaoRequest cartaoRequest, EntityManager manager){

                   //CDD 3
        return new Cartao(UUID.fromString(cartaoRequest.getId()), cartaoRequest.getEmitidoEm(),
                                                                //CDD 4
                FindEntityDependency.findEntityDepedenceDyId(Proposta.class, cartaoRequest.getIdProposta(), manager));
    };
}
//PONTOS CDD: 4
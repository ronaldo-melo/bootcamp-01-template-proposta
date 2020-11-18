package com.proposta.demo.service;

import com.proposta.demo.model.Cartao;
import com.proposta.demo.model.enums.EstadoBloqueio;
import com.proposta.demo.service.model.bloqueio.ResultadoBloqueio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class ValidaBloqueioNoCartao {

    @Autowired
    private BloqueioCartaoResultado bloqueioCartaoResultado; //CCD: 1

    @Autowired
    private SalvaNovoBloqueio salvaNovoBloqueio; ///CCD: 2

    @Transactional
    public Long valida(HttpServletRequest request, UUID id, EntityManager manager){

        ResponseEntity<?> resultado = bloqueioCartaoResultado.tentaBloqueio(id);

        if(resultado.getStatusCode().is2xxSuccessful()){  //CCD: 3

            //CCD: 4       //CCD: 5
            Cartao cartao = FindEntity.findEntityById(Cartao.class, id, manager);
            cartao.bloquearCartao();
            manager.merge(cartao);
        }

        Long idNovoBloqueio = salvaNovoBloqueio.salvar(request, id, manager);
        return idNovoBloqueio;
    }
}
//PONTOS CCD: 6
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

    @Autowired
    private ServiceCartoesFactoty factoty;

    @Autowired
    private FindEntity findEntity;

    @Autowired
    private EntityManager manager;

    @Transactional
    public Long validar(HttpServletRequest request, UUID id){

        ResponseEntity<?> resultado = bloqueioCartaoResultado.tentaBloqueio(id);

        if(resultado.getStatusCode().is2xxSuccessful()){  //CCD: 3

            //CCD: 4       //CCD: 5
            Cartao cartao = findEntity.findById(Cartao.class, id);
            cartao.bloquearCartao();
            manager.merge(cartao);
        }

        Long idNovoBloqueio = salvaNovoBloqueio.salvar(request, id);
        return idNovoBloqueio;
    }
}
//PONTOS CCD: 6
package com.proposta.demo.service;

import com.proposta.demo.model.Bloqueio;
import com.proposta.demo.model.Cartao;
import com.proposta.demo.response.BloqueioResponse;
import org.mockito.internal.matchers.Find;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.UUID;

@Service
public class BuscarBloqueio {

              //CDD 1
    public BloqueioResponse buscar(UUID idCartao, Long idBloqueio, EntityManager manager){

        //CDD 2
        Cartao cartao = FindEntity.findEntityById(Cartao.class, idCartao, manager);

        //CDD 3
        Bloqueio bloqueio = FindEntity.findEntityById(Bloqueio.class, idBloqueio, manager);

        return bloqueio.toResponse();
    }

}

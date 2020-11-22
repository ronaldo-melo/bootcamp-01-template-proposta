package com.proposta.demo.service;

import com.proposta.demo.model.Bloqueio;
import com.proposta.demo.model.Cartao;
import com.proposta.demo.response.BloqueioResponse;
import org.mockito.internal.matchers.Find;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.UUID;

@Service
public class BuscaBloqueio {

    @Autowired
    private FindEntity findEntity;
              //CDD 1
    public ResponseEntity<?> buscar(UUID idCartao, Long idBloqueio){

        //CDD 2
        Cartao cartao = findEntity.findById(Cartao.class, idCartao);

        //CDD 3
        Bloqueio bloqueio = findEntity.findById(Bloqueio.class, idBloqueio);

        return ResponseEntity.ok(bloqueio.toResponse());
    }

}

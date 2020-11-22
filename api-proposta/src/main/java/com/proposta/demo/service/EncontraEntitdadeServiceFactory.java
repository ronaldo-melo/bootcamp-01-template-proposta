package com.proposta.demo.service;

import com.proposta.demo.model.Cartao;
import com.proposta.demo.model.CarteiraDigital;
import com.proposta.demo.model.Solicitacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import java.util.UUID;

@Service
public class EncontraEntitdadeServiceFactory {

    @Autowired
    private RecuperaCartao recuperaCartao; //CDD 1

    @Autowired
    private BuscaBloqueio buscaBloqueio; //CDD 2

    @Autowired
    private FindEntity findEntity; //CDD 3

    public ResponseEntity<?> encontrarNoLegadoCartaoPorIdProposta(Long idProposta){
        return recuperaCartao.recuperarPorIdProposta(idProposta);
    }

    public ResponseEntity<?> encontrarNoLegadoCartaoPorId(UUID idCartao){
        return recuperaCartao.recuperarPorId(idCartao);
    }

    public ResponseEntity<?> encontrarBloqueioPorId(UUID idCartao, Long idBloqueio, EntityManager manager){
        return buscaBloqueio.buscar(idCartao, idBloqueio);
    }

    public ResponseEntity<?> encontrarCartaoPorId(UUID idCartao){
        Cartao cartao = findEntity.findById(Cartao.class, idCartao);
        return ResponseEntity.ok(cartao.toResponse());
    }

    public ResponseEntity<?> encontrarCarteiraPorId(UUID idCarteira){
        CarteiraDigital carteiraDigital = findEntity.findById(CarteiraDigital.class, idCarteira);
        return ResponseEntity.ok(carteiraDigital.toResponse());
    }

    public ResponseEntity<?> encontrarSolicitacaoPorId(Long idSolicitacao){
        Solicitacao solicitacao = findEntity.findById(Solicitacao.class, idSolicitacao);
        return ResponseEntity.ok(solicitacao.toResponse());
    }

}

package com.proposta.demo.controller;

import com.proposta.demo.response.PropostaResponse;
import com.proposta.demo.service.AssociaCartaoIDcomProposta;
import com.proposta.demo.service.AvaliaProposta;
import com.proposta.demo.Repository.ExecutorTransacao;
import com.proposta.demo.model.Proposta;
import com.proposta.demo.request.PropostaRequest;
import com.proposta.demo.service.FindEntity;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;

@RequestMapping("/propostas")
@RestController
public class PropostaController {

    @PersistenceContext
    private EntityManager manager;

                    //1
    private AvaliaProposta avaliaProposta;


    public PropostaController(EntityManager manager, ExecutorTransacao executorTransacao,
                              AvaliaProposta avaliaProposta, FindEntity findEntity) {
        super();
        this.manager = manager;
        this.avaliaProposta = avaliaProposta;
    }

    @PostMapping
    @Transactional                                                   //2
    public ResponseEntity<?> salvarProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder){

        //3
        Proposta proposta = request.toModel();
        manager.persist(proposta);
        proposta.atualizaStatus(avaliaProposta.getStatusAvaliacao(proposta.obterDadosParaAnalise()));
        manager.merge(proposta);

        URI uri = builder.path("/{id}").build(proposta.getId());

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProposta(@PathVariable Long id){
                                    //4
        return ResponseEntity.ok(FindEntity.findEntityById(Proposta.class, id, this.manager).toResponse());
    }

}
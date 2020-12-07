package com.proposta.demo.controller;

import com.proposta.demo.service.AvaliaProposta;
import com.proposta.demo.Repository.ExecutorTransacao;
import com.proposta.demo.model.Proposta;
import com.proposta.demo.request.PropostaRequest;
import com.proposta.demo.service.EncodeDocumento;
import com.proposta.demo.service.FindEntity;
import org.springframework.beans.factory.annotation.Autowired;
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

    private FindEntity findEntity;

    private EncodeDocumento encodeDocumento;

    public PropostaController(EntityManager manager, ExecutorTransacao executorTransacao,
                              AvaliaProposta avaliaProposta, FindEntity findEntity, EncodeDocumento encodeDocumento) {
        super();
        this.manager = manager;
        this.avaliaProposta = avaliaProposta;
        this.findEntity = findEntity;
        this.encodeDocumento = encodeDocumento;
    }

    @PostMapping
    @Transactional                                                   //2
    public ResponseEntity<?> salvarProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder){

        //3
        Proposta proposta = request.toModel(encodeDocumento);
        manager.persist(proposta);
        proposta.atualizaStatus(avaliaProposta.getStatusAvaliacao(proposta.obterDadosParaAnalise()));
        manager.merge(proposta);

        URI uri = builder.path("/{id}").build(proposta.getId());

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProposta(@PathVariable Long id){
                                    //4
        return ResponseEntity.ok(findEntity.findById(Proposta.class, id).toResponse());
    }

}
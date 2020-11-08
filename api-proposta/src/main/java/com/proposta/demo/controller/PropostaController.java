package com.proposta.demo.controller;

import com.proposta.demo.response.PropostaResponse;
import com.proposta.demo.service.AvaliaProposta;
import com.proposta.demo.Repository.ExecutorTransacao;
import com.proposta.demo.model.Proposta;
import com.proposta.demo.request.PropostaRequest;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.net.URI;

@RequestMapping("/propostas")
@RestController
public class PropostaController {

    private EntityManager manager;

    //1
    private ExecutorTransacao executorTransacao;

    //2
    private AvaliaProposta avaliaProposta;

    public PropostaController(EntityManager manager, ExecutorTransacao executorTransacao,
                              AvaliaProposta avaliaProposta) {

        super();
        this.manager = manager;
        this.executorTransacao = executorTransacao;
        this.avaliaProposta = avaliaProposta;
    }

    @PostMapping
    @Transactional                                                   //3
    public ResponseEntity<?> salvarProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder){

        //4
        Proposta proposta = request.toModel();

        executorTransacao.salvaEcomita(proposta);

        proposta.atualizaStatus(avaliaProposta.getStatusAvaliacao(proposta.obterDadosParaAnalise()));

        executorTransacao.atualizaEComita(proposta);

        URI uri = builder.path("/{id}").build(proposta.getId());

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")             //4
    public ResponseEntity<PropostaResponse> getProposta(@PathVariable Long id){

        PropostaResponse response = new PropostaResponse(manager.find(Proposta.class, id));

        return ResponseEntity.ok(response);

    }

}

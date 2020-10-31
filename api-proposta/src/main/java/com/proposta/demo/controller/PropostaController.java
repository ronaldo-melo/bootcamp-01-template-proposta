package com.proposta.demo.controller;

import com.proposta.demo.request.PropostaRequest;
import org.springframework.http.ResponseEntity;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.net.URI;

@RequestMapping("/propostas")
@RestController
public class PropostaController {

    private EntityManager manager;

    public PropostaController(EntityManager manager) {
        super();
        this.manager = manager;
    }

    @PostMapping
    @Transactional                                                   //1
    public ResponseEntity<?> salvarProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder){
        Long id = manager.merge(request.toModel()).getId();
        URI uri = builder.path("/{id}").build(id);
        return ResponseEntity.created(uri).build();
    }

}

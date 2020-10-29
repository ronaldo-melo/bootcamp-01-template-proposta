package com.proposta.demo;

import com.sun.net.httpserver.Headers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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


    public ResponseEntity<?> salvarProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder, Headers headers){
        Long id = manager.merge(request.toModel).getId();

        URI uri = builder.path("/{id}").build(id);

        //ResponseEntity(@Nullable T body, @Nullable MultiValueMap<String, String> headers, HttpStatus status)

        headers.set("location", id.toString());

        return new ResponseEntity<?>()
    }

}

package com.proposta.demo.controller;

import com.proposta.demo.request.BiometriaRequest;
import com.proposta.demo.service.AdicionaBiometraEmCartao;
import com.proposta.demo.service.RecuperaCartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private RecuperaCartao recuperarCartao; //CDD 1

    @Autowired
    private AdicionaBiometraEmCartao adicionaBiometraEmCartao; //CDD 2

    @GetMapping
    public ResponseEntity<?> recuperarCartaoPorIdProposta(@RequestParam Long idProposta){
        return recuperarCartao.getCartaoPorIdProposta(idProposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> recuperarCartaoPorId(@PathVariable UUID id){
        return recuperarCartao.getCartaoPorId(id);
    }

    @PostMapping("/{id}")                                                                   //CDD 3
    private ResponseEntity<?> salvarBiometria(@PathVariable UUID id, @RequestBody @Valid BiometriaRequest request, UriComponentsBuilder builder){
        Long idRecurso = adicionaBiometraEmCartao.adionarBiometria(id, request);
        URI uri = builder.path("/" + id.toString() + "/{idRecurso}").build(idRecurso);

        return ResponseEntity.created(uri).build();
    }

}
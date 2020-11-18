package com.proposta.demo.controller;

import com.proposta.demo.model.Bloqueio;
import com.proposta.demo.request.BiometriaRequest;
import com.proposta.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private RecuperaCartao recuperarCartao; //CDD 1

    @Autowired
    private AdicionaBiometraEmCartao adicionaBiometraEmCartao; //CDD 2

    @Autowired
    private ValidaBloqueioNoCartao validaBloqueioNoCartao; //CDD 3

    @Autowired
    private BuscarBloqueio buscarBloqueio;  //CDD 4

    @PersistenceContext
    private EntityManager manager;

    @GetMapping
    public ResponseEntity<?> recuperarCartaoPorIdProposta(@RequestParam Long idProposta){
        return recuperarCartao.getCartaoPorIdProposta(idProposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> recuperarCartaoPorId(@PathVariable UUID id){
        return recuperarCartao.getCartaoPorId(id);
    }

    @PostMapping("/{id}")                                                                   //CDD 5
    private ResponseEntity<?> salvarBiometria(@PathVariable UUID id, @RequestBody @Valid BiometriaRequest request, UriComponentsBuilder builder){
        Long idRecurso = adicionaBiometraEmCartao.adionarBiometria(id, request);
        URI uri = builder.path("/" + id.toString() + "/{idRecurso}").build(idRecurso);

        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/bloqueios/{id}")
    public ResponseEntity<?> bloquearCartao(@PathVariable UUID id, UriComponentsBuilder builder, HttpServletRequest request){

        Long resourceAdress = validaBloqueioNoCartao.valida(request, id, manager);
        URI uri = builder.path("/bloqueios/".concat(id.toString()) + "/{id}").build(resourceAdress);

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/bloqueios/{idCartao}/{idBloqueio}")
    public ResponseEntity<?> getBloqueioPorId(@PathVariable UUID idCartao, @PathVariable Long idBloqueio){
        return ResponseEntity.ok(buscarBloqueio.buscar(idCartao, idBloqueio, manager));
    }

}//PONTOS CDD:  5
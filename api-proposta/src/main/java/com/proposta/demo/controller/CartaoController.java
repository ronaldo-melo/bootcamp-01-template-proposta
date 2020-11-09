package com.proposta.demo.controller;

import com.proposta.demo.service.RecuperaCartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private RecuperaCartao recuperarCartao;

    @GetMapping
    public ResponseEntity<?> recuperarCartaoPorIdProposta(@RequestParam Long idProposta){
        return recuperarCartao.getCartao(idProposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> recuperarCartaoPorId(@PathVariable @NotBlank String id){
        return recuperarCartao.getCartaoPorId(id);
    }

}

package com.proposta.demo.controller;

import com.proposta.demo.request.AvisoRequest;
import com.proposta.demo.request.BiometriaRequest;
import com.proposta.demo.request.CarteiraDigitalRequest;
import com.proposta.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private ServiceCartoesFactoty services; //CCD 1

    @GetMapping
    public ResponseEntity<?> recuperarCartaoPorIdProposta(@RequestParam Long idProposta){
        return services.recuperarCartaoPorIdProposta(idProposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> recuperarCartaoPorId(@PathVariable UUID id){
        return services.recuperarCartaoPorId(id);
    }

    @PostMapping("/{id}")                                                                    //CCD 2
    private ResponseEntity<?> salvarBiometria(@PathVariable UUID id, @RequestBody @Valid BiometriaRequest request, UriComponentsBuilder builder){
        Long idRecurso = services.adicionarBiometraEmCartao(id, request);
        URI uri = builder.path("/cartoes" + id.toString() + "/{idRecurso}").build(idRecurso);

        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/bloqueios/{id}")
    public ResponseEntity<?> bloquearCartao(@PathVariable UUID id, UriComponentsBuilder builder, HttpServletRequest request){

        Long resourceAdress = services.validarBloqueioNoCartao(request, id);
        URI uri = builder.path("cartoes/bloqueios/".concat(id.toString()) + "/{id}").build(resourceAdress);

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{idCartao}/bloqueios/{idBloqueio}")
    public ResponseEntity<?> getBloqueioPorId(@PathVariable UUID idCartao, @PathVariable Long idBloqueio){
        return ResponseEntity.ok(services.buscarBloqueio(idCartao, idBloqueio));
    }

    @PostMapping("/{idCartao}/solicitacoes")
    public ResponseEntity<?> solicitarRecuperacaoDeSenha(@PathVariable UUID idCartao, HttpServletRequest httpServletRequest, UriComponentsBuilder builder){
        Long idRecurso = services.salvarSolicitacao(idCartao, httpServletRequest);
        URI uri = builder.path("cartoes/recuperacao-senha/" + idCartao + "/{id}").build(idCartao);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{idCartao}/solicitacoes/{idSolicitacao}")
    public ResponseEntity<?> getRecuperacao(@PathVariable UUID idCartao, @PathVariable Long idSolicitacao){
        services.encontrarCartaoPorId(idCartao);
        //CDD 3
        ResponseEntity<?> solicitacao = services.encontrarSolicitacaoPorId(idSolicitacao);
        return ResponseEntity.ok(solicitacao);
    }

    @PostMapping("/aviso-viagem/{idCartao}")                                                                                              //CCD 3
    public ResponseEntity<?> salvarAvisoViagem(@PathVariable UUID idCartao, HttpServletRequest httpServletRequest, @Valid @RequestBody AvisoRequest avisoRequest, UriComponentsBuilder builder){
        Long id = services.salvarAviso(idCartao, httpServletRequest, avisoRequest);
        URI uri = builder.path("cartoes/aviso-viagem/" + idCartao.toString() + "/{id}").build(id);
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/{idCartao}/carteiras")                                                           //CCD 4
    public ResponseEntity<?> salvarCarteira(@PathVariable UUID idCartao, @Valid @RequestBody CarteiraDigitalRequest carteiraDigitalRequest, UriComponentsBuilder builder){
        UUID idNovoRecurso = services.salvarCarteiraDigital(idCartao, carteiraDigitalRequest);
        URI uri = builder.path("cartoes/" + idCartao.toString() + "/carteiras/{id}").build(idNovoRecurso.toString());
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/{idCartao}/carteiras/{idCarteira}")
    public ResponseEntity<?> getCarteira(@PathVariable UUID idCartao, @PathVariable UUID idCarteira) {
       services.encontrarCartaoPorId(idCartao);
        ResponseEntity<?> carteiraDigital = services.encontraCarteiraPorId(idCarteira);
       return ResponseEntity.ok(carteiraDigital);
    }

}//PONTOS CDD: 4
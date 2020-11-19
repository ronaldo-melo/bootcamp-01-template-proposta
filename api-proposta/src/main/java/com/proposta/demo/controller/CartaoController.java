package com.proposta.demo.controller;

import com.proposta.demo.model.Cartao;
import com.proposta.demo.model.Solicitacao;
import com.proposta.demo.request.AvisoRequest;
import com.proposta.demo.request.BiometriaRequest;
import com.proposta.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private SalvaSolicitacao salvaSolicitacao;  //CDD 5

    @Autowired
    private SalvaAviso salvaAviso;

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

    @PostMapping("/{id}")                                                                   //CDD 6
    private ResponseEntity<?> salvarBiometria(@PathVariable UUID id, @RequestBody @Valid BiometriaRequest request, UriComponentsBuilder builder){
        Long idRecurso = adicionaBiometraEmCartao.adionarBiometria(id, request);
        URI uri = builder.path("/cartoes" + id.toString() + "/{idRecurso}").build(idRecurso);

        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/bloqueios/{id}")
    public ResponseEntity<?> bloquearCartao(@PathVariable UUID id, UriComponentsBuilder builder, HttpServletRequest request){

        Long resourceAdress = validaBloqueioNoCartao.valida(request, id, manager);
        URI uri = builder.path("cartoes/bloqueios/".concat(id.toString()) + "/{id}").build(resourceAdress);

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/bloqueios/{idCartao}/{idBloqueio}")
    public ResponseEntity<?> getBloqueioPorId(@PathVariable UUID idCartao, @PathVariable Long idBloqueio){
        return ResponseEntity.ok(buscarBloqueio.buscar(idCartao, idBloqueio, manager));
    }

    @PostMapping("/solicitacoes/{id}")
    public ResponseEntity<?> solicitarRecuperacaoDeSenha(@PathVariable UUID idCartao, HttpServletRequest httpServletRequest, UriComponentsBuilder builder){
        Long idRecurso = salvaSolicitacao.salvar(idCartao, httpServletRequest, manager);
        URI uri = builder.path("cartoes/recuperacao-senha/" + idCartao + "/{id}").build(idCartao);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/recuperacao-senha/{idCartao}/solicitacoes/{idSolicitacao}")
    public ResponseEntity<?> getRecuperacao(@PathVariable UUID idCartao, @PathVariable Long idSolicitacao){
        FindEntity.findEntityById(Cartao.class, idCartao, manager);
        //CDD 7                     //CDD 8
        Solicitacao solicitacao = FindEntity.findEntityById(Solicitacao.class, idSolicitacao, manager);
        return ResponseEntity.ok(solicitacao.toResponse());
    }

    @PostMapping("/aviso-viagem/{idCartao}")
    public ResponseEntity<?> salvarAvisoViagem(@PathVariable UUID idCartao, HttpServletRequest httpServletRequest, @Valid @RequestBody AvisoRequest avisoRequest, UriComponentsBuilder builder){
        Long id = salvaAviso.salvar(idCartao, httpServletRequest, avisoRequest, manager);
        URI uri = builder.path("cartoes/aviso-viagem/" + idCartao.toString() + "/{id}").build(id);
        return ResponseEntity.created(uri).build();
    }

}//PONTOS CDD: 8
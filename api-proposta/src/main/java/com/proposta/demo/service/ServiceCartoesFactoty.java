package com.proposta.demo.service;

import com.proposta.demo.request.AvisoRequest;
import com.proposta.demo.request.BiometriaRequest;
import com.proposta.demo.request.CarteiraDigitalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Service
public class ServiceCartoesFactoty {

    @Autowired
    private EncontraEntitdadeServiceFactory encontraEntitdadeServiceFactory; //CDD 1

    @Autowired
    private AdicionaBiometraEmCartao adicionaBiometraEmCartao; //CDD 2

    @Autowired
    private ValidaBloqueioNoCartao validaBloqueioNoCartao; //CDD 3

    @Autowired
    private BuscaBloqueio buscaBloqueio; //CDD 4

    @Autowired
    private SalvaSolicitacao salvaSolicitacao; //CDD 5

    @Autowired
    private SalvaAviso salvaAviso; //CDD 6

    @Autowired
    private SalvaCarteiraDigital salvaCarteiraDigital; //CDD 7

    public ResponseEntity<?> recuperarCartaoPorIdProposta(Long idProposta){
        return encontraEntitdadeServiceFactory.encontrarNoLegadoCartaoPorIdProposta(idProposta);
    }

    public ResponseEntity<?> recuperarCartaoPorId(UUID idCartao){
        return encontraEntitdadeServiceFactory.encontrarNoLegadoCartaoPorId(idCartao);
    }
                                                            //CDD 7
    public long adicionarBiometraEmCartao(UUID id, @Valid BiometriaRequest request) {
        return adicionaBiometraEmCartao.adicionar(id, request);
    }

    public Long validarBloqueioNoCartao(HttpServletRequest request, UUID id) {
        return validaBloqueioNoCartao.validar(request, id);
    }

    public ResponseEntity<?> buscarBloqueio(UUID idCartao, Long idBloqueio) {
        return buscaBloqueio.buscar(idCartao, idBloqueio);
    }

    public Long salvarSolicitacao(UUID idCartao, HttpServletRequest httpServletRequest) {
        return salvaSolicitacao.salvar(idCartao, httpServletRequest);
    }
                                                                                        //CDD 9
    public Long salvarAviso(UUID idCartao, HttpServletRequest httpServletRequest, AvisoRequest avisoRequest) {
        return salvaAviso.salvar(idCartao, httpServletRequest, avisoRequest);
    }

    public UUID salvarCarteiraDigital(@NotNull UUID idCartao, CarteiraDigitalRequest request) {
        return salvaCarteiraDigital.salvar(idCartao, request);
    }

    public ResponseEntity<?> encontrarCartaoPorId(UUID idCartao){
        ResponseEntity<?> cartao = encontraEntitdadeServiceFactory.encontrarCartaoPorId(idCartao);
        return ResponseEntity.ok(cartao);
    }

    public ResponseEntity<?> encontraCarteiraPorId(UUID idCarteira){
        ResponseEntity<?> carteiraDigital = encontraEntitdadeServiceFactory.encontrarCarteiraPorId(idCarteira);
        return ResponseEntity.ok(carteiraDigital);
    }

    public ResponseEntity<?> encontrarSolicitacaoPorId(Long idSolicitacao){
        ResponseEntity<?> solicitacao =  encontraEntitdadeServiceFactory.encontrarSolicitacaoPorId(idSolicitacao);
        return ResponseEntity.ok(solicitacao);
    }

}  //Pontos CDD: 9

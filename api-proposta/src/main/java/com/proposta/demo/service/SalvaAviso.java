package com.proposta.demo.service;

import com.proposta.demo.model.AvisoViagem;
import com.proposta.demo.model.Cartao;
import com.proposta.demo.model.enums.EstadoBloqueio;
import com.proposta.demo.request.AvisoRequest;
import com.proposta.demo.service.model.viagem.SolicitacaoAvisoViagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Validated
public class SalvaAviso {

    @Autowired
    private NotificaViagem notificaViagem;

    @NotBlank
    private String destino;

    @Future
    private LocalDate terminoaDaViagem;

    @NotEmpty
    private String ipDoCliente;

    @NotEmpty
    private String userAgent;

    @NotNull
    private Cartao cartao; //CDD 1

    @Autowired
    private FindEntity findEntity;

    @Autowired
    private EntityManager manager;

    @Transactional                                                                                      //CDD 2
    public Long salvar(UUID idCartao, HttpServletRequest httpServletRequest, AvisoRequest avisoRequest){

        //CDD 3
        SolicitacaoAvisoViagem solicitacaoAvisoViagem = new SolicitacaoAvisoViagem(avisoRequest.getTerminoaDaViagem(), avisoRequest.getDestino());
        notificaViagem.notificar(solicitacaoAvisoViagem, idCartao);

        destino = avisoRequest.getDestino();
        terminoaDaViagem = avisoRequest.getTerminoaDaViagem();
        userAgent = httpServletRequest.getHeader("User-Agent");
        ipDoCliente = httpServletRequest.getRemoteAddr();
        cartao = findEntity.findById(Cartao.class, idCartao);
        //CDD 4
        AvisoViagem novoAvisoViagem = new AvisoViagem(destino, terminoaDaViagem, ipDoCliente, userAgent, cartao);

        novoAvisoViagem = manager.merge(novoAvisoViagem);

        cartao.adicinarAvisoViagem(novoAvisoViagem);
        manager.merge(cartao);

        return novoAvisoViagem.getId();
    }

}//PONTOS CDD: 5

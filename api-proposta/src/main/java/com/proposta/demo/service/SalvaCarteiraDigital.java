package com.proposta.demo.service;

import com.proposta.demo.model.Cartao;
import com.proposta.demo.model.CarteiraDigital;
import com.proposta.demo.model.enums.TipoCarteira;
import com.proposta.demo.request.CarteiraDigitalRequest;
import com.proposta.demo.service.model.carteiradigital.ResultadoCarteira;
import com.proposta.demo.service.model.carteiradigital.SolicitacaoInclusaoCarteira;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class SalvaCarteiraDigital {

    @Autowired
    private ConsultarCarteiraNoLegado consultarCarteiraNoLegado; //CCD 1

    @Autowired
    private ConsultaCarteiraNoBanco consultaCarteiraNoBanco; //CCD 2

    @Autowired
    private FindEntity findEntity;

    @Autowired
    private EntityManager manager;

    @Transactional
    public UUID salvar(@NotNull UUID idCartao, CarteiraDigitalRequest request){ //CCD 3

        SolicitacaoInclusaoCarteira solicitacao = new SolicitacaoInclusaoCarteira(request.getEmail(), request.getCarteira().toString()); //CCD 4
        ResultadoCarteira resultadoCarteira = consultarCarteiraNoLegado.consultar(solicitacao, idCartao); //CCD 5
        UUID idNovaCarteira = UUID.fromString(resultadoCarteira.getId());
        LocalDateTime associdoEm = LocalDateTime.now();

        //CCD 6
        Cartao cartao = findEntity.findById(Cartao.class, idCartao);

        consultaCarteiraNoBanco.consultar(cartao, TipoCarteira.valueOf(request.getCarteira().toUpperCase()));

        CarteiraDigital carteiraDigital = new CarteiraDigital(idNovaCarteira, request.getEmail(), associdoEm, TipoCarteira.valueOf(request.getCarteira().toUpperCase()), cartao); //CCD 8
        UUID idNovoRecurso = manager.merge(carteiraDigital).getId();
        cartao.adicionarCarteira(carteiraDigital);
        manager.merge(cartao);

        return idNovoRecurso;
    }

}// PONTOS CDD: 8

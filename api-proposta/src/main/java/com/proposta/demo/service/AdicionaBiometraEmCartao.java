package com.proposta.demo.service;

import com.proposta.demo.model.Biometria;
import com.proposta.demo.model.Cartao;
import com.proposta.demo.request.BiometriaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.UUID;

@Service
public class AdicionaBiometraEmCartao {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ServiceCartoesFactoty factoty;

    @Autowired
    private FindEntity findEntity;

    @Transactional              //CDD 1
    public long adicionar(UUID id, @Valid BiometriaRequest request){

        Long idBiometria = 0L;

        //CDD 2         //CDD 3
        Cartao cartao = findEntity.findById(Cartao.class, id);
        Biometria biometria = new Biometria(request.getFingerPrint(), cartao);

        biometria = manager.merge(biometria);
        cartao.adicionarBiometria(biometria);

        return biometria.getId();
    }

}//PONTOS CDD: 3

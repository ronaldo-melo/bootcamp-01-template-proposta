package com.proposta.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncodeDocumento {

    @Autowired
    private PasswordEncoder encoder;

    public String encode(String documento){
        return encoder.encode(documento);
    }

}

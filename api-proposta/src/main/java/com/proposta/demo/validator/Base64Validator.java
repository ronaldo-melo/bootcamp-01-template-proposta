package com.proposta.demo.validator;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Base64Validator implements ConstraintValidator<ValorBase64, String> {

    @Override
    public void initialize(ValorBase64 constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if(!Base64.isBase64(value.getBytes())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A biometria foi informada de forma errada. Tente de novo");
        }

        return true;
    }
}

package com.proposta.demo.validator;

import com.proposta.demo.model.enums.TipoCarteira;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CarteiraDigitalValidator implements ConstraintValidator<CarteiraDigitalValida, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
            boolean ehValido = false;

        try {

            if (Arrays.stream(TipoCarteira.values()).collect(Collectors.toList()).contains(TipoCarteira.valueOf(value.toUpperCase())))
                ehValido =  true;

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O dado '" + value + "' é uma opção inválida de carteira");
        }

        return ehValido;
    }
}

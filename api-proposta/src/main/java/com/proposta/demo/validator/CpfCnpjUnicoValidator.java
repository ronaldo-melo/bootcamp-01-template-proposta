package com.proposta.demo.validator;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfCnpjUnicoValidator implements ConstraintValidator<CpfCnpjUnico, String> {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(CpfCnpjUnico constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        Query query = manager.createNativeQuery("select p.cpf_ou_cnpj from Proposta p where p.cpf_ou_cnpj = :value");

        query.setParameter("value", value);

        if(!query.getResultList().isEmpty())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe um cadastro com o CPF/CNPJ informado");

        return true;
    }
}

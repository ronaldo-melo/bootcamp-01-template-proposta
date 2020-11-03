package com.proposta.demo.validator;

import com.proposta.demo.model.Proposta;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CpfCnpjUnicoValidator implements ConstraintValidator<DocumentoUnico, String> {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(DocumentoUnico constraintAnnotation) {

    }

    @Override
    public boolean isValid(@NotNull String value, ConstraintValidatorContext context) {

        Query query = manager.createNativeQuery("select p.documento from Proposta p where p.documento = :value");
        query.setParameter("value", value);

        if(!query.getResultList().isEmpty())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Já existe um cadastro com o CPF/CNPJ informado.");

        return true;
    }
}

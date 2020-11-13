package com.proposta.demo.validator;

import com.proposta.demo.model.Biometria;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.springframework.http.HttpStatus;
import org.springframework.util.Base64Utils;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UnicoValorValidator implements ConstraintValidator<UnicoValor, String> {

    @PersistenceContext
    private EntityManager manager;

    private Class <?> entity;

    private String domainAttribute;

    @Override
    public void initialize(UnicoValor constraintAnnotation) {
        entity = constraintAnnotation.entity();
        domainAttribute = constraintAnnotation.valorDoAtributo();
    }

    @Override
    public boolean isValid(String mustBeValid, ConstraintValidatorContext context) {

        String originalmustBeValid = mustBeValid;

        if(entity.isAssignableFrom(Biometria.class)){
            mustBeValid = Base64Utils.encodeToString(mustBeValid.getBytes());
        }

        String sql = String.format("SELECT t.%s FROM %s t WHERE t.%s =:value",
                domainAttribute, entity.getSimpleName(), domainAttribute
        );

        Query query = manager.createQuery(sql);

        query.setParameter("value", mustBeValid);

        List<?> list = query.getResultList();

        mustBeValid = originalmustBeValid;

        if(!list.isEmpty()) {

            String msg = String.format("Este(a) '%s' já é associado(a) há um(a) '%s'. Insira um(a) %s diferente.", domainAttribute, entity.getSimpleName(), domainAttribute);

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, msg);
        }
        return list.isEmpty();
    }
}

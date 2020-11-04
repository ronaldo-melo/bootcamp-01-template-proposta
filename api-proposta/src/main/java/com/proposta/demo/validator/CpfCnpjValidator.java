package com.proposta.demo.validator;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfCnpjValidator implements ConstraintValidator<DocumentoFormatoValido, CharSequence> {


    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext context) {

        if(charSequence == null){
            return true;
        }

        CNPJValidator cnpJvalidator = new CNPJValidator();
        CPFValidator cpfValidator = new CPFValidator();

        cnpJvalidator.initialize(null);
        cpfValidator.initialize(null);

        return cnpJvalidator.isValid(charSequence, context) || cpfValidator.isValid(charSequence, context);
    }
}

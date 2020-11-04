package com.proposta.demo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DocumentoUnicoValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface DocumentoUnico {

    String message() default "Já existe um cadastro com CPF/CNPJ informado";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

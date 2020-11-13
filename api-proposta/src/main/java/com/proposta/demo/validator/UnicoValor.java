package com.proposta.demo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UnicoValorValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UnicoValor {

    String message() default "Este valor já foi inserido. Tente outro.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class <?> entity();

    String valorDoAtributo();

}

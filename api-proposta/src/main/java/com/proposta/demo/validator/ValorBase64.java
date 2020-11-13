package com.proposta.demo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = Base64Validator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValorBase64 {

    String message() default "Este valor já foi inserido. Tente outro.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

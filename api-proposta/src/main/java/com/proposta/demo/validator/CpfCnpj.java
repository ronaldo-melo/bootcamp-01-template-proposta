package com.proposta.demo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CpfCnpjValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CpfCnpj {

    String message() default "{br.com.zup.nossocartao.novaproposta.CpfCnpj}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

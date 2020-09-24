package com.chu.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UniqueUserNameValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUserName {
    String message() default "{chu.constraints.username.UniqueUsername.message}";
    Class<?>[]groups() default {};
    Class<? extends Payload>[] payload() default { };
}

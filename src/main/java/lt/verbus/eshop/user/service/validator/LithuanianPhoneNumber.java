package lt.verbus.eshop.user.service.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Constraint(validatedBy = {LithuanianPhoneValidator.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface LithuanianPhoneNumber {

    String message() default "{validation.lithuanian_number.format}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

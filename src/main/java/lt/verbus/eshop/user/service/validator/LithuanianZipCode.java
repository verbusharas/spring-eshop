package lt.verbus.eshop.user.service.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Constraint(validatedBy = {LithuanianZipCodeValidator.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface LithuanianZipCode {
    String message() default "{validation.lithuanian_zip.format}";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default {};
}

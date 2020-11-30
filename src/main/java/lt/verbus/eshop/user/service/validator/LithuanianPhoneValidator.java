package lt.verbus.eshop.user.service.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static lt.verbus.eshop.util.StringUtil.areCharsDigits;


public class LithuanianPhoneValidator implements ConstraintValidator<LithuanianPhoneNumber, String> {

    /**
     * Phone number will be considered valid in the following cases:
     * 86xxxxxxx and +3706xxxxxxx
     * where x has to be a positive number
     * Failing to provide a phone number (null) is also allowed
     */

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return true;
        }
        if (value.length() == 9 && value.startsWith("86")) {
            return areCharsDigits(value.substring(2));
        }
        if (value.length() == 12 && value.startsWith("+370")) {
            return areCharsDigits(value.substring(4));
        }
        return false;
    }


}

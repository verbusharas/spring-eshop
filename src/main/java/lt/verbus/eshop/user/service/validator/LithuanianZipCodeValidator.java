package lt.verbus.eshop.user.service.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static lt.verbus.eshop.util.StringUtil.areCharsDigits;

public class LithuanianZipCodeValidator implements ConstraintValidator<LithuanianZipCode, String> {


    /**
     * Zip code will be considered valid in the following cases:
     * LTxxxxx ant LT-xxxxx where x is a number
     * Failing to provide ZIP code (null) is also valid.
     */

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null){
            return true;
        }
        if (value.length() == 7 && value.startsWith("LT")){
            return areCharsDigits(value.substring(2));
        }
        if(value.length()==8&&value.startsWith("LT-")) {
            return areCharsDigits(value.substring(3));
        }
        return false;
    }




}

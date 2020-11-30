package lt.verbus.eshop.user.exception;

import lt.verbus.eshop.util.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super(Translator.getMessage("user.not_found"));
    }

}

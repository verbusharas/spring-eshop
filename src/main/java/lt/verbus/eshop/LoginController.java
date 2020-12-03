package lt.verbus.eshop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sign-in")
public class LoginController {

    @GetMapping
    public String login(){
        return "user/login";
    }

}

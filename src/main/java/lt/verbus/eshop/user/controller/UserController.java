package lt.verbus.eshop.user.controller;

import lt.verbus.eshop.user.exception.UserNotFoundException;
import lt.verbus.eshop.user.model.User;
import lt.verbus.eshop.user.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAll(@PageableDefault(size=5) Pageable pageable, Model model){
        model.addAttribute("users", userService.getAllUsers(pageable));
        return "user/user-list";
    }

    @GetMapping("/new")
    public String getNewUserForm(Model model){
        model.addAttribute("user", new User());
        return "user/new-user";
    }

    @PostMapping
    public String addNewUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "user/new-user";
        }
        return "redirect:/user";
    }


    @GetMapping("/{id}")
    public String test(@PathVariable long id){
        throw new UserNotFoundException();
    }


}

package lt.verbus.eshop.user.controller;

import lt.verbus.eshop.product.model.Product;
import lt.verbus.eshop.user.model.User;
import lt.verbus.eshop.user.service.UserService;
import lt.verbus.eshop.user.service.validator.UserExtraValidator;
import org.springframework.data.domain.Page;
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
@RequestMapping("/private/user")
public class UserController {

    private final UserService userService;
    private final UserExtraValidator userExtraValidator;

    public UserController(UserService userService, UserExtraValidator userExtraValidator) {
        this.userService = userService;
        this.userExtraValidator = userExtraValidator;
    }

    @GetMapping
    public String getAllUsers(@PageableDefault(size = 5) Pageable pageable, Model model) {
        Page<User> pageOfUsers = userService.getAllUsers(pageable);
        model.addAttribute("usersPage", pageOfUsers);
        return "user/user-list";
    }

    @GetMapping("/new")
    public String getNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/new-user";
    }

    @PostMapping
    public String addNewUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/new-user";
        }

        userService.addUser(user);
        return "redirect:/private/user/" + user.getId();
    }

    @GetMapping("/{id}")
    public String getUserProfile(@PathVariable long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user/user-profile";
    }

    @GetMapping("/update/{id}")
    public String getUpdateUserForm(Model model, @PathVariable long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "user/edit-user";
    }


    @PostMapping("/{id}")
    public String updateUser(@PathVariable long id, BindingResult bindingResult, @ModelAttribute("user") User user) {
        userExtraValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "user/edit-user";
        }
        userService.updateUser(id, user);
        return "redirect:/user/" + id;
    }


}

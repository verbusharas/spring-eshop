package lt.verbus.eshop.cart.controller;

import lt.verbus.eshop.cart.service.CartService;
import lt.verbus.eshop.product.model.Product;
import lt.verbus.eshop.product.service.ProductService;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
@SessionAttributes({"cart", "h2path"})
public class CartController {

    private final ProductService productService;
    private final CartService cartService;
    private Environment env;

    public CartController(ProductService productService, CartService cartService, Environment env) {
        this.productService = productService;
        this.cartService = cartService;
        this.env = env;
    }

    @ModelAttribute("cart")
    public List<Product> cart() {
        return new ArrayList<>();
    }

    @ModelAttribute("h2path")
    public String h2path() {
        return env.getProperty("spring.h2.console.path");
    }

    @GetMapping
    private String openCartList(@ModelAttribute("cart") List<Product> cart, Model model) {
        model.addAttribute("cartTotals", cartService.countTotals(cart));
        model.addAttribute("totalProductsInStore", productService.getTotalProductsInStore());
        return "cart/cart-list";
    }


    @GetMapping("/add/{productId}")
    public String addProductToCart(@PathVariable Long productId, @ModelAttribute("cart") List<Product> cart, Model model){
        Product product = productService.getProductById(productId);
        cart.add(product);
        return "redirect:/public/product";
    }

    @GetMapping("/checkout")
    public String checkout(SessionStatus sessionStatus, RedirectAttributes redirectAttributes, @ModelAttribute("cart") List<Product> cart) {
        redirectAttributes.addFlashAttribute("cartProducts", cart);
        redirectAttributes.addFlashAttribute("loggedInUser", ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        redirectAttributes.addFlashAttribute("cartTotals", cartService.countTotals(cart));
        sessionStatus.setComplete();
        return "redirect:/order/preview";
    }


}

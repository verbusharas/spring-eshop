package lt.verbus.eshop.cart.controller;

import lt.verbus.eshop.cart.service.CartService;
import lt.verbus.eshop.product.model.Product;
import lt.verbus.eshop.product.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")
public class CartController {

    private final ProductService productService;
    private final CartService cartService;

    public CartController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @ModelAttribute("cart")
    public List<Product> cart() {
        return new ArrayList<>();
    }

    @GetMapping
    private String openCartList(@ModelAttribute("cart") List<Product> cart, Model model) {
        model.addAttribute("cartTotals", cartService.countTotalPrice(cart));
        return "cart/cart-list";
    }


    @GetMapping("/add/{productId}")
    public String addProductToCart(@PathVariable Long productId, @ModelAttribute("cart") List<Product> cart){
        Product product = productService.getProductById(productId);
        cart.add(product);
        return "redirect:/product";
    }

    @GetMapping("/checkout")
    public String checkout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/cart";
    }


}

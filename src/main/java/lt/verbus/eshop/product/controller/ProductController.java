package lt.verbus.eshop.product.controller;

import lt.verbus.eshop.product.exception.ProductNotFoundException;
import lt.verbus.eshop.product.model.Product;
import lt.verbus.eshop.product.service.ProductService;
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
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }



    @GetMapping
    public String getAllProducts(@PageableDefault(size=7) Pageable pageable, Model model) {
        Page<Product> pageOfProducts = productService.getPageOfProducts(pageable);
        model.addAttribute("productsPage", pageOfProducts);
        return "product/product-list";
    }

    @GetMapping("/{id}")
    public String getSingleProduct(@PathVariable long id, Model model) throws ProductNotFoundException {
        model.addAttribute("product", getProductById(id));
        return "product/product";
    }

    @GetMapping("/new")
    public String getNewProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/new-product";
    }

    @PostMapping("/new")
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "product/new-product";
        }
        productService.saveOrUpdateProduct(product);
        return "redirect:/product/" + product.getId();
    }

    @GetMapping("/edit-name/{id}")
    public String getEditProductNameForm(@PathVariable long id, Model model) {
        model.addAttribute("product", getProductById(id));
        return "product/edit-product-name";
    }

    @PostMapping("/edit-name")
    public String updateProductName(@ModelAttribute("product") Product product, Model model) {
        model.addAttribute("products", productService.renameProduct(product));
        return "redirect:/product/" + product.getId();
    }

    @GetMapping("/edit-product/{id}")
    public String getEditProductForm(@PathVariable long id, Model model) {
        model.addAttribute("product", getProductById(id));
        return "product/edit-product";
    }

    @PostMapping("/edit-product")
    public String updateProduct(@ModelAttribute("product") Product product, Model model) {
        productService.saveOrUpdateProduct(product);
        return "redirect:/product/" + product.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable long id, Model model) {
        productService.deleteProduct(getProductById(id));
        return "redirect:/product";
    }

    private Product getProductById(long id) {
        return productService.getProductById(id);
    }

}

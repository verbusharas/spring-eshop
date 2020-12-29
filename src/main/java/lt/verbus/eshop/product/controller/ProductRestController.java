package lt.verbus.eshop.product.controller;

import lt.verbus.eshop.product.model.Product;
import lt.verbus.eshop.product.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/product")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/rest/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProductsAsJson(){
        return productService.getPageOfProducts(PageRequest.of(0, 10)).getContent();
    }

    @GetMapping("/rest/xml")
    public List<Product> getAllProductsAsXml(){
        return productService.getPageOfProducts(PageRequest.of(0, 10)).getContent();
    }

}

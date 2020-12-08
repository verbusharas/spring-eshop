package lt.verbus.eshop.product.service;

import lt.verbus.eshop.product.exception.ProductNotFoundException;
import lt.verbus.eshop.product.model.Product;
import lt.verbus.eshop.product.repository.JdbcTemplateProductRepository;
import lt.verbus.eshop.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(JdbcTemplateProductRepository jdbcTemplateProductRepository, ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(long id){

        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public int getTotalProductsInStore(){
        return productRepository.findAll().size();
    }


    public List<Product> saveOrUpdateProduct(Product product){
        productRepository.save(product);
        return getAllProducts();
    }

    @Transactional
    public List<Product> renameProduct(Product product){
        Product productInDb =  productRepository.getOne(product.getId());
        productInDb.setName(product.getName());
        return getAllProducts();
    }

    public List<Product> deleteProduct(Product product){
        productRepository.delete(product);
        return getAllProducts();
    }


    public Page<Product> getPageOfProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}

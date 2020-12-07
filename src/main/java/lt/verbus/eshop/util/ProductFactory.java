package lt.verbus.eshop.util;

import com.github.javafaker.Faker;
import lt.verbus.eshop.product.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductFactory {

    public List<Product> getProducts(int quantity, int initId) {
        Faker faker = new Faker();
        List<Product> products = new ArrayList<>();

        for (int i = 0; i<quantity; i++) {
            Product product = new Product();
            product.setId(initId++);
            product.setName(faker.commerce().productName());
            product.setPrice(BigDecimal.valueOf(Double.parseDouble(faker.commerce().price())));
            product.setDescription("Color: " + faker.commerce().color() + ", material: " + faker.commerce().department() + ", made in " + faker.country().name());
            product.setInStock((int)faker.number().randomNumber(2, false));
            products.add(product);
        }
        return products;
    }


    public List<Product> getProductsNoId(int quantity) {
        Faker faker = new Faker();
        List<Product> products = new ArrayList<>();

        for (int i = 0; i<quantity; i++) {
            Product product = new Product();
            product.setName(faker.commerce().productName());
            product.setPrice(BigDecimal.valueOf(Double.parseDouble(faker.commerce().price())));
            product.setDescription("Color: " + faker.commerce().color() + ", material: " + faker.commerce().department() + ", made in " + faker.country().name());
            product.setInStock((int)faker.number().randomNumber(2, false));
            products.add(product);
        }
        return products;
    }

    public String printProducts(List<Product> products) {
        String str = "";
        for (Product product : products) {
            str+="(\'" + product.getName() + "\', " + product.getInStock() + ", " + product.getPrice() + ", \'" + product.getDescription() + "\'),\n";
        }

        return str;
    }


}

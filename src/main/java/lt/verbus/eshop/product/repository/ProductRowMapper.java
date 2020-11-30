package lt.verbus.eshop.product.repository;

import lt.verbus.eshop.product.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setName(resultSet.getString("product_name"));
        product.setInStock(resultSet.getInt("in_stock"));
        product.setPrice(resultSet.getDouble("price"));
        product.setDescription(resultSet.getString("description"));
        return product;

    }
}

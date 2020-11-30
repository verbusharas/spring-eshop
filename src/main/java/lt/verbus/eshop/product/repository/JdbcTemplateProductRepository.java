package lt.verbus.eshop.product.repository;

import lt.verbus.eshop.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcTemplateProductRepository {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM PRODUCTS WHERE ID=?";
    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM PRODUCTS";
    private static final String UPDATE_PRODUCT_BY_ID = "UPDATE PRODUCTS SET product_name = :name, in_stock = :in_stock, price = :price, description = :description WHERE ID = :id";
    private static final String UPDATE_PRODUCT_NAME_BY_ID = "UPDATE PRODUCTS SET product_name = :name WHERE ID = :id";
    private static final String DELETE_PRODUCT_BY_ID = "DELETE FROM PRODUCTS WHERE ID=?";
    private static final String SAVE_PRODUCT = "INSERT INTO PRODUCTS (product_name, in_stock, price, description) VALUES (:name, :in_stock, :price, :description)";


    @Autowired
    public JdbcTemplateProductRepository(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Product getProductById(long id){
        return jdbcTemplate.queryForObject(SELECT_PRODUCT_BY_ID, new ProductRowMapper(), id);
    }

    public List<Product> getAllProducts(){
        List<Product> products = jdbcTemplate.query(SELECT_ALL_PRODUCTS, new ProductRowMapper());
        return products;
    }

    public List<Product> saveProduct(Product product){
        MapSqlParameterSource namedParams = new MapSqlParameterSource();
        namedParams.addValue("name", product.getName());
        namedParams.addValue("in_stock", product.getInStock());
        namedParams.addValue("price", product.getPrice());
        namedParams.addValue("description", product.getDescription());
        namedParameterJdbcTemplate.update(SAVE_PRODUCT, namedParams);
        return getAllProducts();
    }

    public List<Product> updateProduct(Product product){
        MapSqlParameterSource namedParams = new MapSqlParameterSource();
        namedParams.addValue("name", product.getName());
        namedParams.addValue("in_stock", product.getInStock());
        namedParams.addValue("price", product.getPrice());
        namedParams.addValue("description", product.getDescription());
        namedParams.addValue("id", product.getId());
        namedParameterJdbcTemplate.update(UPDATE_PRODUCT_BY_ID, namedParams);
        return getAllProducts();
    }

    public List<Product> updateProductName(Product product){
        MapSqlParameterSource namedParams = new MapSqlParameterSource();
        namedParams.addValue("name", product.getName());
        namedParams.addValue("id", product.getId());
        namedParameterJdbcTemplate.update(UPDATE_PRODUCT_NAME_BY_ID, namedParams);
        return getAllProducts();
    }

    public List<Product> deleteProduct(long id){
        jdbcTemplate.update(DELETE_PRODUCT_BY_ID, id);
        return getAllProducts();
    }

}

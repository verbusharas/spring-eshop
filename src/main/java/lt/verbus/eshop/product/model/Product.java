package lt.verbus.eshop.product.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_name")
    @NotBlank(message = "{product.name.not.blank}")
    @Size(min = 5, max = 255, message = "{product.name.size}")
    private String name;

    @Column(name = "in_stock")
    @NotNull(message = "{product.not.null}")
    @Min(value=0, message = "{product.stock.min}")
    private Integer inStock;

    @Column(name = "price")
    @NotNull(message = "{product.not.null}")
    @Positive(message="{product.price.positive}")
    private Double price;

    @Column(name = "description")
    @Size(min = 5, max = 255, message = "{product.description.size}")
    private String description;
}

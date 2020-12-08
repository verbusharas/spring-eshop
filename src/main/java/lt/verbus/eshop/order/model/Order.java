package lt.verbus.eshop.order.model;


import lombok.Getter;
import lombok.Setter;
import lt.verbus.eshop.product.model.Product;
import lt.verbus.eshop.user.model.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @OneToMany
    private List<Product> products = new ArrayList<>();

    @NotNull
    @ManyToOne
    private User user;


}

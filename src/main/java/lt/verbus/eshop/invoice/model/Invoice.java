package lt.verbus.eshop.invoice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.verbus.eshop.cart.model.CartTotals;
import lt.verbus.eshop.config.Company;
import lt.verbus.eshop.order.model.Order;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Order order;

    @Embedded
    private CartTotals cartTotals;

    @Embedded
    private Company company;

    private long sequenceNo;

    @NotBlank
    private String fullSerialNo;

    @CreationTimestamp
    private LocalDateTime createdAt;
}

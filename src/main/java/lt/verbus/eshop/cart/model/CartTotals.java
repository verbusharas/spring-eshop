package lt.verbus.eshop.cart.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
public class CartTotals {
    private BigDecimal totalBrutto;
    private BigDecimal totalNetto;
    private BigDecimal totalVat;
}

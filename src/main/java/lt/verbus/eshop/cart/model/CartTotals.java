package lt.verbus.eshop.cart.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartTotals {
    private BigDecimal totalBrutto;
    private BigDecimal totalNetto;
    private BigDecimal totalVat;
}

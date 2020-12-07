package lt.verbus.eshop.cart.service;

import lt.verbus.eshop.cart.model.CartTotals;
import lt.verbus.eshop.product.model.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static lt.verbus.eshop.util.MoneyUtil.VAT;

@Service
public class CartService {
    public CartTotals countTotalPrice(List<Product> products) {
        BigDecimal totalNetto = products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalBrutto = totalNetto.multiply(BigDecimal.valueOf(VAT));
        BigDecimal totalVat = totalBrutto.subtract(totalNetto);

        CartTotals cartTotals = new CartTotals();
        cartTotals.setTotalBrutto(totalBrutto.setScale(2, RoundingMode.HALF_DOWN));
        cartTotals.setTotalNetto(totalNetto.setScale(2, RoundingMode.HALF_DOWN));
        cartTotals.setTotalVat(totalVat.setScale(2, RoundingMode.HALF_DOWN));
        return cartTotals;
    }
}

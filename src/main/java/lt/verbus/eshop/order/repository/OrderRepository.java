package lt.verbus.eshop.order.repository;

import lt.verbus.eshop.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

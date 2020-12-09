package lt.verbus.eshop.order.service;

import lt.verbus.eshop.cart.model.CartTotals;
import lt.verbus.eshop.invoice.service.InvoiceService;
import lt.verbus.eshop.order.model.Order;
import lt.verbus.eshop.order.repository.OrderRepository;
import lt.verbus.eshop.product.model.Product;
import lt.verbus.eshop.user.model.User;
import lt.verbus.eshop.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final InvoiceService invoiceService;

    public OrderService(OrderRepository orderRepository, UserService userService, InvoiceService invoiceService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.invoiceService = invoiceService;
    }

    /**
     * Creates the order by the given name. This function also generates an invoice and returns its ID
     */
    @Transactional
    public long placeOrder(String username, List<Product> cartProducts){
        Order createdOrder = createNewOrder(username, cartProducts);
        return invoiceService.createInvoice(createdOrder);
    }

    /**
     * Creates the order by the given cart products and username.
     */
    private Order createNewOrder(String userName, List<Product> cartProducts){
        User currentUser = userService.findUserByUserName(userName);
        Order order = new Order();
        order.setUser(currentUser);
        order.setProducts(cartProducts);
        return orderRepository.save(order);
    }

    public CartTotals getOrderTotals(long id) {
        return invoiceService.getPriceByOrderId(id);
    }

    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    public Order getOrderById(long id) {
        return orderRepository.getOne(id);
    }

}

package lt.verbus.eshop.invoice.repository;

import lt.verbus.eshop.invoice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Invoice findByOrderId(Long orderId);

}

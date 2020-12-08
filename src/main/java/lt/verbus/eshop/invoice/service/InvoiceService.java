package lt.verbus.eshop.invoice.service;


import lt.verbus.eshop.cart.model.CartTotals;
import lt.verbus.eshop.config.Company;
import lt.verbus.eshop.invoice.model.Invoice;
import lt.verbus.eshop.invoice.repository.InvoiceRepository;
import lt.verbus.eshop.cart.service.CartService;
import lt.verbus.eshop.order.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final Company company;
    private final CartService cartService;


    public InvoiceService(InvoiceRepository invoiceRepository, Company company, CartService cartService) {
        this.invoiceRepository = invoiceRepository;
        this.company = company;
        this.cartService = cartService;
    }

    public Long createInvoice(Order createdOrder) {
        var products = createdOrder.getProducts();
        CartTotals cartTotals = cartService.countTotals(products);
        Long sequenceNo = getNextInvoiceSequence();
        Invoice invoice = new Invoice();
        invoice.setCompany(company);
        invoice.setOrder(createdOrder);
        invoice.setCartTotals(cartTotals);
        invoice.setSequenceNo(sequenceNo);
        invoice.setFullSerialNo(getFullInvoiceSerialNo(sequenceNo));
        return invoiceRepository.save(invoice).getId();
    }

    public Invoice findById(Long id) {
        return invoiceRepository.getOne(id);
    }

    public Page<Invoice> getAllInvoices(Pageable pageable) {
        return invoiceRepository.findAll(pageable);
    }

    public CartTotals getPriceByOrderId(Long id) {
        return invoiceRepository.findByOrderId(id).getCartTotals();
    }

    private Long getNextInvoiceSequence() {
        var invoices = invoiceRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        if (invoices.size() == 0) {
            return 1L;
        }
        return invoices.get(0).getSequenceNo() + 1;
    }

    private String getFullInvoiceSerialNo(Long sequenceNo) {
        return company
                .getSequenceName()
                .concat("-00")
                .concat(String.valueOf(sequenceNo));
    }


}

package lt.verbus.eshop.invoice.service;

import lt.verbus.eshop.cart.service.CartService;
import lt.verbus.eshop.config.Company;
import lt.verbus.eshop.invoice.model.Invoice;
import lt.verbus.eshop.invoice.repository.InvoiceRepository;
import lt.verbus.eshop.order.model.Order;
import lt.verbus.eshop.product.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceShould {

    @Mock
    private InvoiceRepository invoiceRepository;

    private Company company;

    @Captor
    private ArgumentCaptor<Invoice> invoiceCaptor;

    @Mock
    private CartService cartService;

    private static final long INVOICE_ID = 123L;
    private static final Invoice SAVED_INVOICE = Invoice.builder().id(INVOICE_ID).build();

    @InjectMocks
    private InvoiceService invoiceService;

    @BeforeEach
    void init() {
        company = Company.builder()
                .name("name")
                .number(123)
                .iban("LT0000000012333")
                .address("Mano adresas")
                .sequenceName("SN")
                .build();

        invoiceService = new InvoiceService(invoiceRepository, company, cartService);

        when(invoiceRepository.save(any())).thenReturn(SAVED_INVOICE);
    }


    @Test
    void return_id_of_new_invoice() {
        // when
        long invoiceId = invoiceService.createInvoice(new Order());

        // then
        assertEquals(INVOICE_ID, invoiceId);
    }

    @Test
    void create_new_invoice_no_invoices_exist() {
        //given
        Order orderWithProducts = new Order();
        orderWithProducts.setProducts(List.of(new Product()));

        Invoice expectedInvoice = Invoice.builder()
                .sequenceNo(1L)
                .fullSerialNo("SN-001")
                .order(orderWithProducts)
                .build();

        //when
        invoiceService.createInvoice(orderWithProducts);

        // then
        verify(invoiceRepository).save(invoiceCaptor.capture());

        Invoice actualInvoice = invoiceCaptor.getValue();
        assertInvoiceEquals(expectedInvoice, actualInvoice);
    }

    private void assertInvoiceEquals(Invoice expected, Invoice actual) {
        assertOrderEquals(actual.getOrder(), actual.getOrder());
        assertThat(actual.getSequenceNo()).isEqualTo(expected.getSequenceNo());
        assertThat(actual.getFullSerialNo()).isEqualTo(expected.getFullSerialNo());
        assertThat(actual.getCreatedAt()).isEqualTo(expected.getCreatedAt());
    }

    private void assertOrderEquals(@NotNull Order expected, @NotNull Order actual) {
        assertThat(actual.getProducts()).hasSize(expected.getProducts().size());
    }


}
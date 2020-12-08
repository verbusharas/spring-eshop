package lt.verbus.eshop.invoice.controller;

import lt.verbus.eshop.invoice.service.InvoiceService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/{id}")
    private String getOneInvoice(@PathVariable Long id, Model model) {
        model.addAttribute("invoice", invoiceService.findById(id));
        return "invoice/preview";
    }

    @GetMapping
    public String getAllInvoices(@PageableDefault(size=5) Pageable pageable, Model model){
        model.addAttribute("invoicePage", invoiceService.getAllInvoices(pageable));
        return "invoice/invoice-list";
    }

}

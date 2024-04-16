package nl.novi.finalAssignmentBackend.controllers;

import jakarta.persistence.Entity;
import nl.novi.finalAssignmentBackend.Service.InvoiceService;
import nl.novi.finalAssignmentBackend.dtos.invoice.InvoiceResponseDto;
import nl.novi.finalAssignmentBackend.mappers.InvoiceMapper.InvoiceDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/invoices")
@RestController
public class InvoiceController {

    private final InvoiceDtoMapper invoiceDtoMapper;
    private final InvoiceService invoiceService;


    public InvoiceController(InvoiceService invoiceService, InvoiceDtoMapper invoiceDtoMapper){
        this.invoiceService = invoiceService;
        this.invoiceDtoMapper = invoiceDtoMapper;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceResponseDto>>getAllInvoices(){
        var invoices = invoiceService.getInvoices();
        var invoiceDto = invoices.stream().map(invoiceDtoMapper::toInvoiceDto).collect(Collectors.toList());
        return new ResponseEntity<>(invoiceDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponseDto>getInvoiceById(@PathVariable Long id){
        var invoice = invoiceService.getInvoiceById(id);
        if(invoice == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        var invoiceDto = invoiceDtoMapper.toInvoiceDto(invoice);
        return new ResponseEntity<>(invoiceDto, HttpStatus.OK);
    }

    @PostMapping("/{invoiceId}/shopping_list/{shoppingListId}")
    public ResponseEntity<String>addShoppingListToInvoice(@PathVariable long invoiceId, @PathVariable Long shoppingListId){
        invoiceService.addShoppingListToInvoice(invoiceId,shoppingListId);
        return ResponseEntity.ok("the shopping list has been added to the invoice");
    }


}

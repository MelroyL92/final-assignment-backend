package nl.novi.finalAssignmentBackend.controllers;

import jakarta.servlet.http.HttpServletRequest;
import nl.novi.finalAssignmentBackend.Service.InvoiceService;
import nl.novi.finalAssignmentBackend.dtos.invoice.InvoiceInputDto;
import nl.novi.finalAssignmentBackend.dtos.invoice.InvoiceResponseDto;
import nl.novi.finalAssignmentBackend.helper.UrlHelper;
import nl.novi.finalAssignmentBackend.mappers.InvoiceMapper.InvoiceDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RequestMapping("/invoices")
@RestController
public class InvoiceController {

    private final InvoiceDtoMapper invoiceDtoMapper;
    private final InvoiceService invoiceService;

    private final HttpServletRequest request;

    public InvoiceController(InvoiceService invoiceService, InvoiceDtoMapper invoiceDtoMapper, HttpServletRequest request){
        this.invoiceService = invoiceService;
        this.invoiceDtoMapper = invoiceDtoMapper;
        this.request = request;
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

    @PostMapping("")
    public ResponseEntity<InvoiceResponseDto>createInvoice(@RequestBody InvoiceInputDto invoiceInputDto){
        var invoiceModel = invoiceDtoMapper.createInvoiceModel(invoiceInputDto);
        var newInvoice = invoiceService.createInvoice(invoiceModel);
        var invoiceDto = invoiceDtoMapper.toInvoiceDto(newInvoice);
        return ResponseEntity.created(UrlHelper.getCurrentURLWithId(request, invoiceDto.getOrderNumber())).body(invoiceDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceResponseDto>UpdateInvoice(@RequestParam Long id, @RequestBody InvoiceInputDto invoiceInputDto){
        var updateInvoice = invoiceService.updateInvoiceModel(id, invoiceDtoMapper.createInvoiceModel(invoiceInputDto));
        var invoiceDto = invoiceDtoMapper.toInvoiceDto(updateInvoice);
        return new ResponseEntity<>(invoiceDto, HttpStatus.OK);
    }

    @PutMapping("/{invoiceId}/shoppinglists/{shoppingListId}")
    public ResponseEntity<String>addShoppingListToInvoice(@PathVariable long invoiceId, @PathVariable Long shoppingListId){
        invoiceService.addShoppingListToInvoice(invoiceId,shoppingListId);
        return ResponseEntity.ok("the shopping list has been added to the invoice");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object>deleteInvoice(@PathVariable Long id){
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }



}

package nl.novi.finalAssignmentBackend.Service;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.finalAssignmentBackend.Repository.InvoiceRepository;
import nl.novi.finalAssignmentBackend.Repository.ShoppingListRepository;
import nl.novi.finalAssignmentBackend.entities.Invoice;
import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import nl.novi.finalAssignmentBackend.mappers.InvoiceMapper.InvoiceMapper;
import nl.novi.finalAssignmentBackend.model.InvoiceModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final ShoppingListRepository shoppingListRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper, ShoppingListRepository shoppingListRepository) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
        this.shoppingListRepository = shoppingListRepository;
    }


    public List<InvoiceModel> getInvoices(){
        return invoiceRepository.findAll().stream().map(invoiceMapper::fromEntity).collect(Collectors.toList());
    }

    public InvoiceModel getInvoiceById(Long id){
       Invoice invoice= invoiceRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Invoice not found with id " + id));
        return invoiceMapper.fromEntity(invoice);
    }

    public void addShoppingListToInvoice(Long invoiceId, Long shoppingListId){
        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(()-> new EntityNotFoundException("Invoice with id " + invoiceId + " not found"));
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId).orElseThrow(()-> new EntityNotFoundException("Shopping list with id " + shoppingListId + " not found"));

        invoice.getShoppingList().add(shoppingList);
        invoiceRepository.save(invoice);
    }
}

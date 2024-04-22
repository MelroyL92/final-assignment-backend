package nl.novi.finalAssignmentBackend.mappers.InvoiceMapper;

import nl.novi.finalAssignmentBackend.dtos.invoice.InvoiceInputDto;
import nl.novi.finalAssignmentBackend.dtos.invoice.InvoiceResponseDto;
import nl.novi.finalAssignmentBackend.mappers.GameMappers.GameDTOMapper;
import nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper.ShoppingListDTOMapper;
import nl.novi.finalAssignmentBackend.model.InvoiceModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InvoiceDtoMapper {

    private ShoppingListDTOMapper shoppingListDTOMapper;
    private final GameDTOMapper gameDTOMapper;


    public InvoiceDtoMapper(ShoppingListDTOMapper shoppingListDTOMapper, GameDTOMapper gameDTOMapper) {
        this.shoppingListDTOMapper = shoppingListDTOMapper;
        this.gameDTOMapper = gameDTOMapper;
    }

    public InvoiceResponseDto toInvoiceDto(InvoiceModel invoice) {
        return toInvoiceDto(invoice, new InvoiceResponseDto());

    }

    public <D extends InvoiceResponseDto> D toInvoiceDto(InvoiceModel invoice, D target){
        target.setDateOrdered(invoice.getDateOrdered());
        target.setDeliveryDate(invoice.getDeliveryDate());
        target.setStatus(invoice.getStatus());
        target.setOrderNumber(invoice.getOrderNumber());
        target.setOrderConfirmation(invoice.getOrderConfirmation());
        target.setShoppingList(invoice.getShoppingLists());
        target.setUser(invoice.getUser());
        target.setTotalPrice(invoice.getTotalPrice());
        target.setCreatePdf(invoice.isCreatePdf());
        return target;
    }

    public List<InvoiceResponseDto> toInvoicedtos(List<InvoiceModel>invoiceModels){
        List<InvoiceResponseDto> result = new ArrayList<>();
        for (InvoiceModel model: invoiceModels){
            result.add(toInvoiceDto(model));
        }
        return result;
    }


    public InvoiceModel createInvoiceModel(InvoiceInputDto dto){
        var invoice = new InvoiceModel();
        invoice.setDateOrdered(dto.getDateOrdered());
        invoice.setDeliveryDate(dto.getDeliveryDate());
        invoice.setStatus(dto.getStatus());
        invoice.setOrderConfirmation(dto.getOrderConfirmation());
        invoice.setOrderNumber(dto.getOrderNumber());
        invoice.setShoppingLists(dto.getShoppingList());
        invoice.setProfit(dto.getProfit());
        invoice.setUser(dto.getUser());
        invoice.setTotalPrice(dto.getProfit());
        invoice.setCreatePdf(dto.isCreatePdf());
        return invoice;
    }


}

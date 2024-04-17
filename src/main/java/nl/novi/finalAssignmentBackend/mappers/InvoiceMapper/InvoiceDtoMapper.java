package nl.novi.finalAssignmentBackend.mappers.InvoiceMapper;

import nl.novi.finalAssignmentBackend.dtos.invoice.InvoiceInputDto;
import nl.novi.finalAssignmentBackend.dtos.invoice.InvoiceResponseDto;
import nl.novi.finalAssignmentBackend.model.InvoiceModel;
import org.springframework.stereotype.Component;

@Component
public class InvoiceDtoMapper {



    public InvoiceResponseDto toInvoiceDto(InvoiceModel invoice) {
        return toInvoiceDto(invoice, new InvoiceResponseDto());

    }

    public <D extends InvoiceResponseDto> D toInvoiceDto(InvoiceModel invoice, D target){
        target.setDateOrdered(invoice.getDateOrdered());
        target.setDeliveryDate(invoice.getDeliveryDate());
        target.setStatus(invoice.getStatus());
        target.setOrderNumber(invoice.getOrderNumber());
        target.setOrderConfirmation(invoice.getOrderConfirmation());
        target.setShoppingList(invoice.getShoppingList());
        target.setProfit(invoice.getProfit());
        return target;
    }

    public InvoiceModel createInvoiceModel(InvoiceInputDto dto){
        var invoice = new InvoiceModel();
        invoice.setDateOrdered(dto.getDateOrdered());
        invoice.setDeliveryDate(dto.getDeliveryDate());
        invoice.setStatus(dto.getStatus());
        invoice.setOrderConfirmation(dto.getOrderConfirmation());
        invoice.setOrderNumber(dto.getOrderNumber());
        invoice.setShoppingList(dto.getShoppingList());
        invoice.setProfit(dto.getProfit());
        return invoice;
    }


}

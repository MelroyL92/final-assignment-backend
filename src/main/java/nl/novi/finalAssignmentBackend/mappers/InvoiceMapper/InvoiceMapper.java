package nl.novi.finalAssignmentBackend.mappers.InvoiceMapper;

import nl.novi.finalAssignmentBackend.entities.Invoice;
import nl.novi.finalAssignmentBackend.mappers.EntityMapper;
import nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper.ShoppingListMapper;
import nl.novi.finalAssignmentBackend.model.InvoiceModel;
import org.springframework.stereotype.Component;

@Component
public class InvoiceMapper implements EntityMapper<InvoiceModel, Invoice> {

    private final ShoppingListMapper shoppingListMapper;

    public InvoiceMapper(ShoppingListMapper shoppingListMapper) {
        this.shoppingListMapper = shoppingListMapper;
    }

    public InvoiceModel fromEntity(Invoice entity){
        if(entity == null){
            return null;
        }
        InvoiceModel model = new InvoiceModel();
        model.setOrderNumber(entity.getOrderNumber());
        model.setStatus(entity.getStatus());
        model.setOrderConfirmation(entity.getOrderConfirmation());
        model.setDeliveryDate(entity.getDeliveryDate());
        model.setDateOrdered(entity.getDateOrdered());
        model.setShoppingLists(shoppingListMapper.fromEntity(entity.getShoppingList()));
        model.setProfit(entity.getProfit());
        model.setUser(entity.getUser());
        model.setTotalPrice(entity.getTotalPrice());
        model.setCreatePdf(entity.isCreatePdf());
        return model;
    }


    public Invoice toEntity(InvoiceModel model){
        if (model == null){
            return  null;
        }

        Invoice entity = new Invoice();
        entity.setDateOrdered(model.getDateOrdered());
        entity.setDeliveryDate(model.getDeliveryDate());
        entity.setStatus(model.getStatus());
        entity.setOrderConfirmation(model.getOrderConfirmation());
        entity.setOrderNumber(model.getOrderNumber());
        entity.setShoppingList(shoppingListMapper.toEntity(model.getShoppingLists()));
        entity.setProfit(model.getProfit());
        entity.setUser(model.getUser());
        entity.setTotalPrice(model.getTotalPrice());
        entity.setCreatePdf(model.isCreatePdf());
        return entity;
    }
}

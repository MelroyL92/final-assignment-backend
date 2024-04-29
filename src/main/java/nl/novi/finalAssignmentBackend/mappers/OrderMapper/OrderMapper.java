package nl.novi.finalAssignmentBackend.mappers.OrderMapper;

import nl.novi.finalAssignmentBackend.entities.Order;
import nl.novi.finalAssignmentBackend.mappers.EntityMapper;
import nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper.ShoppingListMapper;
import nl.novi.finalAssignmentBackend.model.OrderModel;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements EntityMapper<OrderModel, Order> {

    private final ShoppingListMapper shoppingListMapper;

    public OrderMapper(ShoppingListMapper shoppingListMapper) {
        this.shoppingListMapper = shoppingListMapper;
    }

    public OrderModel fromEntity(Order entity){
        if(entity == null){
            return null;
        }
        OrderModel model = new OrderModel();
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


    public Order toEntity(OrderModel model){
        if (model == null){
            return  null;
        }

        Order entity = new Order();
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

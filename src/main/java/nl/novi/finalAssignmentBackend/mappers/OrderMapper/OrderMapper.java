package nl.novi.finalAssignmentBackend.mappers.OrderMapper;

import nl.novi.finalAssignmentBackend.entities.Order;
import nl.novi.finalAssignmentBackend.mappers.EntityMapper;
import nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper.ShoppingListMapper;
import nl.novi.finalAssignmentBackend.mappers.UserMappers.UserMapper;
import nl.novi.finalAssignmentBackend.model.OrderModel;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements EntityMapper<OrderModel, Order> {

    private final ShoppingListMapper shoppingListMapper;
    private final UserMapper usermapper;

    public OrderMapper(ShoppingListMapper shoppingListMapper, UserMapper usermapper) {
        this.shoppingListMapper = shoppingListMapper;
        this.usermapper = usermapper;
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
        model.setUserModel(usermapper.fromEntity(entity.getUser()));
        model.setTotalPrice(entity.getTotalPrice());
        model.setCreatePdf(entity.getCreatePdf());
        model.setHasPaid(entity.getHasPaid());
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
        entity.setUser(usermapper.toEntity(model.getUserModel()));
        entity.setTotalPrice(model.getTotalPrice());
        entity.setCreatePdf(model.getCreatePdf());
        entity.setHasPaid(model.getHasPaid());
        return entity;
    }
}

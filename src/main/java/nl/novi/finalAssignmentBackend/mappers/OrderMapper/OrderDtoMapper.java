package nl.novi.finalAssignmentBackend.mappers.OrderMapper;

import nl.novi.finalAssignmentBackend.dtos.order.OrderInputDto;
import nl.novi.finalAssignmentBackend.dtos.order.OrderResponseDto;
import nl.novi.finalAssignmentBackend.mappers.GameMappers.GameDTOMapper;
import nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper.ShoppingListDTOMapper;
import nl.novi.finalAssignmentBackend.model.OrderModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDtoMapper {

    // not used? if so then remove it!
    private final ShoppingListDTOMapper shoppingListDTOMapper;
    private final GameDTOMapper gameDTOMapper;


    public OrderDtoMapper(ShoppingListDTOMapper shoppingListDTOMapper, GameDTOMapper gameDTOMapper) {
        this.shoppingListDTOMapper = shoppingListDTOMapper;
        this.gameDTOMapper = gameDTOMapper;
    }

    public OrderResponseDto toOrderDto(OrderModel order) {
        return toOrderDto(order, new OrderResponseDto());

    }

    public <D extends OrderResponseDto> D toOrderDto(OrderModel order, D target){
        target.setDateOrdered(order.getDateOrdered());
        target.setDeliveryDate(order.getDeliveryDate());
        target.setStatus(order.getStatus());
        target.setOrderNumber(order.getOrderNumber());
        target.setOrderConfirmation(order.getOrderConfirmation());
        target.setShoppingList(order.getShoppingLists());
        target.setUser(order.getUser());
        target.setTotalPrice(order.getTotalPrice());
        target.setCreatePdf(order.isCreatePdf());
        return target;
    }

    public List<OrderResponseDto> toOrderDtos(List<OrderModel>orderModels){
        List<OrderResponseDto> result = new ArrayList<>();
        for (OrderModel model: orderModels){
            result.add(toOrderDto(model));
        }
        return result;
    }


    public OrderModel createOrderModel(OrderInputDto dto){
        var order = new OrderModel();
        order.setDateOrdered(dto.getDateOrdered());
        order.setDeliveryDate(dto.getDeliveryDate());
        order.setStatus(dto.getStatus());
        order.setOrderConfirmation(dto.getOrderConfirmation());
        order.setOrderNumber(dto.getOrderNumber());
        order.setShoppingLists(dto.getShoppingList());
        order.setProfit(dto.getProfit());
        order.setUser(dto.getUser());
        order.setTotalPrice(dto.getProfit());
        order.setCreatePdf(dto.isCreatePdf());
        return order;
    }


}

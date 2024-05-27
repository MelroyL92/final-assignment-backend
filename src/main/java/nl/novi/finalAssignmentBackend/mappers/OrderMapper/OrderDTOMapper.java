package nl.novi.finalAssignmentBackend.mappers.OrderMapper;

import nl.novi.finalAssignmentBackend.dtos.order.OrderInputDTO;
import nl.novi.finalAssignmentBackend.dtos.order.OrderResponseDTO;
import nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper.ShoppingListDTOMapper;
import nl.novi.finalAssignmentBackend.mappers.UserMappers.UserDtoMapper;
import nl.novi.finalAssignmentBackend.model.OrderModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class OrderDTOMapper {


    private final UserDtoMapper userDtoMapper;
    private final ShoppingListDTOMapper shoppingListDTOMapper;




    public OrderDTOMapper(UserDtoMapper userDtoMapper, ShoppingListDTOMapper shoppingListDTOMapper) {
        this.userDtoMapper = userDtoMapper;
        this.shoppingListDTOMapper = shoppingListDTOMapper;
    }




    public OrderResponseDTO toOrderDTO(OrderModel order) {
        return toOrderDTO(order, new OrderResponseDTO());

    }

    public <D extends OrderResponseDTO> D toOrderDTO(OrderModel order, D target){
        target.setDateOrdered(order.getDateOrdered());
        target.setDeliveryDate(order.getDeliveryDate());
        target.setStatus(order.getStatus());
        target.setOrderNumber(order.getOrderNumber());
        target.setOrderConfirmation(order.getOrderConfirmation());
        target.setShoppingList(shoppingListDTOMapper.toShoppingListDTOs(order.getShoppingLists()));
        target.setUser(userDtoMapper.toUserDTO(order.getUserModel()));
        target.setTotalPrice(order.getTotalPrice());
        target.setCreatePdf(order.getCreatePdf());
        target.setHasPaid(order.getHasPaid());
        return target;
    }

    public List<OrderResponseDTO> toOrderDTOs(List<OrderModel>orderModels){
        List<OrderResponseDTO> result = new ArrayList<>();
        for (OrderModel model: orderModels){
            result.add(toOrderDTO(model));
        }
        return result;
    }


    public OrderModel createOrderModel(OrderInputDTO dto){
        var order = new OrderModel();
        order.setDateOrdered(dto.getDateOrdered());
        order.setDeliveryDate(dto.getDeliveryDate());
        order.setStatus(dto.getStatus());
        order.setOrderConfirmation(dto.getOrderConfirmation());
        order.setOrderNumber(dto.getOrderNumber());
        order.setShoppingLists(dto.getShoppingList());
        order.setProfit(dto.getProfit());
        order.setTotalPrice(dto.getProfit());
        order.setCreatePdf(dto.getCreatePdf());
        order.setHasPaid(dto.getHasPaid());
        return order;
    }


}

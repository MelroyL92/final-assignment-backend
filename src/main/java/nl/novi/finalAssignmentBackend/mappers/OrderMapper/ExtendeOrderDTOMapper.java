package nl.novi.finalAssignmentBackend.mappers.OrderMapper;

import nl.novi.finalAssignmentBackend.dtos.order.ExtendedOrderResponseDTO;
import nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper.ShoppingListDTOMapper;
import nl.novi.finalAssignmentBackend.mappers.UserMappers.UserDtoMapper;
import nl.novi.finalAssignmentBackend.model.OrderModel;
import org.springframework.stereotype.Component;

@Component
public class ExtendeOrderDTOMapper extends OrderDTOMapper{

    public ExtendeOrderDTOMapper(UserDtoMapper userDtoMapper, ShoppingListDTOMapper shoppingListDTOMapper) {
        super(userDtoMapper, shoppingListDTOMapper);
    }

    public ExtendedOrderResponseDTO toExtendedOrderDTO(OrderModel order){
        return toExtendedOrderDTO(order, new ExtendedOrderResponseDTO());
    }

    public<D extends ExtendedOrderResponseDTO > D toExtendedOrderDTO(OrderModel order, D target){
        target = super.toOrderDTO(order, target);
        target.setProfit(order.getProfit());
        return target;
    }


}

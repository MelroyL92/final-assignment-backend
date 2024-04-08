package nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper;

import nl.novi.finalAssignmentBackend.dtos.ShoppingList.ShoppingListInputDto;
import nl.novi.finalAssignmentBackend.dtos.ShoppingList.ShoppingListResponseDto;
import nl.novi.finalAssignmentBackend.model.ShoppingListModel;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListDTOMapper {



    public ShoppingListResponseDto toShoppingListDto(ShoppingListModel shoppingList){
        return toShoppingListDto(shoppingList, new ShoppingListResponseDto());
    }

    public <D extends ShoppingListResponseDto> D toShoppingListDto(ShoppingListModel shoppinglist, D target) {
        target.setId(shoppinglist.getId());
        target.setSubtotal(shoppinglist.getSubtotal());
        target.setType(shoppinglist.getType());
        return target;
    }


    public ShoppingListModel createShoppingListModel(ShoppingListInputDto dto) {
        var shoppingList = new ShoppingListModel();
        shoppingList.setId(dto.getId());
        shoppingList.setType(dto.getType());
        shoppingList.setSubtotal(dto.getSubtotal());
        return shoppingList;
    }
}


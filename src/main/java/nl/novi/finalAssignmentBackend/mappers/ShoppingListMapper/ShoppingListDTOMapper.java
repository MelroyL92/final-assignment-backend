package nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper;

import nl.novi.finalAssignmentBackend.dtos.ShoppingList.ShoppingListInputDto;
import nl.novi.finalAssignmentBackend.dtos.ShoppingList.ShoppingListResponseDto;
import nl.novi.finalAssignmentBackend.model.ShoppingListModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ShoppingListDTOMapper {


    public ShoppingListResponseDto toShoppingListDto(ShoppingListModel shoppingList) {
        return toShoppingListDto(shoppingList, new ShoppingListResponseDto());
    }

    public <D extends ShoppingListResponseDto> D toShoppingListDto(ShoppingListModel shoppingList, D target) {
        target.setId(shoppingList.getId());
        target.setSubtotal(shoppingList.getSubtotal());
        target.setType(shoppingList.getType());
        target.setGames(shoppingList.getGames());
        target.setMovies(shoppingList.getMovies());
        target.setDeliveryCost(shoppingList.getDeliverCost());
        target.setPackaging(shoppingList.getPackaging());
        target.setAtHomeDelivery(shoppingList.getAtHomeDelivery());
        target.setPackagingCost(shoppingList.getPackagingCost());
        target.setUser(shoppingList.getUser());
        return target;
    }

    // Is being used or not? removing it did give a error, maybe its just not directly being used?
    public List<ShoppingListResponseDto> toGameDTOs(List<ShoppingListModel> shoppingListModels) {
        List<ShoppingListResponseDto> result = new ArrayList<>();
        for (ShoppingListModel shoppingListModel : shoppingListModels) {
            result.add(toShoppingListDto(shoppingListModel));
        }
        return result;
    }


    public ShoppingListModel createShoppingListModel(ShoppingListInputDto dto) {
        var shoppingList = new ShoppingListModel();
        shoppingList.setId(dto.getId());
        shoppingList.setType(dto.getType());
        shoppingList.setSubtotal(dto.getSubtotal());
        shoppingList.setMovies(dto.getMovies());
        shoppingList.setGames(dto.getGames());
        shoppingList.setDeliverCost(dto.getDeliveryCost());
        shoppingList.setPackaging(dto.getPackaging());
        shoppingList.setAtHomeDelivery(dto.getAtHomeDelivery());
        shoppingList.setPackagingCost(dto.getPackagingCost());
        shoppingList.setUser(dto.getUser());
        return shoppingList;
    }
}



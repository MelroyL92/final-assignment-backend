package nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper;

import nl.novi.finalAssignmentBackend.dtos.ShoppingList.ShoppingListInputDto;
import nl.novi.finalAssignmentBackend.dtos.ShoppingList.ShoppingListResponseDto;
import nl.novi.finalAssignmentBackend.mappers.GameMappers.GameDTOMapper;
import nl.novi.finalAssignmentBackend.model.ShoppingListModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ShoppingListDTOMapper {

    private final GameDTOMapper gameDTOMapper;

    public ShoppingListDTOMapper(GameDTOMapper gameDTOMapper) {
        this.gameDTOMapper = gameDTOMapper;
    }

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
        return target;
    }

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
        return shoppingList;
    }
}



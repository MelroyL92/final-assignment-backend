package nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper;

import nl.novi.finalAssignmentBackend.dtos.ShoppingList.ShoppingListInputDto;
import nl.novi.finalAssignmentBackend.dtos.ShoppingList.ShoppingListResponseDto;
import nl.novi.finalAssignmentBackend.mappers.GameMappers.GameDTOMapper;
import nl.novi.finalAssignmentBackend.mappers.MovieMappers.MovieDTOMapper;
import nl.novi.finalAssignmentBackend.model.ShoppingListModel;
import org.springframework.stereotype.Component;



@Component
public class ShoppingListDTOMapper {

    private GameDTOMapper gameDTOMapper;
    private MovieDTOMapper movieDTOMapper;

    public ShoppingListResponseDto toShoppingListDto(ShoppingListModel shoppingList){
        return toShoppingListDto(shoppingList, new ShoppingListResponseDto());
    }

    public <D extends ShoppingListResponseDto> D toShoppingListDto(ShoppingListModel shoppingList, D target) {
        target.setId(shoppingList.getId());
        target.setSubtotal(shoppingList.getSubtotal());
        target.setType(shoppingList.getType());
        target.setGames(shoppingList.getGames());
        target.setMovies(shoppingList.getMovies());
        return target;
    }


    public ShoppingListModel createShoppingListModel(ShoppingListInputDto dto) {
        var shoppingList = new ShoppingListModel();
        shoppingList.setId(dto.getId());
        shoppingList.setType(dto.getType());
        shoppingList.setSubtotal(dto.getSubtotal());
        shoppingList.setMovies(dto.getMovies());
        shoppingList.setGames(dto.getGames());
        return shoppingList;
    }
}


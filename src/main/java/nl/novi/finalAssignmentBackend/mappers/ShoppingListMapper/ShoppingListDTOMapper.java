package nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper;

import nl.novi.finalAssignmentBackend.dtos.shoppingList.ShoppingListInputDto;
import nl.novi.finalAssignmentBackend.dtos.shoppingList.ShoppingListResponseDto;
import nl.novi.finalAssignmentBackend.mappers.GameMappers.GameDTOMapper;
import nl.novi.finalAssignmentBackend.mappers.MovieMappers.MovieDTOMapper;
import nl.novi.finalAssignmentBackend.mappers.UserMappers.UserDtoMapper;
import nl.novi.finalAssignmentBackend.model.ShoppingListModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ShoppingListDTOMapper {

    private final GameDTOMapper gameDTOMapper;
    private final MovieDTOMapper movieDTOMapper;
    private final UserDtoMapper userDtoMapper;

    public ShoppingListDTOMapper(GameDTOMapper gameDTOMapper, MovieDTOMapper movieDTOMapper, UserDtoMapper userDtoMapper) {
        this.gameDTOMapper = gameDTOMapper;
        this.movieDTOMapper = movieDTOMapper;
        this.userDtoMapper = userDtoMapper;
    }


    public ShoppingListResponseDto toShoppingListDto(ShoppingListModel shoppingList) {
        return toShoppingListDto(shoppingList, new ShoppingListResponseDto());
    }

    public <D extends ShoppingListResponseDto> D toShoppingListDto(ShoppingListModel shoppingList, D target) {
        target.setId(shoppingList.getId());
        target.setSubtotal(shoppingList.getSubtotal());
        target.setType(shoppingList.getType());
        target.setGames(gameDTOMapper.toGameDTOs(shoppingList.getGames()));
        target.setMovies(movieDTOMapper.toMovieDTOs(shoppingList.getMovies()));
        target.setDeliveryCost(shoppingList.getDeliverCost());
        target.setPackaging(shoppingList.getPackaging());
        target.setAtHomeDelivery(shoppingList.getAtHomeDelivery());
        target.setPackagingCost(shoppingList.getPackagingCost());
        target.setUser(userDtoMapper.toUserDTO(shoppingList.getUserModel()));
        target.setCreatePdf(shoppingList.getCreatePdf());
        return target;
    }

    // Is being used or not? removing it did give a error, maybe its just not directly being used?
    public List<ShoppingListResponseDto> toShoppingListDTOs(List<ShoppingListModel> shoppingListModels) {
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
        shoppingList.setCreatePdf(dto.getCreatePdf());
        return shoppingList;
    }
}



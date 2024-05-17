package nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper;

import nl.novi.finalAssignmentBackend.dtos.shoppingList.ShoppingListInputDTO;
import nl.novi.finalAssignmentBackend.dtos.shoppingList.ShoppingListResponseDTO;
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


    public ShoppingListResponseDTO toShoppingListDto(ShoppingListModel shoppingList) {
        return toShoppingListDto(shoppingList, new ShoppingListResponseDTO());
    }

    public <D extends ShoppingListResponseDTO> D toShoppingListDto(ShoppingListModel shoppingList, D target) {
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

    public List<ShoppingListResponseDTO> toShoppingListDTOs(List<ShoppingListModel> shoppingListModels) {
        List<ShoppingListResponseDTO> result = new ArrayList<>();
        for (ShoppingListModel shoppingListModel : shoppingListModels) {
            result.add(toShoppingListDto(shoppingListModel));
        }
        return result;
    }



    public ShoppingListModel createShoppingListModel(ShoppingListInputDTO dto) {
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



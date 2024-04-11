package nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper;


import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import nl.novi.finalAssignmentBackend.mappers.EntityMapper;
import nl.novi.finalAssignmentBackend.mappers.GameMappers.GameMapper;
import nl.novi.finalAssignmentBackend.mappers.MovieMappers.MovieMapper;
import nl.novi.finalAssignmentBackend.model.ShoppingListModel;
import org.springframework.stereotype.Component;


@Component
public class ShoppingListMapper implements EntityMapper<ShoppingListModel, ShoppingList> {


    private GameMapper gameMapper;
    private MovieMapper movieMapper;

    // I should probably add  something for the movie/game here? But perhaps also just in the service, because i want to keep this simple...
    //but then should i do: model.setGames(shoppingList.getGames())? and then do the rest of the logic in the service? something to think about
    @Override
    public ShoppingListModel fromEntity(ShoppingList shoppingList) {
       if(shoppingList == null) {
           return null;

       }
       ShoppingListModel model = new ShoppingListModel();
       model.setSubtotal(shoppingList.getSubtotal());
       model.setId(shoppingList.getId());
       model.setType(shoppingList.getType());
       model.setGames(shoppingList.getGames());
       model.setMovies(shoppingList.getMovies());

       return model;
    }

    @Override
    public ShoppingList toEntity(ShoppingListModel shoppingListModel) {
        if (shoppingListModel == null) {
            return null;
        }
        ShoppingList entity = new ShoppingList();
        entity.setSubtotal(shoppingListModel.getSubtotal());
        entity.setId(shoppingListModel.getId());
        entity.setType(shoppingListModel.getType());
        entity.setGames(shoppingListModel.getGames());
        entity.setMovies(shoppingListModel.getMovies());
        return entity;
    }
}

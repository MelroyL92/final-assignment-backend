package nl.novi.finalAssignmentBackend.Service;


import jakarta.persistence.EntityNotFoundException;
import nl.novi.finalAssignmentBackend.Repository.GameRepository;
import nl.novi.finalAssignmentBackend.Repository.MovieRepository;
import nl.novi.finalAssignmentBackend.Repository.ShoppingListRepository;
import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.entities.Movie;
import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import nl.novi.finalAssignmentBackend.mappers.GameMappers.GameMapper;
import nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper.ShoppingListMapper;
import nl.novi.finalAssignmentBackend.model.GameModel;
import nl.novi.finalAssignmentBackend.model.ShoppingListModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingListService {

    private final ShoppingListMapper shoppingListMapper;
    private final ShoppingListRepository shoppingListRepository;
    private final GameMapper gameMapper;
    private final GameRepository gameRepository;
    private final MovieRepository movieRepository;


    public ShoppingListService(ShoppingListMapper shoppingListMapper, ShoppingListRepository shoppingListRepository, GameMapper gameMapper, GameRepository gameRepository, MovieRepository movieRepository) {
        this.shoppingListMapper = shoppingListMapper;
        this.shoppingListRepository = shoppingListRepository;
        this.gameMapper = gameMapper;
        this.gameRepository = gameRepository;
        this.movieRepository = movieRepository;
    }


    public List<ShoppingListModel> getShoppingList(){
        return shoppingListRepository.findAll().stream().map(shoppingListMapper::fromEntity).collect(Collectors.toList());
    }

    public ShoppingListModel getShoppingListById(Long id){
        ShoppingList shoppingList = shoppingListRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Shoppinglist not found with id: " + id));
        return shoppingListMapper.fromEntity(shoppingList);
    }

    public ShoppingListModel createShoppingList(ShoppingListModel shoppingListModel){
        ShoppingList shoppingList = shoppingListMapper.toEntity(shoppingListModel);
        shoppingList = shoppingListRepository.save(shoppingList);
        return shoppingListMapper.fromEntity(shoppingList);
    }



    public void updateSubtotal(Long shoppingListId){
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping list not found"));

        Integer subtotal = 0;

        for (Game game : shoppingList.getGames()){
            subtotal += game.getSellingPrice();
        }

        for (Movie movie: shoppingList.getMovies()){
            subtotal += movie.getSellingPrice();
        }

        shoppingList.setSubtotal(subtotal);
        shoppingListRepository.save(shoppingList);
    }


    // the entire game gets saved because thats what we need to be able to acces
    // but i do need to make sure that when a client gets acces to it it goes through the dto
    public void addGameToShoppingList(Long shoppingListId, Long gameId) {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping list not found"));

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("Game not found"));

        shoppingList.getGames().add(game);

        shoppingListRepository.save(shoppingList);
        updateSubtotal(shoppingListId);
    }

    public void addMovieToShoppingList(Long shoppingListId, Long movieId){
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping list not found"));

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));


        shoppingList.getMovies().add(movie);
        shoppingListRepository.save(shoppingList);
        updateSubtotal(shoppingListId);
    }




}











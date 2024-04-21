package nl.novi.finalAssignmentBackend.Service;


import jakarta.persistence.EntityNotFoundException;
import nl.novi.finalAssignmentBackend.Repository.GameRepository;
import nl.novi.finalAssignmentBackend.Repository.MovieRepository;
import nl.novi.finalAssignmentBackend.Repository.ShoppingListRepository;
import nl.novi.finalAssignmentBackend.dtos.game.GameResponseDto;
import nl.novi.finalAssignmentBackend.dtos.movie.MovieResponseDto;
import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.entities.Movie;
import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import nl.novi.finalAssignmentBackend.exceptions.RecordNotFoundException;
import nl.novi.finalAssignmentBackend.helper.ShoppingListHelpers;
import nl.novi.finalAssignmentBackend.mappers.GameMappers.GameDTOMapper;
import nl.novi.finalAssignmentBackend.mappers.GameMappers.GameMapper;
import nl.novi.finalAssignmentBackend.mappers.MovieMappers.MovieDTOMapper;
import nl.novi.finalAssignmentBackend.mappers.MovieMappers.MovieMapper;
import nl.novi.finalAssignmentBackend.mappers.ShoppingListMapper.ShoppingListMapper;
import nl.novi.finalAssignmentBackend.model.ShoppingListModel;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingListService {

    private final ShoppingListMapper shoppingListMapper;
    private final ShoppingListRepository shoppingListRepository;
    private final GameRepository gameRepository;
    private final MovieRepository movieRepository;
    private final ShoppingListHelpers shoppingListHelpers;
    private final GameDTOMapper gameDTOMapper;
    private final GameMapper gameMapper;
    private final MovieDTOMapper movieDTOMapper;
    private final MovieMapper movieMapper;

    public ShoppingListService(ShoppingListMapper shoppingListMapper, ShoppingListRepository shoppingListRepository, GameRepository gameRepository, MovieRepository movieRepository,
                               ShoppingListHelpers shoppingListHelpers, GameDTOMapper gameDTOMapper, GameMapper gameMapper, MovieMapper movieMapper, MovieDTOMapper movieDTOMapper) {
        this.shoppingListMapper = shoppingListMapper;
        this.shoppingListRepository = shoppingListRepository;
        this.gameRepository = gameRepository;
        this.movieRepository = movieRepository;
        this.shoppingListHelpers = shoppingListHelpers;
        this.gameDTOMapper = gameDTOMapper;
        this.gameMapper = gameMapper;
        this.movieMapper = movieMapper;
        this.movieDTOMapper = movieDTOMapper;
    }


    public List<ShoppingListModel> getShoppingList() {
        return shoppingListRepository.findAll().stream().map(shoppingListMapper::fromEntity).collect(Collectors.toList());
    }

    public ShoppingListModel getShoppingListById(Long id) {
        ShoppingList shoppingList = shoppingListRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Shoppinglist not found with id: " + id));
        return shoppingListMapper.fromEntity(shoppingList);
    }

    public ShoppingListModel createShoppingList(ShoppingListModel shoppingListModel) {
        ShoppingList shoppingList = shoppingListMapper.toEntity(shoppingListModel);
        shoppingList = shoppingListRepository.save(shoppingList);
        return shoppingListMapper.fromEntity(shoppingList);
    }

    public List<GameResponseDto> getGameFromShoppingList(Long gameId, Long shoppingListId) {
        Optional<ShoppingList> shoppingLists = shoppingListRepository.findById(shoppingListId);
        if (shoppingLists.isEmpty()) {
            throw new RecordNotFoundException("The requested game within the list does not exist");
        } else {
            var gamesInList = shoppingLists.get().getGames();
            Optional<Game> gameOptional = gamesInList.stream()
                    .filter(game -> game.getId().equals(gameId))
                    .findFirst();
            if (gameOptional.isPresent()) {
                Game game = gameOptional.get();
                var toGameModel = gameMapper.fromEntity(game);
                return Collections.singletonList(gameDTOMapper.toGameDto(toGameModel));
            } else {
                throw new RecordNotFoundException("Game not found in the shopping list");
            }
        }
    }

    public List<MovieResponseDto> getMovieFromShoppingList(Long movieId, Long shoppingListId) {
        Optional<ShoppingList> shoppingLists = shoppingListRepository.findById(shoppingListId);
        if (shoppingLists.isEmpty()) {
            throw new RecordNotFoundException("The requested movie within the list does not exist");
        } else {
            var movieInList = shoppingLists.get().getMovies();
            Optional<Movie> optionalMovie = movieInList.stream()
                    .filter(movie -> movie.getId().equals(movieId))
                    .findFirst();
            if (optionalMovie.isPresent()) {
                Movie movie = optionalMovie.get();
                var toMoviemodel = movieMapper.fromEntity(movie);
                return Collections.singletonList(movieDTOMapper.toMovieDto(toMoviemodel));
            } else {
                throw new RecordNotFoundException("movie has not been found within the shopping list");
            }
        }
    }

    public ShoppingListModel updateShoppingList(Long id, ShoppingListModel shoppingListModel) {
        Optional<ShoppingList> shoppingListFound = shoppingListRepository.findById(id);
        if (shoppingListFound.isPresent()) {
            ShoppingList excistingShoppingList = shoppingListFound.get();
            excistingShoppingList.setAtHomeDelivery(shoppingListModel.getAtHomeDelivery());
            excistingShoppingList.setType(shoppingListModel.getType());
            excistingShoppingList.setPackaging(excistingShoppingList.getPackaging());
            excistingShoppingList = shoppingListRepository.save(excistingShoppingList);
            return shoppingListMapper.fromEntity(excistingShoppingList);
        } else {
            throw new RecordNotFoundException("shopping list with id " + id + " not found");
        }

    }

//     the entire game gets saved because thats what we need to be able to acces
//     but i do need to make sure that when a client gets acces to it it goes through the dto
    public void addGameToShoppingList(Long shoppingListId, Long gameId) {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping list not found"));

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("Game not found"));

        shoppingList.getGames().add(game);

        shoppingListHelpers.updateSubtotal(shoppingListId);
        shoppingListHelpers.calculateDeliveryCost(shoppingListId);
        shoppingListHelpers.calculatePackagingCost(shoppingListId);
        shoppingListRepository.save(shoppingList);
    }

    public void addMovieToShoppingList(Long shoppingListId, Long movieId) {
        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping list not found"));

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));

        shoppingList.getMovies().add(movie);


        shoppingListHelpers.updateSubtotal(shoppingListId);
        shoppingListHelpers.calculateDeliveryCost(shoppingListId);
        shoppingListHelpers.calculatePackagingCost(shoppingListId);
        shoppingListRepository.save(shoppingList);
    }



    public void deleteShoppingList(Long id) {
        shoppingListRepository.deleteById(id);
    }

    public void deleteGameWithinShoppingList(Long gameId, Long shoppingListId) {

        Optional<ShoppingList> optionalShoppingList = shoppingListRepository.findById(shoppingListId);
        if (optionalShoppingList.isEmpty()) {
            throw new RecordNotFoundException("shoppingList with id " + shoppingListId + " does not exist");
        }

        ShoppingList shoppingList = optionalShoppingList.get();
        List<Game> gameInList = shoppingList.getGames();

        Optional<Game> optionalGame = gameInList.stream().filter(game -> game.getId().equals(gameId)).findFirst();

        if (optionalGame.isPresent()) {
            Game gameToDelete = optionalGame.get();
            gameInList.remove(gameToDelete);
            shoppingListRepository.save(shoppingList);
        } else {
            throw new RecordNotFoundException("the game you requested to delete with id " + gameId + " does not exist");
        }
    }
    
    public void deleteMovieWithinShoppingList(Long movieId, Long shoppingListId) {
        Optional<ShoppingList> optionalShoppingList = shoppingListRepository.findById(shoppingListId);
        if (optionalShoppingList.isEmpty()) {
            throw new RecordNotFoundException("shoppingList with id " + shoppingListId + " does not exist");
        }

        ShoppingList shoppingList = optionalShoppingList.get();
        List<Movie> movieInList = shoppingList.getMovies();

        Optional<Movie> optionalMovie = movieInList.stream().filter(movie -> movie.getId().equals(movieId)).findFirst();

        if (optionalMovie.isPresent()) {
            Movie movieToDelete = optionalMovie.get();
            movieInList.remove(movieToDelete);
            shoppingListRepository.save(shoppingList);
        } else {
            throw new RecordNotFoundException("the movie you requested to delete with id " + movieId + " does not exist");
        }
    }
}











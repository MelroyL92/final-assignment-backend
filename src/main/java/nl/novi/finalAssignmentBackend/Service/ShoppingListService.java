package nl.novi.finalAssignmentBackend.Service;


import jakarta.persistence.EntityNotFoundException;
import nl.novi.finalAssignmentBackend.Repository.GameRepository;
import nl.novi.finalAssignmentBackend.Repository.MovieRepository;
import nl.novi.finalAssignmentBackend.Repository.ShoppingListRepository;
import nl.novi.finalAssignmentBackend.Repository.UserRepository;
import nl.novi.finalAssignmentBackend.dtos.game.GameResponseDTO;
import nl.novi.finalAssignmentBackend.dtos.movie.MovieResponseDTO;
import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.entities.Movie;
import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import nl.novi.finalAssignmentBackend.entities.User;
import nl.novi.finalAssignmentBackend.exceptions.NoUserAssignedException;
import nl.novi.finalAssignmentBackend.exceptions.RecordNotFoundException;
import nl.novi.finalAssignmentBackend.helper.LoggedInCheck;
import nl.novi.finalAssignmentBackend.helper.OrderConfirmationHelper;
import nl.novi.finalAssignmentBackend.helper.PDFCreator.PdfFileWishList;
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
    private final LoggedInCheck loggedInCheck;
    private final GameDTOMapper gameDTOMapper;
    private final GameMapper gameMapper;
    private final MovieDTOMapper movieDTOMapper;
    private final MovieMapper movieMapper;
    private final OrderConfirmationHelper orderConfirmationHelper;
    private final PdfFileWishList pdfFileWishList;
    private final UserRepository userRepository;



    public ShoppingListService(ShoppingListMapper shoppingListMapper, ShoppingListRepository shoppingListRepository, GameRepository gameRepository, MovieRepository movieRepository
                              , ShoppingListHelpers shoppingListHelpers, LoggedInCheck loggedInCheck, GameDTOMapper gameDTOMapper, GameMapper gameMapper, MovieMapper movieMapper, MovieDTOMapper movieDTOMapper, OrderConfirmationHelper orderConfirmationHelper, PdfFileWishList pdfFileWishList, UserRepository userRepository) {
        this.shoppingListMapper = shoppingListMapper;
        this.shoppingListRepository = shoppingListRepository;
        this.gameRepository = gameRepository;
        this.movieRepository = movieRepository;
        this.shoppingListHelpers = shoppingListHelpers;
        this.loggedInCheck = loggedInCheck;
        this.gameDTOMapper = gameDTOMapper;
        this.gameMapper = gameMapper;
        this.movieMapper = movieMapper;
        this.movieDTOMapper = movieDTOMapper;
        this.orderConfirmationHelper = orderConfirmationHelper;
        this.pdfFileWishList = pdfFileWishList;
        this.userRepository = userRepository;
    }

    private void extracted(Long shoppingListId, ShoppingList shoppingList) {
        shoppingListHelpers.updateSubtotal(shoppingListId);
        shoppingListHelpers.calculateDeliveryCost(shoppingListId);
        shoppingListHelpers.calculatePackagingCost(shoppingListId);
        shoppingListRepository.save(shoppingList);
    }

    public List<ShoppingListModel> getAllShoppingLists() {
        List<ShoppingList> shoppingLists = shoppingListRepository.findAll();

        List<ShoppingList> shoppingListsWithUsers = shoppingLists.stream()
                .filter(shoppingList -> shoppingList.getUser() != null)
                .collect(Collectors.toList());

        if (shoppingListsWithUsers.isEmpty()) {
            throw new EntityNotFoundException("No shopping lists with users found.");
        }

        return shoppingListsWithUsers.stream()
                .map(shoppingListMapper::fromEntity)
                .collect(Collectors.toList());
    }

    public ShoppingListModel getShoppingListById(Long id, String username) {

        loggedInCheck.verifyLoggedInUser(username);

        ShoppingList shoppingList = shoppingListRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Shoppinglist not found with id: " + id));
        if(shoppingList.getUser() == null){
            throw new EntityNotFoundException("No user found please add a user to the shoppingList before you continue");
        }

        loggedInCheck.verifyOwnerAuthorization(shoppingList.getUser().getUsername(), username, "shopping list" );
        return shoppingListMapper.fromEntity(shoppingList);
    }

    public ShoppingListModel createShoppingList(ShoppingListModel shoppingListModel, String username) {
        loggedInCheck.verifyLoggedInUser(username);

        ShoppingList shoppingList = new ShoppingList();

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }
        shoppingList.setUser(user);
        shoppingList.setType(shoppingListModel.getType());
        shoppingList.setPackaging(shoppingListModel.getPackaging());
        shoppingList.setAtHomeDelivery(shoppingListModel.getAtHomeDelivery());
        shoppingList = shoppingListRepository.save(shoppingList);
        return shoppingListMapper.fromEntity(shoppingList);
    }

    public List<GameResponseDTO> getGameFromShoppingList(Long shoppingListId, String username, Long gameId) {
        loggedInCheck.verifyLoggedInUser(username);

        Optional<ShoppingList> shoppingListOptional = shoppingListRepository.findById(shoppingListId);
        if (shoppingListOptional.isEmpty()) {
            throw new EntityNotFoundException("ShoppingList with id " + shoppingListId + " does not exist");
        }
        ShoppingList shoppingList = shoppingListOptional.get();
        if(shoppingList.getUser() == null){
            throw new NoUserAssignedException("shopping list");
        }
        loggedInCheck.verifyOwnerAuthorization(shoppingList.getUser().getUsername(), username, "shopping list");

        Optional<Game> gameOptional = shoppingList.getGames().stream()
                .filter(game -> game.getId().equals(gameId))
                .findFirst();

        if (gameOptional.isPresent()) {
            Game game = gameOptional.get();
            return Collections.singletonList(gameDTOMapper.toGameDto(gameMapper.fromEntity(game)));
        } else {
            throw new RecordNotFoundException("Game not found in the shopping list");
        }
    }

    public List<MovieResponseDTO> getMovieFromShoppingList(Long shoppingListId, String username, Long movieId) {
        loggedInCheck.verifyLoggedInUser(username);

        Optional<ShoppingList> shoppingListOptional = shoppingListRepository.findById(shoppingListId);
        if (shoppingListOptional.isEmpty()) {
            throw new EntityNotFoundException("ShoppingList with id " + shoppingListId + " does not exist");
        }
        ShoppingList shoppingList = shoppingListOptional.get();
        if(shoppingList.getUser() == null){
            throw new NoUserAssignedException("shopping list");
        }
        loggedInCheck.verifyOwnerAuthorization(shoppingList.getUser().getUsername(), username, "shopping list");



        if (shoppingList.getMovies().isEmpty()) {
            throw new RecordNotFoundException("The requested movie within the list does not exist");
        } else {
            Optional<Movie> optionalMovie = shoppingList.getMovies().stream()
                    .filter(movie -> movie.getId().equals(movieId))
                    .findFirst();
            if (optionalMovie.isPresent()) {
                Movie movie = optionalMovie.get();

                return Collections.singletonList(movieDTOMapper.toMovieDTO(movieMapper.fromEntity(movie)));
            } else {
                throw new RecordNotFoundException("movie has not been found within the shopping list");
            }
        }
    }

    public ShoppingListModel updateShoppingList(Long id, String username, ShoppingListModel shoppingListModel) {
        loggedInCheck.verifyLoggedInUser(username);

        Optional<ShoppingList> shoppingListFound = shoppingListRepository.findById(id);


        if (shoppingListFound.isPresent()) {
            ShoppingList existingShoppingList = shoppingListFound.get();
            if(existingShoppingList.getUser() == null){
                throw new EntityNotFoundException("Add a user before adjusting the shopping list");
            }
            loggedInCheck.verifyOwnerAuthorization(existingShoppingList.getUser().getUsername(), username, "shopping list");
            if(orderConfirmationHelper.isShoppingListConnectedToOrder(id)){
                throw new EntityNotFoundException("it is not allowed to adjust fields of a shoppinglist within a order with the confirmation set to true");
            }
            if (shoppingListModel.getAtHomeDelivery() != null) {
                existingShoppingList.setAtHomeDelivery(shoppingListModel.getAtHomeDelivery());
                shoppingListHelpers.calculateDeliveryCost(existingShoppingList.getId());
            }
            if (shoppingListModel.getType() != null &&
                    (shoppingListModel.getType().equalsIgnoreCase("wishlist") ||
                            shoppingListModel.getType().equalsIgnoreCase("shoppinglist"))) {
                existingShoppingList.setType(shoppingListModel.getType().toLowerCase());
            } else {
                throw new EntityNotFoundException("please fill in a correct type, either shoppinglist or wishlist");
            }
            if (shoppingListModel.getPackaging() != null) {
                existingShoppingList.setPackaging(shoppingListModel.getPackaging());
                shoppingListHelpers.calculatePackagingCost(existingShoppingList.getId());
            }
            if (shoppingListModel.getCreatePdf() != null){
                existingShoppingList.setCreatePdf(shoppingListModel.getCreatePdf());
            }

            if(shoppingListModel.getType().contains("wishlist") && shoppingListModel.getCreatePdf()){
                pdfFileWishList.createPDFFromWishlist(existingShoppingList);
            }
            existingShoppingList = shoppingListRepository.save(existingShoppingList);
            return shoppingListMapper.fromEntity(existingShoppingList);
        }    else {
            throw new RecordNotFoundException("Shopping list with id " + id + " not found");
        }

    }



    public void addGameToShoppingList(Long shoppingListId, String username, Long gameId) {
        loggedInCheck.verifyLoggedInUser(username);

        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping list not found"));
        if(shoppingList.getUser() == null){
            throw new NoUserAssignedException("shopping list");
        }
        loggedInCheck.verifyOwnerAuthorization(shoppingList.getUser().getUsername(), username, "shopping list");
        if(orderConfirmationHelper.isShoppingListConnectedToOrder(shoppingListId)){
            throw new EntityNotFoundException("it is not allowed to adjust fields of a shoppinglist within a order with the confirmation set to true");
        }
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("Game not found"));

        shoppingList.getGames().add(game);

        extracted(shoppingListId, shoppingList);
    }



    public void addMovieToShoppingList(Long shoppingListId, String username,  Long movieId) {
        loggedInCheck.verifyLoggedInUser(username);

        ShoppingList shoppingList = shoppingListRepository.findById(shoppingListId)
                .orElseThrow(() -> new EntityNotFoundException("Shopping list not found"));
        if(shoppingList.getUser() == null){
            throw new NoUserAssignedException("shopping list");
        }
        loggedInCheck.verifyOwnerAuthorization(shoppingList.getUser().getUsername(), username, "shopping list");
        if(orderConfirmationHelper.isShoppingListConnectedToOrder(shoppingListId)){
            throw new EntityNotFoundException("it is not allowed to adjust fields of a shoppinglist within a order with the confirmation set to true");
        }
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));

        shoppingList.getMovies().add(movie);


        extracted(shoppingListId, shoppingList);
    }


    public void deleteShoppingList(Long id, String username) {
        loggedInCheck.verifyLoggedInUser(username);

        Optional<ShoppingList> optionalShoppingList = shoppingListRepository.findById(id);
        if (optionalShoppingList.isEmpty()) {
            throw new RecordNotFoundException("Shopping list with id " + id + " does not exist");
        }
        ShoppingList shoppingList = optionalShoppingList.get();

        if (shoppingList.getUser() == null || shoppingList.getUser().getUsername() == null || shoppingList.getUser().getUsername().isEmpty()) {
            throw new NoUserAssignedException("shopping list", username);
        }
        loggedInCheck.verifyOwnerAuthorization(shoppingList.getUser().getUsername(), username, "shopping list be");

        shoppingListRepository.deleteById(id);
    }



    public void deleteGameWithinShoppingList(String username, Long shoppingListId, Long gameId) {
        loggedInCheck.verifyLoggedInUser(username);

        Optional<ShoppingList> optionalShoppingList = shoppingListRepository.findById(shoppingListId);
        if (optionalShoppingList.isEmpty()) {
            throw new RecordNotFoundException("shopping list with id " + shoppingListId + " does not exist");
        }
        ShoppingList shoppingList = optionalShoppingList.get();
        loggedInCheck.verifyOwnerAuthorization(shoppingList.getUser().getUsername(), username, "shopping list" );

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
    
    public void deleteMovieWithinShoppingList(String username, Long shoppingListId, Long movieId) {

        loggedInCheck.verifyLoggedInUser(username);

        Optional<ShoppingList> optionalShoppingList = shoppingListRepository.findById(shoppingListId);
        if (optionalShoppingList.isEmpty()) {
            throw new EntityNotFoundException("Shopping list with id " + shoppingListId + " does not exist");
        }
        ShoppingList shoppingList = optionalShoppingList.get();
        loggedInCheck.verifyOwnerAuthorization(shoppingList.getUser().getUsername(), username, "shopping list" );

        List<Movie> movieInList = shoppingList.getMovies();
        Optional<Movie> optionalMovie = movieInList.stream().filter(movie -> movie.getId().equals(movieId)).findFirst();

        if (optionalMovie.isPresent()) {
            Movie movieToDelete = optionalMovie.get();
            movieInList.remove(movieToDelete);
            shoppingListRepository.save(shoppingList);
        } else {
            throw new RecordNotFoundException("The movie you requested to delete with id " + movieId + " does not exist");
        }
    }

    public void deleteShoppingList(Long id){
        shoppingListRepository.deleteById(id);
    }


}












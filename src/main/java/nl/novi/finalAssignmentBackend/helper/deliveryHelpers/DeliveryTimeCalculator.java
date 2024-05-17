package nl.novi.finalAssignmentBackend.helper.deliveryHelpers;

import jakarta.persistence.EntityNotFoundException;
import nl.novi.finalAssignmentBackend.Repository.GameRepository;
import nl.novi.finalAssignmentBackend.Repository.MovieRepository;
import nl.novi.finalAssignmentBackend.Repository.OrderRepository;
import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.entities.Movie;
import nl.novi.finalAssignmentBackend.entities.Order;
import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import nl.novi.finalAssignmentBackend.exceptions.InsufficientStockException;
import nl.novi.finalAssignmentBackend.mappers.GameMappers.GameMapper;
import nl.novi.finalAssignmentBackend.mappers.MovieMappers.MovieMapper;
import nl.novi.finalAssignmentBackend.model.GameModel;
import nl.novi.finalAssignmentBackend.model.MovieModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Component
public class DeliveryTimeCalculator {

    private final OrderRepository orderRepository;
    private final MovieRepository movieRepository;
    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final MovieMapper movieMapper;

    public DeliveryTimeCalculator(OrderRepository orderRepository, MovieRepository movieRepository, GameRepository gameRepository, GameMapper gameMapper, MovieMapper movieMapper) {
        this.orderRepository = orderRepository;
        this.movieRepository = movieRepository;
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
        this.movieMapper = movieMapper;
    }


    public void setDeliveryDate(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        ShoppingList shoppingList = order.getShoppingList();

        if (shoppingList == null) {
            throw new EntityNotFoundException("Shopping list not found for the order with id: " + id);
        }

        ProductCountHelper gameCount = new ProductCountHelper();
        ProductCountHelper movieCount = new ProductCountHelper();

        for (Game game : shoppingList.getGames()) {
            gameCount.increment(game.getId());
        }
        for (Movie movie : shoppingList.getMovies()) {
            movieCount.increment(movie.getId());
        }

        checkStockAndSetDeliveryDate(order, gameCount, movieCount);
    }

    private void checkStockAndSetDeliveryDate(Order order, ProductCountHelper gamecount, ProductCountHelper movieCount) {
        for (Map.Entry<Long, Integer> entry : gamecount.getProductCounts().entrySet()) {
            Long gameId = entry.getKey();
            int gameQuantity = entry.getValue();

            Game game = gameRepository.findById(gameId)
                    .orElseThrow(() -> new EntityNotFoundException("Game not found with id: " + gameId));
            GameModel gameModel = gameMapper.fromEntity(game);
            if (gameModel.getCurrentStock() < gameQuantity) {
                throw new InsufficientStockException("game", game.getName());
            }
            if(order.getOrderConfirmation()){
                gameModel.setAmountSold(gameModel.getAmountSold() + gameQuantity);
            }
            game = gameMapper.toEntity(gameModel);
            gameRepository.save(game);

        }

        for (Map.Entry<Long, Integer> entry : movieCount.getProductCounts().entrySet()) {
            Long movieId = entry.getKey();
            int movieQuantity = entry.getValue();

            Movie movie = movieRepository.findById(movieId)
                    .orElseThrow(() -> new EntityNotFoundException("Movie not found with id: " + movieId));

            MovieModel movieModel = movieMapper.fromEntity(movie);
            if (movie.getCurrentStock() < movieQuantity) {
                throw new InsufficientStockException("movie", movie.getName());

            }
            if(order.getOrderConfirmation()){
                movieModel.setAmountSold(movieModel.getAmountSold() + movieQuantity);
            }
            movie = movieMapper.toEntity(movieModel);
            movieRepository.save(movie);
        }


        order.setDeliveryDate(LocalDate.now().plusDays(5));
        order.setCreatePdf(true);
        orderRepository.save(order);
    }
}





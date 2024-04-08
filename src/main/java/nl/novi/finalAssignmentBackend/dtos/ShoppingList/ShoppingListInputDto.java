package nl.novi.finalAssignmentBackend.dtos.ShoppingList;

import nl.novi.finalAssignmentBackend.model.GameModel;
import nl.novi.finalAssignmentBackend.model.MovieModel;

import java.util.List;

public class ShoppingListInputDto {

        private Long id;
        private String type;
        private Integer subtotal;


        List<MovieModel> movieModel;

        List<GameModel> gameModel;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    public List<MovieModel> getMovieModel() {
        return movieModel;
    }

    public void setMovieModel(List<MovieModel> movieModel) {
        this.movieModel = movieModel;
    }

    public List<GameModel> getGameModel() {
        return gameModel;
    }

    public void setGameModel(List<GameModel> gameModel) {
        this.gameModel = gameModel;
    }
}

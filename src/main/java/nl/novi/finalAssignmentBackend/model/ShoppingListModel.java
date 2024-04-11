package nl.novi.finalAssignmentBackend.model;


import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.entities.Movie;

import java.util.List;

public class ShoppingListModel {


    private Long id;
    private String type;
    private Integer subtotal;


    private List<Game> games;
    private List<Movie> movies;


    public List<Movie> getMovies() {
        return this.movies;
    }
    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }



    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

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

}

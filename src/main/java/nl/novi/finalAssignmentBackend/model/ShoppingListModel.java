package nl.novi.finalAssignmentBackend.model;


import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.entities.Movie;

import java.util.List;

public class ShoppingListModel {


    private Long id;
    private String type;
    private Double subtotal;

    private Boolean packaging;
    private Boolean atHomeDelivery;
    private Integer deliveryCost;

    private Double packagingCost;

    private List<Game> games;
    private List<Movie> movies;

    public Boolean getPackaging() {
        return packaging;
    }

    public void setPackaging(Boolean packaging) {
        this.packaging = packaging;
    }

    public Boolean getAtHomeDelivery() {
        return atHomeDelivery;
    }

    public void setAtHomeDelivery(Boolean atHomeDelivery) {
        this.atHomeDelivery = atHomeDelivery;
    }

    public Integer getDeliverCost() {
        return deliveryCost;
    }

    public void setDeliverCost(Integer deliverCost) {
        this.deliveryCost = deliverCost;
    }

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

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getPackagingCost() {
        return packagingCost;
    }

    public void setPackagingCost(Double packagingCost) {
        this.packagingCost = packagingCost;
    }
}

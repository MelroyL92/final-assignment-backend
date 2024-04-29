package nl.novi.finalAssignmentBackend.dtos.shoppingList;

import nl.novi.finalAssignmentBackend.dtos.game.GameResponseDto;
import nl.novi.finalAssignmentBackend.dtos.movie.MovieResponseDto;
import nl.novi.finalAssignmentBackend.dtos.user.UserResponseDto;
import nl.novi.finalAssignmentBackend.entities.User;


import java.util.List;

public class ShoppingListResponseDto {

    private Long id;
    private String type;
    private Double subtotal;

    private Boolean packaging;
    private Boolean atHomeDelivery;
    private Integer deliveryCost;
    private Double packagingCost;

    private List<MovieResponseDto> movies;

    private List<GameResponseDto> games;

    private UserResponseDto user;



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

    public List<MovieResponseDto> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieResponseDto> movies) {
        this.movies = movies;
    }

    public List<GameResponseDto> getGames() {
        return games;
    }

    public void setGames(List<GameResponseDto> games) {
        this.games = games;
    }

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

    public Integer getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(Integer cost) {
        this.deliveryCost = cost;
    }

    public Double getPackagingCost() {
        return packagingCost;
    }

    public void setPackagingCost(Double packagingCost) {
        this.packagingCost = packagingCost;
    }

    public UserResponseDto getUser() {
        return user;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }
}


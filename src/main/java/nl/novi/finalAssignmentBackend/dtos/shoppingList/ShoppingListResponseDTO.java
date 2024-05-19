package nl.novi.finalAssignmentBackend.dtos.shoppingList;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import nl.novi.finalAssignmentBackend.dtos.game.GameResponseDTO;
import nl.novi.finalAssignmentBackend.dtos.movie.MovieResponseDTO;
import nl.novi.finalAssignmentBackend.dtos.order.OrderResponseDTO;
import nl.novi.finalAssignmentBackend.dtos.user.UserResponseDTO;
import nl.novi.finalAssignmentBackend.helper.enums;


import java.util.List;

public class ShoppingListResponseDTO {

    private Long id;
    @Enumerated(EnumType.STRING)
    private enums.ShoppingListType type;
    private Double subtotal;

    private Boolean packaging;
    private Boolean atHomeDelivery;
    private Integer deliveryCost;
    private Double packagingCost;
    private Boolean createPdf;



    private List<MovieResponseDTO> movies;

    private List<GameResponseDTO> games;

    private UserResponseDTO user;

    private OrderResponseDTO order;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public enums.ShoppingListType getType() {
        return type;
    }

    public void setType(enums.ShoppingListType type) {
        this.type = type;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public List<MovieResponseDTO> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieResponseDTO> movies) {
        this.movies = movies;
    }

    public List<GameResponseDTO> getGames() {
        return games;
    }

    public void setGames(List<GameResponseDTO> games) {
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

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    public Boolean getCreatePdf() {
        return createPdf;
    }

    public void setCreatePdf(Boolean createPdf) {
        this.createPdf = createPdf;
    }

    public OrderResponseDTO getOrder() {
        return order;
    }

    public void setOrder(OrderResponseDTO order) {
        this.order = order;
    }
}


package nl.novi.finalAssignmentBackend.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import nl.novi.finalAssignmentBackend.helper.enums;

import java.util.List;

public class ShoppingListModel {


    private Long id;
    @Enumerated(EnumType.STRING)
    private enums.ShoppingListType type;
    private Double subtotal;

    private Boolean packaging;
    private Boolean atHomeDelivery;
    private Integer deliveryCost;

    private Double packagingCost;
    private Boolean createPdf;


    private List<GameModel> games;
    private List<MovieModel> movies;

    private UserModel userModel;


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

    public enums.ShoppingListType getType() {
        return type;
    }

    public void setType(enums.ShoppingListType type) {
        this.type = type;
    }

    public Integer getDeliverCost() {
        return deliveryCost;
    }

    public void setDeliverCost(Integer deliverCost) {
        this.deliveryCost = deliverCost;
    }

    public List<GameModel> getGames() {
        return games;
    }

    public void setGames(List<GameModel> games) {
        this.games = games;
    }

    public List<MovieModel> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieModel> movies) {
        this.movies = movies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public Boolean getCreatePdf() {
        if (createPdf == null){
            setCreatePdf(false);
        }
        return createPdf;
    }

    public void setCreatePdf(Boolean createPdf) {
        this.createPdf = createPdf;
    }
}

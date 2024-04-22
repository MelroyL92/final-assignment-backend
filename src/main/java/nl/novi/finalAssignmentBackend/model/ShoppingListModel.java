package nl.novi.finalAssignmentBackend.model;
import java.util.List;

public class ShoppingListModel {


    private Long id;
    private String type;
    private Double subtotal;

    private Boolean packaging;
    private Boolean atHomeDelivery;
    private Integer deliveryCost;

    private Double packagingCost;

    private Boolean isLocked;  // toegevoegd voor authorisatiepoging



    public Boolean getLocked() { // toegevoegd voor authorisatiepoging
        return isLocked;
    }

    public void setLocked(Boolean locked) { // toegevoegd voor authorisatiepoging
        isLocked = locked;
    }

    private List<GameModel> games;
    private List<MovieModel> movies;

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

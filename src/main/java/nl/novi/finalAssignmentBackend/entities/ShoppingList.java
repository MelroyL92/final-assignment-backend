package nl.novi.finalAssignmentBackend.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shopping_list")
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "subtotal")
    private Double subtotal;

    @Column(name = "packaging")
    private Boolean packaging;

    @Column(name = "packaging_cost")
    private Double packagingCost;

    @Column(name = "at_home_delivery")
    @NotNull(message = "please fill in either true or false for atHomeDelivery")
    private Boolean atHomeDelivery;

    @Column(name = "delivery_cost")
    private Integer deliveryCost;

    @Column(name = "create_pdf")
    @NotNull(message = "please fill in either true or false for the creation of a pdf")
    private Boolean createPdf;

    @ManyToMany()
    @JoinTable(
            name = "shopping_list_movies",
            joinColumns = @JoinColumn(name = "shopping_list_id"),
            inverseJoinColumns = @JoinColumn(name = "movies_id")
    )
    private List<Movie> movies = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "shopping_list_game",
            joinColumns = @JoinColumn(name = "shopping_list_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private List<Game> games = new ArrayList<>();


    @ManyToMany(mappedBy = "shoppingList")
    private List<Order>orders = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;


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

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Double getPackagingCost() {
        return packagingCost;
    }

    public void setPackagingCost(Double packagingCost) {
        this.packagingCost = packagingCost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getCreatePdf() {
        return createPdf;
    }

    public void setCreatePdf(Boolean createPdf) {
        this.createPdf = createPdf;
    }
}


package nl.novi.finalAssignmentBackend.entities;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "movies")
public class Movie extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "genre")
    private String genre;

    @Column(name = "type")
    private String type;

    @Column(name = "director")
    private String director;


    @Column(name = "watch_time_in_min")
    private Integer watchTimeInMin;

   @ManyToMany(mappedBy = "movies")
   private List<ShoppingList> shoppingList = new ArrayList<>();


    public Integer getWatchTimeInMin() {
        return watchTimeInMin;
    }

    public void setWatchTimeInMin(Integer watchTimeInMin) {
        this.watchTimeInMin = watchTimeInMin;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

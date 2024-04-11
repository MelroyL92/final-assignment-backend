package nl.novi.finalAssignmentBackend.entities;
import jakarta.persistence.*;



@Entity
@Table(name = "movies")
public class Movie extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String genre;
    private String type;
    private String director;

    private String playtime;

    @ManyToOne
    @JoinColumn(name = "shopping_list_movies")
    private ShoppingList shoppingList;


    public String getPlaytime() {
        return playtime;
    }

    public void setPlaytime(String playtime) {
        this.playtime = playtime;
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

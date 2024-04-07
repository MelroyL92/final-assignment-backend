package nl.novi.finalAssignmentBackend.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;



@Entity
@Table(name = "movies")
public class Movie extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "please fill in the genre for this Movie")
    private String genre;

    @NotBlank(message = "please fill in the type of the movie")
    private String type;

    @NotBlank(message = "please fill in the director of the movie")
    private String director;

    @NotBlank(message = "please fill in the playtime of the movie")
    private String playtime;

    @ManyToOne
    @JoinColumn(name = "shopping_list_id")
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

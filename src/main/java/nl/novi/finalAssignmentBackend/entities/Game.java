package nl.novi.finalAssignmentBackend.entities;
import jakarta.persistence.*;


@Entity
@Table(name = "games")
public class Game extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String platform;
    private String publisher;
    @Column(name = "play_duration")
    private String playDuration;

    @ManyToOne
    @JoinColumn(name = "shopping_list_games")
    private ShoppingList shoppingList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPlayDuration() {
        return playDuration;
    }

    public void setPlayDuration(String playDuration) {
        this.playDuration = playDuration;
    }


}

package nl.novi.finalAssignmentBackend.entities;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "games")
public class Game extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "platform")
    private String platform;

    @Column(name = "publisher")
    private String publisher;
    @Column(name = "play_duration_in_min")
    private Integer playDurationInMin;

    @ManyToMany(mappedBy = "games")
    private List<ShoppingList> shoppingList = new ArrayList<>();


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

    public Integer getPlayDurationInMin() {
        return playDurationInMin;
    }

    public void setPlayDurationInMin(Integer playDurationInMin) {
        this.playDurationInMin = playDurationInMin;
    }

}

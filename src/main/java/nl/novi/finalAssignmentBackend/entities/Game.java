package nl.novi.finalAssignmentBackend.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "games")
public class Game extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Platform must not be empty")
    @Size(min =1, max = 100, message = "please fill in a description between 3 and 100 characters")
    private String platform;

    @NotBlank(message = "Publisher must not be empty")
    @Size(min =3, max = 100, message = "please fill in a description between 3 and 100 characters")
    private String publisher;
    @Column(name = "play_duration_in_min")
    @NotNull(message = "the playtime should be between 1 and 10000 minutes")
    @Min(1)
    @Max(10000)
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

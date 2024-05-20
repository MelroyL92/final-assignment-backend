package nl.novi.finalAssignmentBackend.dtos.movie;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import nl.novi.finalAssignmentBackend.model.ProductModel;

public class MovieInputDTO extends ProductModel {
    private Long id;
    @NotBlank(message = "genre cannot be empty")
    @Size(min =3, max = 100, message = "please fill in a genre between 3 and 100 characters")
    private String genre;

    @NotBlank(message = "type cannot be empty")
    @Size(min =3, max = 100, message = "please fill in a type between 3 and 100 characters")
    private String type;

    @NotBlank(message = "director cannot be empty")
    @Size(min =3, max = 100, message = "please fill in the director between 3 and 100 characters")
    private String director;

    @NotNull(message = "please fill in a valid playtime between 10 and 400 min")
    @Min(10)
    @Max(400)
    @Column(name = "watch_time_in_min")
    private Integer watchTimeInMin;


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

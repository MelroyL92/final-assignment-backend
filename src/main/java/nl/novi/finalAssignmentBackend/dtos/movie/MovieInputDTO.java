package nl.novi.finalAssignmentBackend.dtos.movie;
import nl.novi.finalAssignmentBackend.model.ProductModel;

public class MovieInputDTO extends ProductModel {
    private Long id;

    private String genre;
    private String type;
    private String director;

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

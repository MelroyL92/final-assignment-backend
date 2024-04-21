package nl.novi.finalAssignmentBackend.dtos.movie;

import nl.novi.finalAssignmentBackend.entities.Product;

public class MovieInputDto extends Product {
    private Long id;

    private String genre;
    private String type;
    private String director;

    private Integer playtimeInMin;


    public Integer getPlaytimeInMin() {
        return playtimeInMin;
    }

    public void setPlaytimeInMin(Integer playtimeInMin) {
        this.playtimeInMin = playtimeInMin;
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

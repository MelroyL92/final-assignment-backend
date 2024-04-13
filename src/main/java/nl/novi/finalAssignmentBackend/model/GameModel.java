package nl.novi.finalAssignmentBackend.model;


import nl.novi.finalAssignmentBackend.entities.ShoppingList;

import java.util.ArrayList;
import java.util.List;

public class GameModel extends ProductModel {


    private Long id;
    private String platform;
    private String publisher;
    private String playDuration;

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

    public String getPlayDuration() {
        return playDuration;
    }

    public void setPlayDuration(String playDuration) {
        this.playDuration = playDuration;
    }
}

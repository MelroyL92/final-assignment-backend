package nl.novi.finalAssignmentBackend.model;

import java.util.ArrayList;
import java.util.List;

public class GameModel extends ProductModel {


    private Long id;
    private String platform;
    private String publisher;
    private Integer playDurationInMin;

    private List<ShoppingListModel> shoppingList = new ArrayList<>();


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

//    public List<ShoppingListModel> getShoppingList() {
//        return shoppingList;
//    }
//
//    public void setShoppingList(List<ShoppingListModel> shoppingList) {
//        this.shoppingList = shoppingList;
//    }
}

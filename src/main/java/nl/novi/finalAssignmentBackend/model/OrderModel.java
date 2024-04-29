package nl.novi.finalAssignmentBackend.model;
import nl.novi.finalAssignmentBackend.entities.User;

import java.util.ArrayList;
import java.util.List;

public class OrderModel {

    private Long orderNumber;
    private String dateOrdered;
    private Boolean orderConfirmation;
    private String status;
    private String deliveryDate;

    private Double profit;

    private Double totalPrice;

    private boolean createPdf;// (nieuw inclusief getters + setters, test)

    private List<ShoppingListModel> shoppingLists = new ArrayList<>();

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(String dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public Boolean getOrderConfirmation() {
        return orderConfirmation;
    }

    public void setOrderConfirmation(Boolean orderConfirmation) {
        this.orderConfirmation = orderConfirmation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }



    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ShoppingListModel> getShoppingLists() {
        return shoppingLists;
    }

    public void setShoppingLists(List<ShoppingListModel> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    public boolean isCreatePdf() {
        return createPdf;
    }

    public void setCreatePdf(boolean createPdf) {
        this.createPdf = createPdf;
    }
}

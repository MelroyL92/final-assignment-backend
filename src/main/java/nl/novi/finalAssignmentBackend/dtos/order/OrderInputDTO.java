package nl.novi.finalAssignmentBackend.dtos.order;

import nl.novi.finalAssignmentBackend.model.ShoppingListModel;
import nl.novi.finalAssignmentBackend.model.UserModel;

import java.time.LocalDate;
import java.util.List;

public class OrderInputDTO {

    private Long orderNumber;
    private Boolean orderConfirmation;
    private String status;

    private Double profit;

    private Double totalPrice;

    private Boolean createPdf;

    public Boolean getCreatePdf() {
        return createPdf;
    }

    public void setCreatePdf(Boolean createPdf) {
        this.createPdf = createPdf;
    }

    private LocalDate deliveryDate;

    private LocalDate dateOrdered;

    private Boolean hasPaid;


    private List<ShoppingListModel> shoppingList;


    private UserModel user;




    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
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

    public List<ShoppingListModel> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(List<ShoppingListModel> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public LocalDate getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(LocalDate dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public Boolean getHasPaid() {
        return hasPaid;
    }

    public void setHasPaid(Boolean hasPaid) {
        this.hasPaid = hasPaid;
    }
}

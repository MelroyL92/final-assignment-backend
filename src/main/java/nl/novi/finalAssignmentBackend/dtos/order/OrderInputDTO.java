package nl.novi.finalAssignmentBackend.dtos.order;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import nl.novi.finalAssignmentBackend.helper.enums;
import nl.novi.finalAssignmentBackend.model.ShoppingListModel;
import nl.novi.finalAssignmentBackend.model.UserModel;

import java.time.LocalDate;

public class OrderInputDTO {

    private Long orderNumber;
    private Boolean orderConfirmation;
    @Enumerated(EnumType.STRING)
    private enums.OrderStatus status;

    private Double profit;

    private Double totalPrice;

    private boolean createPdf;

    private LocalDate deliveryDate;

    private LocalDate dateOrdered;

    private Boolean hasPaid;


    private ShoppingListModel shoppingList;

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

    public enums.OrderStatus getStatus() {
        return status;
    }

    public void setStatus(enums.OrderStatus status) {
        this.status = status;
    }

    public ShoppingListModel getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingListModel shoppingList) {
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

    public boolean isCreatePdf() {
        return createPdf;
    }

    public void setCreatePdf(boolean createPdf) {
        this.createPdf = createPdf;
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

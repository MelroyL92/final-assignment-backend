package nl.novi.finalAssignmentBackend.dtos.order;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import nl.novi.finalAssignmentBackend.dtos.shoppingList.ShoppingListResponseDTO;
import nl.novi.finalAssignmentBackend.dtos.user.UserResponseDTO;
import nl.novi.finalAssignmentBackend.helper.enums;

import java.time.LocalDate;

public class OrderResponseDTO {

    private Long orderNumber;
    private Boolean orderConfirmation;
    @Enumerated(EnumType.STRING)
    private enums.OrderStatus status;
    private LocalDate dateOrdered;
    private LocalDate deliveryDate;
    private Boolean hasPaid;
    private Double totalPrice;
    private boolean createPdf;




    private UserResponseDTO user;

    private ShoppingListResponseDTO shoppingList;


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

    public ShoppingListResponseDTO getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingListResponseDTO shoppingList) {
        this.shoppingList = shoppingList;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public UserResponseDTO getUser() {
        return user;
    }

    public void setUser(UserResponseDTO user) {
        this.user = user;
    }

    public boolean isCreatePdf() {
        return createPdf;
    }

    public void setCreatePdf(boolean createPdf) {
        this.createPdf = createPdf;
    }

    public LocalDate getDateOrdered() {
        return dateOrdered;
    }
    public void setDateOrdered(LocalDate dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Boolean getHasPaid() {
        return hasPaid;
    }
    public void setHasPaid(Boolean hasPaid) {
        this.hasPaid = hasPaid;
    }
}

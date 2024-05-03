package nl.novi.finalAssignmentBackend.dtos.order;

import nl.novi.finalAssignmentBackend.dtos.shoppingList.ShoppingListResponseDto;
import nl.novi.finalAssignmentBackend.dtos.user.UserResponseDto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderResponseDto {

    private Long orderNumber;
    private Boolean orderConfirmation;
    private String status;
    private LocalDate dateOrdered;
    private LocalDate deliveryDate;
    private Boolean hasPaid;
    private Double totalPrice;
    private boolean createPdf;




    private UserResponseDto user;
    private List<ShoppingListResponseDto> shoppingList = new ArrayList<>();




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

    public List<ShoppingListResponseDto> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(List<ShoppingListResponseDto> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public UserResponseDto getUser() {
        return user;
    }

    public void setUser(UserResponseDto user) {
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

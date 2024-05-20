package nl.novi.finalAssignmentBackend.model;



import java.time.LocalDate;

public class OrderModel {

    private Long orderNumber;
    private Boolean orderConfirmation;
    private String status;
    private Double profit;
    private Double totalPrice;
    private LocalDate dateOrdered;
    private LocalDate deliveryDate;
    private Boolean createPdf;
    private Boolean hasPaid;

    private ShoppingListModel shoppingLists;
    private UserModel userModel;




    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

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


    public ShoppingListModel getShoppingLists() {
        return shoppingLists;
    }

    public void setShoppingLists(ShoppingListModel shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    public Boolean getCreatePdf() {
        if (createPdf == null){
            setCreatePdf(false);
        }
        return createPdf;
    }

    public void setCreatePdf(Boolean createPdf) {
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
        if (hasPaid == null){
            setHasPaid(false);
        }
        return hasPaid;
    }

    public void setHasPaid(Boolean hasPaid) {
        this.hasPaid = hasPaid;
    }
}

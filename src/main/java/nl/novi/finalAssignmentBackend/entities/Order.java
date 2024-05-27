package nl.novi.finalAssignmentBackend.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNumber;

    @Column(name = "date_ordered")
    private LocalDate dateOrdered;

    @Column(name = "order_confirmation")
    private Boolean orderConfirmation;

    @Column(name = "status")
    private String status;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "profit")
    private Double profit;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "has_paid")
    private Boolean hasPaid;

    @Column(name = "create_pdf")
    private Boolean createPdf;


    @ManyToMany()
    @JoinTable(
            name = "Order_shopping_list",
            joinColumns = @JoinColumn(name = "order_number_id"),
            inverseJoinColumns = @JoinColumn(name = "shopping_list_id")
    )
    private List<ShoppingList> shoppingList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;


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


    public List<ShoppingList> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(List<ShoppingList> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public User getUser() {
       return user;
   }

   public void setUser(User user) {
       this.user = user;
   }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getCreatePdf() {
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
        return hasPaid;
    }

    public void setHasPaid(Boolean hasPaid) {
        this.hasPaid = hasPaid;
    }
}

package nl.novi.finalAssignmentBackend.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNumber;
    private String dateOrdered;
    private Boolean orderConfirmation;
    private String status;
    private String deliveryDate;
    private Double profit;

    @ManyToMany()
    @JoinTable(
            name = "invoice_shopping_list",
            joinColumns = @JoinColumn(name = "order_number_id"),
            inverseJoinColumns = @JoinColumn(name = "shopping_list_id")
    )
    private List<ShoppingList> shoppingList = new ArrayList<>();

    // this one still needs to be checked better
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;
// Something to think about: maybe make a attribute for profit to only be shown to the admin so he/she can see what profit gets made on each order

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
}

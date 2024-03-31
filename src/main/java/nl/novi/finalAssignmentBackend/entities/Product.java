package nl.novi.finalAssignmentBackend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@MappedSuperclass
public abstract class Product {



        @Min(1)
        @Max(500)
        private Integer sellingPrice;

        @Column(name = "original_stock")
        private Integer originalStock;

        @NotBlank(message = "please fill in a description for this product")
        @Size(min =10, max = 300, message = "please fill in a description between 3 and 300 characters")
        private String description;

        @NotBlank(message = "name must not be empty")
        private String name;

        @Column(name = "amount_sold")
        private Integer amountSold;

        @NotNull()
        @NotBlank(message = "please fill in a valid year of release")
        @Column(name = "year_of_release")
        private String yearOfRelease;

        private Integer purchasePrice;




        public String getYearOfRelease() {
            return yearOfRelease;
        }

        public void setYearOfRelease(String yearOfRelease) {
            this.yearOfRelease = yearOfRelease;
        }

        public Integer getSellingPrice() {
        return sellingPrice;
        }

        public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
     }

    public Integer getPurchasePrice() {
            return purchasePrice;
        }

        public void setPurchasePrice(Integer purchasePrice) {
            this.purchasePrice = purchasePrice;
        }

        public Integer getOriginalStock() {
            return originalStock;
        }

        public void setOriginalStock(Integer originalStock) {
            this.originalStock = originalStock;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAmountSold() {
            return amountSold;
        }

        public void setAmountSold(Integer amountSold) {
            this.amountSold = amountSold;
        }


}

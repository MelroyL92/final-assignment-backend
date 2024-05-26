package nl.novi.finalAssignmentBackend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@MappedSuperclass
public abstract class Product {


        @Column(name = "selling_price")
        @NotNull(message = "the selling price should be between 1 and 500 euro")
        @Min(value = 1, message = "the selling price should be 1 or above")
        @Max(value = 500, message = "the selling price should be less then 500")
        private Double sellingPrice;

        @Column(name = "original_stock")
        @NotNull(message = "the original stock should be set to 0 or above")
        @Min(value = 0 ,message = "the original stock should be at least 0")
        @Max(value = 5000, message = "the original stock should be less then 5000")
        private Integer originalStock;

        @Column(name = "description")
        @NotBlank(message = "please fill in a description for this product")
        @Size(min =10, max = 300, message = "please fill in a description between 3 and 300 characters")
        private String description;

        @Column(name = "name")
        @NotBlank(message = "name must not be empty")
        private String name;

        @Column(name = "amount_sold")
        @Min(value = 0, message = "the amount sold should be set to 0 or above")
        private Integer amountSold;


        @Column(name = "year_of_release")
        @NotNull
        @Min(value = 1970, message = "the year of release is at least 1970")
        @Max(value = 2100, message = "check the year of release, cant be set above 2100")
        private Integer yearOfRelease;

        @Column(name = "purchase_price")
        @NotNull(message = "please fill in a valid purchase price")
        @Min(value = 1, message = "purchase price should be at least 1")
        @Max(value = 200, message = "purchase price cannot exceed 200")
        private Double purchasePrice;

        @Column(name = "current_stock")
        @Min(value = 0, message = "current stock should be set to 0 or above")
        private Integer currentStock;

        public Integer getYearOfRelease() {
            return yearOfRelease;
        }

        public void setYearOfRelease(Integer yearOfRelease) {
            this.yearOfRelease = yearOfRelease;
        }

        public Double getSellingPrice() {
            return sellingPrice;
        }

        public void setSellingPrice(Double sellingPrice) {
            this.sellingPrice = sellingPrice;
        }

        public Double getPurchasePrice() {
            return purchasePrice;
        }

        public void setPurchasePrice(Double purchasePrice) {
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

        public Integer getCurrentStock() {
        return currentStock;
        }

        public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
        }
}

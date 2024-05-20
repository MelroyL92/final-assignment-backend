package nl.novi.finalAssignmentBackend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@MappedSuperclass
public abstract class Product {


        @NotNull(message = "the selling price should be at least 25% lower then the buy-in price")
        @Min(1)
        @Max(500)
        private Double sellingPrice;

        @Column(name = "original_stock")
        private Integer originalStock;

        @NotBlank(message = "please fill in a description for this product")
        @Size(min =10, max = 300, message = "please fill in a description between 3 and 300 characters")
        private String description;

        @NotBlank(message = "name must not be empty")
        private String name;

        @Column(name = "amount_sold")
        @Min(0)
        private Integer amountSold;

        @NotNull(message = "has to be between 1970 and 2100")
        @Min(1970)
        @Max(2100)
        @Column(name = "year_of_release")
        private Integer yearOfRelease;

        @NotNull(message = "please fill in a valid purchase price")
        @Min(1)
        @Max(200)
        private Double purchasePrice;

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

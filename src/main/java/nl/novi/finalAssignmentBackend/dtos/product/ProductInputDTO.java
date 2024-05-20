package nl.novi.finalAssignmentBackend.dtos.product;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

public class ProductInputDTO {


          @NotNull(message = "the selling price should be between 1 and 500 euro")
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

          @NotNull
          @Min(1970)
          @Max(2100)
          @Column(name = "year_of_release")
          private Integer yearOfRelease;

          @NotNull(message = "please fill in a valid purchase price")
          @Min(1)
          @Max(200)
          private Double purchasePrice;

          private Integer currentStock;


        public Double getSellingPrice() {
            return sellingPrice;
        }

        public void setSellingPrice(Double sellingPrice) {
            this.sellingPrice = sellingPrice;
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

        public Integer getYearOfRelease() {
            return yearOfRelease;
        }

        public void setYearOfRelease(Integer yearOfRelease) {
            this.yearOfRelease = yearOfRelease;
        }

        public Double getPurchasePrice() {
            return purchasePrice;
        }

        public void setPurchasePrice(Double purchasePrice) {
            this.purchasePrice = purchasePrice;
        }

        public Integer getCurrentStock() {
            return currentStock;
        }

        public void setCurrentStock(Integer currentStock) {
            this.currentStock = originalStock -amountSold;

        }
    }


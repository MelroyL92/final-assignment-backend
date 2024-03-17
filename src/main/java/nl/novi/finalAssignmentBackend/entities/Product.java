package nl.novi.finalAssignmentBackend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@MappedSuperclass
public abstract class Product {


        @NotNull()
        @Min(1)
        @Max(500)
        private Integer price;

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
        @Min(1970)
        @Max(2100)
        @Column(name = "year_of_release")
        private String yearOfRelease;

        public String getYearOfRelease() {
            return yearOfRelease;
        }

        public void setYearOfRelease(String yearOfRelease) {
            this.yearOfRelease = yearOfRelease;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
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

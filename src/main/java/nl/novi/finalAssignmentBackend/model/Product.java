package nl.novi.finalAssignmentBackend.model;




public abstract class Product {

    private Long id;

    private Integer price;

    private Integer originalStock;

    private String description;

    private String name;

    private Integer amountSold;



    public Product() {

    }


    public Product(Long id, Integer price, Integer originalStock, String description, String name, Integer amountSold){
        this.id = id;
        this.price = price;
        this.originalStock = originalStock;
        this.description = description;
        this.name = name;
        this.amountSold = amountSold;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

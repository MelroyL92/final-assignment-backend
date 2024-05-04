package nl.novi.finalAssignmentBackend.helper.deliveryHelpers;

import java.util.HashMap;
import java.util.Map;

public class ProductCountHelper {

    private Map<Long, Integer> productCounts;

    public ProductCountHelper(){
        this.productCounts = new HashMap<>();
    }


    public void increment (Long productId){
        productCounts.put(productId, productCounts.getOrDefault(productId, 0)+ 1);

    }

    public Map<Long, Integer> getProductCounts() {
        return productCounts;
    }
}

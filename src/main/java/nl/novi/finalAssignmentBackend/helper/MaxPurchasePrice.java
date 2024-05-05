package nl.novi.finalAssignmentBackend.helper;

import nl.novi.finalAssignmentBackend.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MaxPurchasePrice {

    public Double isPurchasePriceValid(double purchasePrice, double sellingPrice) {

        double minPurchasePrice = sellingPrice * 0.75;
        if (purchasePrice > minPurchasePrice) {
            throw new RecordNotFoundException("Purchase price cannot less then 25% under the selling price to maintain a profit margin");
        }
        return purchasePrice;
    }
}


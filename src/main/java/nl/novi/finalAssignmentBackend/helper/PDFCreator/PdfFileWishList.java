package nl.novi.finalAssignmentBackend.helper.PDFCreator;

import nl.novi.finalAssignmentBackend.entities.Game;
import nl.novi.finalAssignmentBackend.entities.Movie;
import nl.novi.finalAssignmentBackend.entities.ShoppingList;
import nl.novi.finalAssignmentBackend.exceptions.RecordNotFoundException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PdfFileWishList {

    public void createPdf(ShoppingList shoppingList) throws IOException {

        if (shoppingList.getUser() == null) {
            throw new RecordNotFoundException("Wishlist does not have a user associated with it.");
        }


        if(!shoppingList.getType().contains("wishlist")){
            throw new  RecordNotFoundException("you cant make a pdf file from anything but a wishlist");
        }

        boolean hasGamesOrMovies = false;
            if (!shoppingList.getGames().isEmpty() || !shoppingList.getMovies().isEmpty()) {
                hasGamesOrMovies = true;
            }
            if (!hasGamesOrMovies) {
            throw new RecordNotFoundException("Wishlist does not contain any games or movies.");
            }


        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_BOLD), 12);

                float yPosition = 700;
                contentStream.beginText();
                contentStream.newLineAtOffset(100, yPosition);
                contentStream.showText("Ordernumber: " + shoppingList.getId());
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Ordered by: " + shoppingList.getUser().getUsername());
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Type : " + shoppingList.getType());
                contentStream.newLineAtOffset(0, -50);

                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Subtotal: €" + shoppingList.getSubtotal());
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Delivery cost: €" + shoppingList.getDeliveryCost());
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Packaging cost: €" + shoppingList.getPackagingCost());
                yPosition -= 20;
                contentStream.newLineAtOffset(0, -40);


                for (Game game : shoppingList.getGames()) {
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Game: " + game.getName() + " - Price: €" + game.getSellingPrice());
                    yPosition -= 20;
                }

                for (Movie movie : shoppingList.getMovies()) {
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("Movie: " + movie.getName() + " - Price: €" + movie.getSellingPrice());
                    yPosition -= 20;
                }
                contentStream.newLineAtOffset(0, -40);
                contentStream.showText("Total price: €" + (shoppingList.getPackagingCost() + shoppingList.getDeliveryCost() + shoppingList.getSubtotal()));
                contentStream.endText();
            }
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            document.save("wishlist_" + shoppingList.getId() +  "_" + timestamp + ".pdf");
        }
    }

    public void createPDFFromWishlist(ShoppingList shoppingList) {
        if (shoppingList.getCreatePdf() && shoppingList.getType().contains("wishlist")) {
            PdfFileWishList pdfFileWishList = new PdfFileWishList();
            try {
                pdfFileWishList.createPdf(shoppingList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

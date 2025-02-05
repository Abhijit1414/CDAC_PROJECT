package com.marketplace.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.marketplace.pojos.CartItem;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class PdfExportService {

    // Export the cart items as a PDF receipt
    public boolean export(List<CartItem> items) {
        try {
            // Add a logo to the PDF
            Image logo = Image.getInstance("fmj.jpg");
            logo.scaleAbsolute(50, 50);
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("receipt.pdf"));
            document.open();

            // Add company name and other details
            Font font = FontFactory.getFont(FontFactory.COURIER, 20, BaseColor.BLACK);
            Paragraph p = new Paragraph();
            p.setTabSettings(new TabSettings(56f));
            p.add(Chunk.TABBING);
            p.add(new Chunk("Farmers Marketplace", font));
            document.add(p);

            // Add dotted line separator
            Chunk linebreak = new Chunk(new DottedLineSeparator());
            document.add(linebreak);

            // Create a table and add cart items
            PdfPTable table = new PdfPTable(4);
            addTableHeader(table);
            addRows(table, items);

            document.add(table);

            // Calculate and add the total amount
            double grandTotal = 0.0;
            for (CartItem item : items) {
                grandTotal += item.getAmount();
            }

            for (int i = 0; i < 10; i++) {
                document.add(Chunk.NEWLINE);
            }

            // Add total amount
            Chunk total = new Chunk("Total Amount: " + grandTotal, font);
            document.add(total);

            document.close();
            System.out.println("PDF generated successfully.");
            return true;
        } catch (DocumentException | IOException | URISyntaxException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Add table header
    private void addTableHeader(PdfPTable table) {
        Stream.of("Product Name", "Quantity", "Price", "Amount").forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(columnTitle));
            table.addCell(header);
        });
    }

    // Add rows with cart item data
    private void addRows(PdfPTable table, List<CartItem> items) throws URISyntaxException, BadElementException, MalformedURLException, IOException {
        for (CartItem cartItem : items) {
            table.addCell(cartItem.getItem());
            table.addCell(String.valueOf(cartItem.getQty()));
            table.addCell(String.valueOf(cartItem.getPrice()));
            table.addCell(String.valueOf(cartItem.getAmount()));
        }
    }
}

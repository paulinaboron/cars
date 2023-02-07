import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Time;
import java.util.ArrayList;

public class Invoice {
    long time; // timestamp wygenerowania faktury, System.currentTimeMillis()
    String title; // tytuł/numer faktury
    String seller; // sprzedawca
    String buyer; // nabywca
    ArrayList<Car> list;

    public Invoice(String seller, String buyer, ArrayList<Car> list) {
        long t = System.currentTimeMillis();
        this.time = t;
        this.title = "invoice" + t + ".pdf";
        this.seller = seller;
        this.buyer = buyer;
        this.list = list;
    }

    public float getFullPrice(){
        float sum = 0;
        for(Car c : list){
            sum += c.price + (c.price * c.vat/100);
        }
        return sum;
    }

    public String generatePdf(String subtitle) throws FileNotFoundException, DocumentException {
        Document document = new Document(); // dokument pdf
        String path = "src/main/resources/public/pdfs/" + subtitle + time + ".pdf"; // lokalizacja zapisu
        PdfWriter.getInstance(document, new FileOutputStream(path));
        document.open();

        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Faktura: VAT/" + time, font); // akapit
        document.add(chunk);

        Paragraph p = new Paragraph("Nabywca: " + buyer, font);
        document.add(p);

        Paragraph p2 = new Paragraph("Sprzedawca: " + seller, font);
        document.add(p2);

        Font redFont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.RED);
        Paragraph p3 = new Paragraph(subtitle + "\n", redFont);
        document.add(p3);

        PdfPTable table = new PdfPTable(4);
        PdfPCell c1 = new PdfPCell(new Phrase("lp", font));
        table.addCell(c1);
        PdfPCell c2 = new PdfPCell(new Phrase("cena", font));
        table.addCell(c2);
        PdfPCell c3 = new PdfPCell(new Phrase("vat", font));
        table.addCell(c3);
        PdfPCell c4 = new PdfPCell(new Phrase("wartosc", font));
        table.addCell(c4);

        int i = 1;
        for(Car car : list){
            PdfPCell cLp = new PdfPCell(new Phrase(String.valueOf(i), font));
            table.addCell(cLp);

            PdfPCell cPrice = new PdfPCell(new Phrase(String.valueOf(car.price), font));
            table.addCell(cPrice);

            PdfPCell cVat = new PdfPCell(new Phrase(car.vat + "%", font));
            table.addCell(cVat);

            PdfPCell cValue = new PdfPCell(new Phrase(String.valueOf(car.price + car.price*car.vat/100), font));
            table.addCell(cValue);

            i += 1;
        }

        document.add(table);

        Paragraph p4 = new Paragraph("Do zaplaty: " + this.getFullPrice(), font);
        document.add(p4);

        document.close();
        return subtitle + time + ".pdf";
    }
}

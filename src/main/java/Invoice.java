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
}

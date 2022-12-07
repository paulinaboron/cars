import java.sql.Time;
import java.util.ArrayList;

public class Invoice {
    Time time; // timestamp wygenerowania faktury, System.currentTimeMillis()
    String title; // tytuł/numer faktury
    String seller; // sprzedawca
    String buyer; // nabywca
    ArrayList<Car> list;
}

import java.util.concurrent.ThreadLocalRandom;

public class CustomDate {
    private int day;
    private int month;
    private int year;

    public CustomDate() {
        this.day = ThreadLocalRandom.current().nextInt(1, 31);
        this.month = ThreadLocalRandom.current().nextInt(1, 13);
        this.year = ThreadLocalRandom.current().nextInt(2000, 2022);
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }
}

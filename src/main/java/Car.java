import com.fasterxml.uuid.Generators;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

class Car{
    int id;
    UUID uuid;
    String model;
    int year;
    String color;
    ArrayList<Airbag> airbags;
    boolean invoice;
    int price;
    int vat;
    CustomDate date;
    String img;
    ArrayList<String> photos;

    public Car() {
        if (App.cars.isEmpty()) {
            this.id = 1;
        }else{
            this.id = App.cars.get(App.cars.size()-1).id + 1;
        }
        this.uuid = Generators.randomBasedGenerator().generate();
        this.invoice = false;
    }

    public Car( String model, int year, String color, ArrayList<Airbag> airbags) {
        if (App.cars.isEmpty()) {
            this.id = 1;
        }else{
            this.id = App.cars.get(App.cars.size()-1).id + 1;
        }
        this.uuid = Generators.randomBasedGenerator().generate();
        this.model = model;
        this.year = year;
        this.color = color;
        this.airbags = airbags;
        this.invoice = false;
        this.price = ThreadLocalRandom.current().nextInt(10000, 50000 + 1);
        this.vat = App.vatList.get(ThreadLocalRandom.current().nextInt(0, 3));
        this.date = new CustomDate();
        this.photos = new ArrayList<>();

        if(App.models.contains(this.model)){
            img = App.modelsMap.get(this.model).substring(25);
        }else{
            img = "/imgs/car.png";
        }
    }

    public int getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public ArrayList<Airbag> getAirbags() {
        return airbags;
    }

    public int getPrice() {
        return price;
    }

    public int getVat() {
        return vat;
    }

    public CustomDate getDate() {
        return date;
    }

    public void setInvoice(boolean invoice) {
        this.invoice = invoice;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", airbags=" + airbags +
                '}';
    }
}

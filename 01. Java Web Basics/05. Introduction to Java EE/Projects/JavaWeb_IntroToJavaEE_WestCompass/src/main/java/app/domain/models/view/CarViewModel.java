package app.domain.models.view;

public class CarViewModel {
    private String model;
    private String brand;
    private int year;
    private String engine;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "<li class=\"d-flex justify-content-around\">" +
                "    <div class=\"col-md-4 d-flex flex-column text-center mb-3\">" +
                "        <h4 class=\"text-white text-center\">Brand: " + this.getBrand() + "</h4>" +
                "        <h4 class=\"text-white text-center\">Model: " + this.getModel() + "</h4>" +
                "        <h4 class=\"text-white text-center\">Year: " + this.getYear() + "</h4>" +
                "        <h4 class=\"text-white text-center\">Engine: " + this.getEngine() + "</h4>" +
                "    </div>" +
                "</li>";
    }
}


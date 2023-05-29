package camp.it.shop.model;

public class Tires implements Writable {

    private String brand;
    private String productName;
    private String season;
    private String size;
    private int year;
    private int price;
    private int quantity;
    private String productCode;

    public Tires(String brand, String productName, String season, String size, int year, int price, int quantity, String productCode) {
        this.brand = brand;
        this.productName = productName;
        this.season = season;
        this.size = size;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
        this.productCode = productCode;
    }

    public Tires(String[] vars) {
        this(vars[0], vars[1], vars[2], vars[3], Integer.parseInt(vars[4]), Integer.parseInt(vars[5]), Integer.parseInt(vars[6]), vars[7]);


    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.brand)
                .append(" ")
                .append(this.productName)
                .append(" ")
                .append(" Season: ")
                .append(this.season)
                .append(" Size: ")
                .append(this.size)
                .append(" Production: ")
                .append(this.year)
                .append(" Price: ")
                .append(this.price)
                .append(" Available quantity: ")
                .append(this.quantity)
                .append(" Product code: ")
                .append(this.productCode)
                .toString();
    }

    @Override
    public String toCSV() {
        return new StringBuilder()
                .append(getClass().getSimpleName())
                .append(";")
                .append(this.brand)
                .append(";")
                .append(this.productName)
                .append(";")
                .append(this.season)
                .append(";")
                .append(this.size)
                .append(";")
                .append(this.year)
                .append(";")
                .append(this.price)
                .append(";")
                .append(this.quantity)
                .append(";")
                .append(this.productCode)
                .toString();
    }
}







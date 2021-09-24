package comboComponents;

public class Beverage implements IComboComponent<Beverage> {

    private int code;
    private String name;
    private double price;

    public Beverage(Integer code, String name, Double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice(){
        return this.price;
    }

    @Override
    public Beverage clone() {
        return new Beverage(code, name, price);
    }

    @Override
    public Beverage deepClone() {
        return clone();
    }

    @Override
    public String toString() {
        return this.code + " - " + this.name + ": &emsp; " + this.getPrice();
    }

}
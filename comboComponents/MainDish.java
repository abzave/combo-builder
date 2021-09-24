package comboComponents;

public class MainDish implements IComboComponent<MainDish> {

    private int code;
    private String name;
    private double price;

    public MainDish(Integer code, String name, Double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice(){
        return this.price;
    }

    @Override
    public MainDish clone() {
        return new MainDish(code, name, price);
    }

    @Override
    public MainDish deepClone() {
        return clone();
    }

    @Override
    public String toString() {
        return this.code + " - " + this.name + ": &emsp; " + this.getPrice();
    }

}
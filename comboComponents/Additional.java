package comboComponents;

public class Additional implements IComboComponent<Additional>{

    private int code;
    private String name;
    private double price;

    public Additional(Integer code, String name, Double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice(){
        return this.price;
    }

    @Override
    public Additional clone() {
        return new Additional(code, name, price);
    }

    @Override
    public Additional deepClone() {
        return clone();
    }

    @Override
    public String toString() {
        return this.code + " - " + this.name + ": &emsp; " + this.getPrice();
    }

}
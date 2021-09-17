package comboComponents;

public class Beverage implements IComboComponent<Beverage> {

    public Beverage(int code, String name, double price) {
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

}
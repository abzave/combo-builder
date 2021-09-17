package comboComponents;

public class MainDish implements IComboComponent<MainDish> {

    public MainDish(int code, String name, double price) {
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

}
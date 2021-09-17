package comboComponents;

public class Additional implements IComboComponent<Additional>{

    public Additional(int code, String name, double price) {
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

}
package comboComponents;

public interface IComboComponent<T extends IComboComponent> extends IPrototype<T>  {
    protected int code;
    protected String name;
    protected double price;

    public double getPrice();
}
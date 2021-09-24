package comboComponents;

import patternInterfaces.IPrototype;

public interface IComboComponent<T extends IComboComponent> extends IPrototype<T>  {
    public double getPrice();
}
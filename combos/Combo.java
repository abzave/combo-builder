package combos;

import patternInterfaces.IPrototype;
import patternInterfaces.IBuilder;
import comboComponents.IComboComponent;
import java.util.List;

public class Combo implements IPrototype<Combo> {

    private IComboComponent mainDish;
    private List<IComboComponent> additionals;
    private List<IComboComponent> beverages;

    private Combo(
        IComboComponent mainDish, 
        List<IComboComponent> additionals, 
        List<IComboComponent> beverages
    ) {
        this.mainDish = mainDish;
        this.additionals = additionals;
        this.beverages = beverages;
    }

    public double getTotalPrice() {
        double totalPrice = mainDish.getPrice();

        additionals.forEach(additional -> {
            totalPrice += additional.getPrice();
        });
        beverages.forEach(beverage -> {
            totalPrice += beverage.getPrice();
        });
        
        return totalPrice;
    }

    @Override
    public Combo clone() {
        return new Combo(mainDish, additionals, beverages);
    }

    @Override
    public Combo deepClone() {
        List<IComboComponent> newAdditionals = new ArrayList();
        List<IComboComponent> newBeverages = new ArrayList();

        additionals.forEach(additional -> {
            newAdditionals.add(additional.deepClone());
        });
        beverages.forEach(beverage -> {
            newBeverages.add(beverage.deepClone());
        });

        return new Combo(mainDish.deepClone(), newAdditionals, newBeverages);
    }

    public static class ComboBuilder implements IBuilder<Combo> {

        private IComboComponent mainDish;
        private List<IComboComponent> additionals;
        private List<IComboComponent> beverages;

        public ComboBuilder setMainDish(IComboComponent mainDish) {
            this.mainDish = mainDish;
            return this;
        }

        public ComboBuilder addAdditional(IComboComponent additional) {
            this.additionals.add(additional);
            return this;
        }

        public ComboBuilder addBeverage(IComboComponent beverage) {
            this.beverages.add(beverage);
            return this;
        }

        @Override
        public Combo build() {
            return new Combo(mainDish, additionals, beverages);
        }

    }

}
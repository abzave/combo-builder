package combos;

import patternInterfaces.IPrototype;
import patternInterfaces.IBuilder;
import comboComponents.IComboComponent;

import java.util.List;
import java.util.ArrayList;

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

        for (IComboComponent additional : additionals) {
            totalPrice += additional.getPrice();
        }
        for (IComboComponent beverage : beverages) {
            totalPrice += beverage.getPrice();
        }
        
        return totalPrice;
    }

    public void addAdditional(IComboComponent additional) {
        this.additionals.add(additional);
    }

    public void addBeverage(IComboComponent beverage) {
        this.beverages.add(beverage);
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
            newAdditionals.add((IComboComponent) additional.deepClone());
        });
        beverages.forEach(beverage -> {
            newBeverages.add((IComboComponent) beverage.deepClone());
        });

        return new Combo((IComboComponent) mainDish.deepClone(), newAdditionals, newBeverages);
    }

    @Override
        public String toString() {
            StringBuilder itemList = new StringBuilder("Combo: &emsp; " + this.getTotalPrice() + "<br>");

            itemList.append("&emsp; " + mainDish.toString() + "<br>");

            for(IComboComponent additional : additionals){
                itemList.append("&emsp;" + additional.toString() + "<br>");
            }

            for(IComboComponent beverage : beverages){
                itemList.append("&emsp;" + beverage.toString() + "<br>");
            }
            
            return itemList.toString();
        }

    public static class ComboBuilder implements IBuilder<Combo> {

        private IComboComponent mainDish;
        private List<IComboComponent> additionals = new ArrayList<>();
        private List<IComboComponent> beverages = new ArrayList<>();

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
package combos;

import combos.Combo;

import java.util.List;
import java.util.ArrayList;

public class Cart {
    private static List<Combo> items = new ArrayList<>();

    public static double getTotalPrice() {
        double totalPrice = 0;

        for(Combo combo : items){
            totalPrice += combo.getTotalPrice();
        }
        
        return totalPrice;
    }

    public static void addItem(Combo combo) {
        items.add(combo);
    }

    public static String listItems() {
        StringBuilder itemList = new StringBuilder("<html>Carrito:<br>");

        for(Combo combo : items){
            itemList.append(combo.toString());
        }

        itemList.append("Total: &emsp;" + getTotalPrice() + "</html>");
        return itemList.toString();
    }

}
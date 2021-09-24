import combos.Combo;
import GUI.MenuWindow;
import comboComponents.Additional;
import comboComponents.Beverage;
import comboComponents.MainDish;
import comboComponents.IComboComponent;
import patternInterfaces.IPrototype;
import factories.PrototypeFactory;

import org.json.JSONObject;
import org.json.JSONArray;
import java.util.Iterator;
import IO.FileHandler;
import java.lang.reflect.Constructor;

public class Main {

    public static void main(String[] args) {
        String menuContent = FileHandler.read("menu.json");
        JSONObject jsonMenu = new JSONObject(menuContent);

        loadComponents(jsonMenu, "MainDish");
        loadComponents(jsonMenu, "Beverage");
        loadComponents(jsonMenu, "Additional");

        loadCombos(jsonMenu);

        MenuWindow window = new MenuWindow(jsonMenu);
    }

    private static void loadComponents(JSONObject menu, String key) {
        JSONArray components = menu.getJSONArray(key);
        Iterator<Object> componentIterator = components.iterator();

        while(componentIterator.hasNext()) {
            JSONObject component = (JSONObject) componentIterator.next();

            int code = component.getInt("code");
            String name = component.getString("name");
            double price = component.getDouble("price");

            try{
                Class<?> classObject = Class.forName("comboComponents." + key);
                Constructor<?> classConstructor = classObject.getConstructor(Integer.class, String.class, Double.class);

                IComboComponent instance = (IComboComponent) classConstructor.newInstance(code, name, price);
                PrototypeFactory.addElement(component.getString("name"), instance);
            } catch(Exception e) {
                System.out.println("comboComponents." + key + ": Class not found");
                System.out.println(e);
            }
        }
    }

    private static void loadCombos(JSONObject menu) {
        int counter = 1;

        JSONArray combos = menu.getJSONArray("Combo");
        Iterator<Object> comboIterator = combos.iterator();

        while(comboIterator.hasNext()) {
            JSONObject combo = (JSONObject) comboIterator.next();
            IComboComponent mainDish = (IComboComponent) PrototypeFactory.getElement(combo.getString("mainDish"));

            Combo.ComboBuilder builder = new Combo.ComboBuilder()
                .setMainDish(mainDish);

            JSONArray beverages = combo.getJSONArray("beverages");
            Iterator<Object> beveragesIterator = beverages.iterator();

            while(beveragesIterator.hasNext()) {
                String beverageName = (String) beveragesIterator.next();
                IComboComponent beverage = (IComboComponent) PrototypeFactory.getElement(beverageName);

                builder.addBeverage(beverage);
            }

            JSONArray additionals = combo.getJSONArray("additionals");
            Iterator<Object> additionalsIterator = additionals.iterator();

            while(additionalsIterator.hasNext()) {
                String additionalName = (String) additionalsIterator.next();
                IComboComponent additional = (IComboComponent) PrototypeFactory.getElement(additionalName);

                builder.addAdditional(additional);
            }

            IPrototype instance = builder.build();
            PrototypeFactory.addElement("Combo " + counter, instance);
            counter++;
        }
    }

}
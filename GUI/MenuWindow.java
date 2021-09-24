package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.Iterator;

import comboComponents.IComboComponent;
import factories.PrototypeFactory;
import combos.Combo;
import combos.Cart;

public class MenuWindow {

    private JFrame frame;
    private JLabel totalLabel;
    private JSONObject jsonMenu;
    private Combo lastCombo;

    public MenuWindow(JSONObject jsonMenu) {
        frame = new JFrame("Menu");
        this.jsonMenu = jsonMenu;

        buildLabels();
        buildComboButtons();
        buildComponentButtons("MainDish", 0);
        buildComponentButtons("Beverage", 1);
        buildComponentButtons("Additional", 2); 

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setSize(880, 495);
        frame.setVisible(true);
    }

    private void buildLabels() {
        JLabel comboLabel = new JLabel("Combos");
        comboLabel.setBounds(10, 10, 100, 20);

        JLabel mainDishLabel = new JLabel("Plato principal");
        mainDishLabel.setBounds(145, 10, 150, 20);

        JLabel beverageLabel = new JLabel("Bebida");
        beverageLabel.setBounds(290, 10, 150, 20);

        JLabel additionalLabel = new JLabel("Adicional");
        additionalLabel.setBounds(435, 10, 150, 20);

        totalLabel = new JLabel("Carrito: ");
        totalLabel.setBounds(580, 10, 290, 1080);
        totalLabel.setVerticalAlignment(SwingConstants.TOP);

        frame.add(comboLabel);
        frame.add(mainDishLabel);
        frame.add(beverageLabel);
        frame.add(additionalLabel);
        frame.add(totalLabel);
    }

    private void buildComboButtons() {
        int counter = 1;

        JSONArray combos = jsonMenu.getJSONArray("Combo");
        Iterator<Object> comboIterator = combos.iterator();

        while(comboIterator.hasNext()) {
            final String comboName = "Combo " + counter;
            StringBuilder comboDescription = new StringBuilder("<html>" + comboName + "<br>");
            
            JSONObject combo = (JSONObject) comboIterator.next();
            comboDescription.append(combo.getString("mainDish") + "<br>");

            JSONArray beverages = combo.getJSONArray("beverages");
            Iterator<Object> beveragesIterator = beverages.iterator();

            while(beveragesIterator.hasNext()) {
                String beverageName = (String) beveragesIterator.next();
                comboDescription.append(beverageName + "<br>");
            }

            JSONArray additionals = combo.getJSONArray("additionals");
            Iterator<Object> additionalsIterator = additionals.iterator();

            while(additionalsIterator.hasNext()) {
                String additionalName = (String) additionalsIterator.next();
                comboDescription.append(additionalName + "<br>");
            }

            comboDescription.append("</html>");

            JButton button = new JButton(comboDescription.toString());
            button.setBounds(10, 40 + ((counter - 1) * 85), 125, 75);
            button.addActionListener(event -> {
                lastCombo = (Combo) PrototypeFactory.getElement(comboName);
                Cart.addItem(lastCombo);
                totalLabel.setText(Cart.listItems());
            });

            frame.add(button);
            counter++;
        }
    }

    private void buildComponentButtons(String componentName, int row) {
        int counter = 0;

        JSONArray components = jsonMenu.getJSONArray(componentName);
        Iterator<Object> componentIterator = components.iterator();

        while(componentIterator.hasNext()) {
            JSONObject component = (JSONObject) componentIterator.next();

            String name = component.getString("name");

            JButton button = new JButton(name);
            button.setBounds(145 + (row * 145), 40 + (counter * 35), 135, 25);
            button.addActionListener(event -> {
                if (componentName.equals("Beverage")){
                    lastCombo.addBeverage((IComboComponent) PrototypeFactory.getElement(name));
                } else if (componentName.equals("Additional")) {
                    lastCombo.addAdditional((IComboComponent) PrototypeFactory.getElement(name));
                } else if (componentName.equals("MainDish")) {
                    lastCombo = new Combo.ComboBuilder()
                        .setMainDish((IComboComponent) PrototypeFactory.getElement(name))
                        .build();
                    Cart.addItem(lastCombo);
                }
                totalLabel.setText(Cart.listItems());
            });

            frame.add(button);
            counter++;
        }
    }

}
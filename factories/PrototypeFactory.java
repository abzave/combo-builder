package factories;

import java.util.HashMap;
import patternInterfaces.IPrototype;

public class PrototypeFactory {
    private static HashMap<String, IPrototype> prototypes = 
        new HashMap<>();

    public static void addElement(String key, IPrototype prototype) {
        prototypes.put(key, prototype);
    }

    public static IPrototype getElement(String key) {
        return prototypes.get(key).deepClone();
    }
}
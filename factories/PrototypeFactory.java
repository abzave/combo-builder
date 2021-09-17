package factories;

import java.util.HashMap;
import patternInterfaces.IPrototype;

public interface PrototypeFactory {
    private static HashMap<String, IPrototype> prototypes = 
        new HashMap<>();

    public static void addCombo(String key, IPrototype prototype) {
        prototypes.put(key, prototype);
    }

    public static IPrototype getCombo(String key) {
        return prototypes.get(key).deepClone();
    }
}
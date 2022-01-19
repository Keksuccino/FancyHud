//TODO übernehmen
package de.keksuccino.spiffyhud.api.placeholder;

import de.keksuccino.spiffyhud.SpiffyHud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaceholderTextRegistry {

    private static Map<String, PlaceholderTextContainer> placeholders = new HashMap<>();

    public static void registerPlaceholder(PlaceholderTextContainer placeholder) {
        if (placeholder != null) {
            if (placeholder.getIdentifier() != null) {
                if (placeholders.containsKey(placeholder.getIdentifier())) {
                    SpiffyHud.LOGGER.warn("[SPIFFY HUD] WARNING! A placeholder text with the identifier '" + placeholder.getIdentifier() + "' is already registered! Overriding item!");
                }
                placeholders.put(placeholder.getIdentifier(), placeholder);
            } else {
                SpiffyHud.LOGGER.error("[SPIFFY HUD] ERROR! Placeholder identifier cannot be null for PlaceholderTextContainers!");
            }
        }
    }

    /**
     * Unregister a previously added placeholder.
     */
    public static void unregisterPlaceholder(String placeholderIdentifier) {
        placeholders.remove(placeholderIdentifier);
    }

    /**
     * Get all registered placeholders as list.
     */
    public static List<PlaceholderTextContainer> getPlaceholders() {
        List<PlaceholderTextContainer> l = new ArrayList<>();
        l.addAll(placeholders.values());
        return l;
    }

    /**
     * Get a registered placeholder by its identifier.
     */
    public static PlaceholderTextContainer getPlaceholder(String placeholderIdentifier) {
        return placeholders.get(placeholderIdentifier);
    }

}

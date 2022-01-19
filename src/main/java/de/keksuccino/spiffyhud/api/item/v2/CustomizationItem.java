//TODO übernehmen
package de.keksuccino.spiffyhud.api.item.v2;

import de.keksuccino.konkrete.properties.PropertiesSection;
import de.keksuccino.spiffyhud.customization.items.CustomizationItemBase;

public abstract class CustomizationItem extends CustomizationItemBase {

    public CustomizationItemContainer parentItemContainer;

    public CustomizationItem(CustomizationItemContainer parentContainer, PropertiesSection item) {
        super(item);
        this.parentItemContainer = parentContainer;
        if (this.value == null) {
            this.value = parentContainer.getDisplayName();
        }
        if (this.width == -1) {
            this.width = 10;
        }
        if (this.height == -1) {
            this.height = 10;
        }
    }

}

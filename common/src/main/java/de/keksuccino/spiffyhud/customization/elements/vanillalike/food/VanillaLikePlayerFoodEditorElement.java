package de.keksuccino.spiffyhud.customization.elements.vanillalike.food;

import de.keksuccino.fancymenu.customization.element.AbstractElement;
import de.keksuccino.fancymenu.customization.element.editor.AbstractEditorElement;
import de.keksuccino.fancymenu.customization.layout.editor.LayoutEditorScreen;
import de.keksuccino.spiffyhud.util.SpiffyAlignment;
import org.jetbrains.annotations.NotNull;

public class VanillaLikePlayerFoodEditorElement extends AbstractEditorElement {

    public VanillaLikePlayerFoodEditorElement(@NotNull AbstractElement element, @NotNull LayoutEditorScreen editor) {
        super(element, editor);
        this.settings.setStretchable(false);
        this.settings.setAdvancedSizingSupported(false);
        this.settings.setResizeable(false);
    }

    @Override
    public void init() {

        super.init();

        this.rightClickMenu.addValueCycleEntry("body_alignment", SpiffyAlignment.TOP_LEFT.cycle(this.getElement().spiffyAlignment)
                        .addCycleListener(alignment -> {
                            editor.history.saveSnapshot();
                            this.getElement().spiffyAlignment = alignment;
                        }))
                .setStackable(false);

    }

    public VanillaLikePlayerFoodElement getElement() {
        return (VanillaLikePlayerFoodElement) this.element;
    }

}

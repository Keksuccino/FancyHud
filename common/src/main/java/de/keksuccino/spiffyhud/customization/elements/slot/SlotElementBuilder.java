package de.keksuccino.spiffyhud.customization.elements.slot;

import de.keksuccino.fancymenu.customization.element.AbstractElement;
import de.keksuccino.fancymenu.customization.element.ElementBuilder;
import de.keksuccino.fancymenu.customization.element.SerializedElement;
import de.keksuccino.fancymenu.customization.layout.editor.LayoutEditorScreen;
import de.keksuccino.fancymenu.util.LocalizationUtils;
import de.keksuccino.fancymenu.util.SerializationUtils;
import de.keksuccino.fancymenu.util.rendering.DrawableColor;
import de.keksuccino.spiffyhud.customization.SpiffyOverlayScreen;
import net.minecraft.network.chat.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Objects;

public class SlotElementBuilder extends ElementBuilder<SlotElement, SlotEditorElement> {

    private static final Logger LOGGER = LogManager.getLogger();

    public SlotElementBuilder() {
        super("spiffy_slot");
    }

    @Override
    public @NotNull SlotElement buildDefaultInstance() {
        SlotElement i = new SlotElement(this);
        i.baseWidth = 20;
        i.baseHeight = 20;
        i.inEditorColor = DrawableColor.of(new Color(140, 0, 98));
        i.stayOnScreen = false;
        i.stickyAnchor = true;
        return i;
    }

    @Override
    public SlotElement deserializeElement(@NotNull SerializedElement serialized) {

        SlotElement element = this.buildDefaultInstance();

        element.slot = Objects.requireNonNullElse(serialized.getValue("inventory_slot"), element.slot);
        element.useSelectedSlot = SerializationUtils.deserializeBoolean(element.useSelectedSlot, serialized.getValue("use_selected_slot"));

        return element;

    }

    @Override
    protected SerializedElement serializeElement(@NotNull SlotElement element, @NotNull SerializedElement serializeTo) {

        serializeTo.putProperty("inventory_slot", element.slot);
        serializeTo.putProperty("use_selected_slot", "" + element.useSelectedSlot);

        return serializeTo;

    }

    @Override
    public @NotNull SlotEditorElement wrapIntoEditorElement(@NotNull SlotElement element, @NotNull LayoutEditorScreen editor) {
        return new SlotEditorElement(element, editor);
    }

    @Override
    public @NotNull Component getDisplayName(@Nullable AbstractElement element) {
        return Component.translatable("spiffyhud.elements.slot");
    }

    @Override
    public @Nullable Component[] getDescription(@Nullable AbstractElement element) {
        return LocalizationUtils.splitLocalizedLines("spiffyhud.elements.slot.desc");
    }

    @Override
    public boolean shouldShowUpInEditorElementMenu(@NotNull LayoutEditorScreen editor) {
        return (editor.layoutTargetScreen instanceof SpiffyOverlayScreen);
    }

}

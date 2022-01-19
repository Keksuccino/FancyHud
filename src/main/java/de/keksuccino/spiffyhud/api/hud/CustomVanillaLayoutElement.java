package de.keksuccino.spiffyhud.api.hud;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.vertex.PoseStack;

import de.keksuccino.spiffyhud.SpiffyHud;
import de.keksuccino.spiffyhud.api.IngameHud;
import de.keksuccino.spiffyhud.customization.helper.editor.LayoutEditorScreen;
import de.keksuccino.spiffyhud.customization.helper.editor.elements.vanilla.VanillaLayoutElement;
import de.keksuccino.konkrete.properties.PropertiesSection;
import net.minecraft.client.Minecraft;

@Deprecated
public class CustomVanillaLayoutElement extends VanillaLayoutElement {

	public HudElementContainer container;

	@Deprecated
	public CustomVanillaLayoutElement(HudElementContainer container, CustomVanillaCustomizationItem object, LayoutEditorScreen handler) {
		super(object, handler);
		this.container = container;
		this.container.onInitEditor(this, this.rightclickMenu);
	}
	
	@Override
	public void render(PoseStack matrix, int mouseX, int mouseY) {
		
		super.render(matrix, mouseX, mouseY);

		if (IngameHud.getInstance().getGui().isEditor()) {
			Window w = Minecraft.getInstance().getWindow();
			this.container.element.render(matrix, w.getGuiScaledWidth(), w.getGuiScaledHeight(), Minecraft.getInstance().getFrameTime());
		}
		
	}

	@Override
	public List<PropertiesSection> getProperties() {
		
		List<PropertiesSection> l = new ArrayList<PropertiesSection>();
		PropertiesSection sec = new PropertiesSection("customization");
		
		sec.addEntry("action", "edit_" + this.container.elementIdentifier);
		
		if (!this.getVanillaObject().isOriginalOrientation) {
			sec.addEntry("orientation", this.object.orientation);
		}
		if (!this.getVanillaObject().isOriginalPosX) {
			sec.addEntry("x", "" + this.object.posX);
		}
		if (!this.getVanillaObject().isOriginalPosY) {
			sec.addEntry("y", "" + this.object.posY);
		}
		if (!this.getVanillaObject().vanillaVisible) {
			sec.addEntry("visible", "" + this.getVanillaObject().vanillaVisible);
		}
		
		Map<String, String> customProps = this.container.getProperties();
		if (customProps != null) {
			for (Map.Entry<String, String> m : customProps.entrySet()) {
				if (!sec.hasEntry(m.getKey())) {
					sec.addEntry(m.getKey(), m.getValue());
				} else {
					printPropertiesKeyError(m.getKey());
				}
			}
		}
		
		l.add(sec);
		
		return l;
		
	}
	
	protected void printPropertiesKeyError(String key) {
		SpiffyHud.LOGGER.error("[SPIFFY HUD] ERROR: CustomVanillaLayoutElement#getProperties():");
		SpiffyHud.LOGGER.error("Invalid properties key '" + key + "' found for HUD element '" + this.container.elementIdentifier + "'!");
		SpiffyHud.LOGGER.error("This key already exists or is reserved by the system and can't be used!");
	}

	@Override
	public void resetElement() {
		PropertiesSection props = new PropertiesSection("customization");
		this.object = new CustomVanillaCustomizationItem(this.container, props, false);
		this.container.onResetElement();
	}

}

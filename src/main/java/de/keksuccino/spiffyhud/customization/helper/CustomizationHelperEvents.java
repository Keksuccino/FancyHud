package de.keksuccino.spiffyhud.customization.helper;

import de.keksuccino.spiffyhud.SpiffyHud;
import de.keksuccino.spiffyhud.customization.helper.ui.UIBase;
import de.keksuccino.konkrete.gui.content.AdvancedButton;
import de.keksuccino.konkrete.input.StringUtils;
import de.keksuccino.konkrete.localization.Locals;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CustomizationHelperEvents {
	
	private static final ResourceLocation OPEN_HELPER_BUTTON_TEXTURE_IDLE = new ResourceLocation("spiffyhud", "/helper/openhelper_btn_idle.png");
	private static final ResourceLocation OPEN_HELPER_BUTTON_TEXTURE_HOVER = new ResourceLocation("spiffyhud", "/helper/openhelper_btn_hover.png");
	
	protected AdvancedButton openHelperButton;
	
	@SubscribeEvent
	public void onInitScreenPost(ScreenEvent.InitScreenEvent.Post e) {
		
		if (e.getScreen() instanceof PauseScreen) {

			if (SpiffyHud.config.getOrDefault("showcustomizationcontrols", true)) {

				int btnwidth = (int) (107 * UIBase.getUIScale());
				int btnheight = (int) (107 * UIBase.getUIScale());

				this.openHelperButton = new AdvancedButton(0, e.getScreen().height - btnheight , btnwidth, btnheight, "", false, (press) -> {
					Minecraft.getInstance().setScreen(new CustomizationHelperScreen());
				});
				this.openHelperButton.setBackgroundTexture(OPEN_HELPER_BUTTON_TEXTURE_IDLE, OPEN_HELPER_BUTTON_TEXTURE_HOVER);
				this.openHelperButton.setDescription(StringUtils.splitLines(Locals.localize("spiffyhud.helper.openhelper"), "%n%"));

				e.addListener(openHelperButton);

			}

		}
		
	}

}

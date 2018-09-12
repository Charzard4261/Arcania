package com.charzard.arcania.util.buttons;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;

public class GuiButtonTextured extends GuiButton {

	ResourceLocation resourceLocation;
	int textureX, textureY, hoverX, hoverY, enabledX, enabledY;
	boolean playsSound = true;

	public GuiButtonTextured(int buttonId, int x, int y, ResourceLocation resourceLocation, int textureX, int textureY, int width, int height)
	{
		super(buttonId, x, y, width, height, "");
		this.resourceLocation = resourceLocation;
		this.textureX = textureX;
		this.textureY = textureY;
		this.hoverX = textureX;
		this.hoverY = textureY;
		this.enabledX = textureX;
		this.enabledY = textureY;
	}

	public GuiButtonTextured(int buttonId, int x, int y, ResourceLocation resourceLocation, int textureX, int textureY, int width, int height,
			int hoverX, int hoverY)
	{
		super(buttonId, x, y, width, height, "");
		this.resourceLocation = resourceLocation;
		this.textureX = textureX;
		this.textureY = textureY;
		this.hoverX = hoverX;
		this.hoverY = hoverY;
		this.enabledX = textureX;
		this.enabledY = textureY;
	}
	
	public GuiButtonTextured(int buttonId, int x, int y, ResourceLocation resourceLocation, int textureX, int textureY, int width, int height,
			int hoverX, int hoverY, int enabledX, int enabledY)
	{
		super(buttonId, x, y, width, height, "");
		this.resourceLocation = resourceLocation;
		this.textureX = textureX;
		this.textureY = textureY;
		this.hoverX = textureX;
		this.hoverY = textureY;
		this.enabledX = enabledX;
		this.enabledY = enabledY;
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
	{
		if (this.visible)
		{
			FontRenderer fontrenderer = mc.fontRenderer;
			mc.getTextureManager().bindTexture(resourceLocation);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
			int i = this.getHoverState(this.hovered);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
					GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			if (i == 0)
				this.drawTexturedModalRect(this.x, this.y, enabledX, enabledY, this.width, this.height);
			if (i == 1)
				this.drawTexturedModalRect(this.x, this.y, textureX, textureY, this.width, this.height);
			else if (i == 2)
				this.drawTexturedModalRect(this.x, this.y, hoverX, hoverY, this.width, this.height);
			this.mouseDragged(mc, mouseX, mouseY);
			int j = 14737632;

//			if (packedFGColour != 0)
//			{
//				j = packedFGColour;
//			} else if (!this.enabled)
//			{
//				j = 10526880;
//			} else if (this.hovered)
//			{
//				j = 16777120;
//			}

			this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
		}
	}

	@Override
	public void playPressSound(SoundHandler soundHandlerIn)
	{
		if (playsSound)
			soundHandlerIn.playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
	}
	
	public void playClickSound(boolean bool)
	{
		playsSound = bool;
	}

}

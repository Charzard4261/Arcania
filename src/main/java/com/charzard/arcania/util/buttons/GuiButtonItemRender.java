package com.charzard.arcania.util.buttons;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiButtonItemRender extends GuiButton {

	Item item;
	boolean playsSound = true;

	public GuiButtonItemRender(int buttonId, int x, int y, Item item)
	{
		super(buttonId, x, y, 16, 16, "");
		this.item = item;
	}

	public GuiButtonItemRender(int buttonId, int x, int y, Block block)
	{
		super(buttonId, x, y, 16, 16, "");
		this.item = Item.getItemFromBlock(block);
	}
	
	public GuiButtonItemRender(int buttonId, int x, int y, Item item, String text)
	{
		super(buttonId, x, y, 16 + Minecraft.getMinecraft().fontRenderer.getStringWidth(text), 16, text);
		this.item = item;
	}

	public GuiButtonItemRender(int buttonId, int x, int y, Block block, String text)
	{
		super(buttonId, x, y, 16 + Minecraft.getMinecraft().fontRenderer.getStringWidth(text), 16, text);
		this.item = Item.getItemFromBlock(block);
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
	{
		if (this.visible)
		{
			FontRenderer fontrenderer = mc.fontRenderer;
			// GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
			int i = this.getHoverState(this.hovered);
			// GlStateManager.enableBlend();
			// GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
			// GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
			// GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			// GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA,
			// GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(item), this.x, this.y);
			this.mouseDragged(mc, mouseX, mouseY);
			int j = 0x000000;

			if (packedFGColour != 0)
			{
				j = packedFGColour;
			} else if (!this.enabled)
			{
				j = 10526880;
			} else if (this.hovered)
			{
				j = 0xFFFF50;
			}

			fontrenderer.drawString(this.displayString, this.x + 17, this.y + (this.height - 8) / 2, j, false);
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

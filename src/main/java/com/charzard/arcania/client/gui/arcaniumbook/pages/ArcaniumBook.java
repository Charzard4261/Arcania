package com.charzard.arcania.client.gui.arcaniumbook.pages;

import java.io.IOException;

import com.charzard.arcania.client.gui.arcaniumbook.ArcaniumBookGUI;
import com.charzard.arcania.client.gui.arcaniumbook.Page;
import com.charzard.arcania.items.ModItems;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ArcaniumBook extends Page {

	public ArcaniumBook(ArcaniumBookGUI gui)
	{
		super(gui);
	}

	@Override
	public void initGuiPage()
	{
		gui.getButtonList().add(home);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		int centerX = gui.width / 2, centerY = gui.height / 2;
		gui.drawCenteredString("Arcanium Book", leftTitleX - 4, titleY, 0x000000);
		gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(ModItems.ARCANIUM_BOOK), leftTitleX + 40, titleY - 4);

		gui.getFontRenderer().drawString("Leather, imbued with", leftX, lineOneY, 0x000000, false);
		gui.getFontRenderer().drawString("the magical properties", leftX, lineOneY + lineOffsetY, 0x000000, false);
		gui.getFontRenderer().drawString("of Arcanium, has let", leftX, lineOneY + lineOffsetY * 2, 0x000000, false);
		gui.getFontRenderer().drawString("me create a book", leftX, lineOneY + lineOffsetY * 3, 0x000000, false);
		gui.getFontRenderer().drawString("capable of editing", leftX, lineOneY + lineOffsetY * 4, 0x000000, false);
		gui.getFontRenderer().drawString("previous entries.", leftX, lineOneY + lineOffsetY * 5, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 6, 0x000000, false);
		gui.getFontRenderer().drawString("This should prove", leftX, lineOneY + lineOffsetY * 7, 0x000000, false);
		gui.getFontRenderer().drawString("itself to be useful in", leftX, lineOneY + lineOffsetY * 8, 0x000000, false);
		gui.getFontRenderer().drawString("aiding my research.", leftX, lineOneY + lineOffsetY * 9, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 10, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 11, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 12, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 13, 0x000000, false);

		gui.getFontRenderer().drawString("", rightX, lineOneY, 0x000000, false);
		gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY, 0x000000, false);
		gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY * 2, 0x000000, false);
		gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY * 3, 0x000000, false);
		gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY * 4, 0x000000, false);
		gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY * 5, 0x000000, false);
		gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY * 6, 0x000000, false);
		gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY * 7, 0x000000, false);
		gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY * 8, 0x000000, false);
		gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY * 9, 0x000000, false);
		gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY * 10, 0x000000, false);
		gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY * 11, 0x000000, false);
		gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY * 12, 0x000000, false);
		gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY * 13, 0x000000, false);
		
		gui.mc.getRenderItem().renderItemIntoGUI(new ItemStack(ModItems.ARCANIUM_POWDER), centerX + 45, centerY - 26 + 18);
		gui.mc.getRenderItem().renderItemIntoGUI(new ItemStack(Items.WRITABLE_BOOK), centerX + 63, centerY - 26 + 18);

		GlStateManager.pushMatrix();
		{
			GlStateManager.enableAlpha();
			gui.mc.getTextureManager().bindTexture(texture_1);
			gui.drawTexturedModalRect(centerX + 45, centerY - 26, 60, 190, 52, 52);
		}
		GlStateManager.popMatrix();

	}

	@Override
	public void actionPerformedPage(GuiButton button)
	{

	}

}

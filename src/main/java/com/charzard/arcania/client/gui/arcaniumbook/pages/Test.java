package com.charzard.arcania.client.gui.arcaniumbook.pages;

import java.io.IOException;

import com.charzard.arcania.capabilities.bookentry.BookEntryProvider;
import com.charzard.arcania.client.gui.arcaniumbook.ArcaniumBookGUI;
import com.charzard.arcania.client.gui.arcaniumbook.Page;
import com.charzard.arcania.items.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;

public class Test extends Page {

	public Test(ArcaniumBookGUI gui)
	{
		super(gui);
	}

	@Override
	public void initGuiPage()
	{
		int centerX = gui.width / 2, centerY = gui.height / 2;
		gui.getButtonList().add(forwards);
		gui.getButtonList().add(backwards);
		gui.getButtonList().add(home);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		if (Minecraft.getMinecraft().player.hasCapability(BookEntryProvider.ENTRIES, null))
			if (Minecraft.getMinecraft().player.getCapability(BookEntryProvider.ENTRIES, null).hasEntry("arcanium_powder"))
				gui.getFontRenderer().drawString("A", 0, 0, 0x000000, false);

		int centerX = gui.width / 2, centerY = gui.height / 2;
		gui.drawCenteredString("A Title", leftTitleX, titleY, 0x000000);
		gui.drawCenteredString("B Title", rightTitleX, titleY, 0x000000);
		gui.getFontRenderer().drawString("A", leftX, lineOneY, 0x000000, false);
		gui.getFontRenderer().drawString("B", rightX, lineOneY, 0x000000, false);

		gui.getFontRenderer().drawString("", leftX, lineOneY, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 2, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 3, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 4, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 5, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 6, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 7, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 8, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 9, 0x000000, false);
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

		GlStateManager.pushMatrix();
		{
			GlStateManager.enableAlpha();
			gui.mc.getTextureManager().bindTexture(texture_1);
			gui.drawTexturedModalRect(centerX + 45, centerY, 60, 190, 52, 52);
		}
		GlStateManager.popMatrix();

		gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(ModItems.ARCANIUM_POWDER), centerX + 45, centerY);
		gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(ModItems.ARCANIUM_POWDER), centerX + 63, centerY);
		gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(ModItems.ARCANIUM_POWDER), centerX + 81, centerY);
		gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(ModItems.ARCANIUM_POWDER), centerX + 45, centerY + 18);
		gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(ModItems.ARCANIUM_POWDER), centerX + 63, centerY + 18);
		gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(ModItems.ARCANIUM_POWDER), centerX + 81, centerY + 18);
		gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(ModItems.ARCANIUM_POWDER), centerX + 45, centerY + 36);
		gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(ModItems.ARCANIUM_POWDER), centerX + 63, centerY + 36);
		gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(ModItems.ARCANIUM_POWDER), centerX + 81, centerY + 36);
	}

	@Override
	public void actionPerformedPage(GuiButton button)
	{
		if (button.id == 100)
			gui.setPage(new Index(gui));

		else if (button.id == 101)
			gui.setPage(new ArcaniumPowder(gui));
	}

}

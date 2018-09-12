package com.charzard.arcania.client.gui.arcaniumbook.pages;

import java.io.IOException;

import com.charzard.arcania.client.gui.arcaniumbook.ArcaniumBookGUI;
import com.charzard.arcania.client.gui.arcaniumbook.Page;
import com.charzard.arcania.items.ModItems;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ArcaniumDust extends Page {

	public ArcaniumDust(ArcaniumBookGUI gui)
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
		gui.drawCenteredString("Arcanium Dust", leftTitleX - 4, titleY, 0x000000);
		gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(ModItems.ARCANIUM_DUST), leftTitleX + 40, titleY - 4);

		gui.getFontRenderer().drawString("Lapis has proven to", leftX, lineOneY, 0x000000, false);
		gui.getFontRenderer().drawString("be capable of", leftX, lineOneY + lineOffsetY, 0x000000, false);
		gui.getFontRenderer().drawString("extracting this dust", leftX, lineOneY + lineOffsetY * 2, 0x000000, false);
		gui.getFontRenderer().drawString("from the powder.", leftX, lineOneY + lineOffsetY * 3, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 4, 0x000000, false);
		gui.getFontRenderer().drawString("Arcanium Dust, though", leftX, lineOneY + lineOffsetY * 5, 0x000000, false);
		gui.getFontRenderer().drawString("not as strong as pure", leftX, lineOneY + lineOffsetY * 6, 0x000000, false);
		gui.getFontRenderer().drawString("Arcanium, can be used", leftX, lineOneY + lineOffsetY * 7, 0x000000, false);
		gui.getFontRenderer().drawString("as a substitute for", leftX, lineOneY + lineOffsetY * 8, 0x000000, false);
		gui.getFontRenderer().drawString("now.", leftX, lineOneY + lineOffsetY * 9, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 10, 0x000000, false);
		gui.getFontRenderer().drawString("This is very tedious,", leftX, lineOneY + lineOffsetY * 11, 0x000000, false);
		gui.getFontRenderer().drawString("and I should find a", leftX, lineOneY + lineOffsetY * 12, 0x000000, false);
		gui.getFontRenderer().drawString("way of automating", leftX, lineOneY + lineOffsetY * 13, 0x000000, false);

		gui.getFontRenderer().drawString("production before I", rightX, lineOneY, 0x000000, false);
		gui.getFontRenderer().drawString("progress too much.", rightX, lineOneY + lineOffsetY, 0x000000, false);
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

	}

	@Override
	public void actionPerformedPage(GuiButton button)
	{

	}

}

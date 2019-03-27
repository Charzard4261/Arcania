package com.charzard.arcania.client.gui.arcaniumbook.pages;

import java.io.IOException;

import com.charzard.arcania.client.gui.arcaniumbook.ArcaniumBookGUI;
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

		gui.getFontRenderer().drawString("So it seems Lapis is", leftX, lineOneY, 0x000000, false);
		gui.getFontRenderer().drawString("capable of extracting", leftX, lineOneY + lineOffsetY, 0x000000, false);
		gui.getFontRenderer().drawString("this dust from the", leftX, lineOneY + lineOffsetY * 2, 0x000000, false);
		gui.getFontRenderer().drawString("Arcanium Powder.", leftX, lineOneY + lineOffsetY * 3, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 4, 0x000000, false);
		gui.getFontRenderer().drawString("Arcanium Dust, though", leftX, lineOneY + lineOffsetY * 5, 0x000000, false);
		gui.getFontRenderer().drawString("not as strong as pure", leftX, lineOneY + lineOffsetY * 6, 0x000000, false);
		gui.getFontRenderer().drawString("Arcanium, can be used", leftX, lineOneY + lineOffsetY * 7, 0x000000, false);
		gui.getFontRenderer().drawString("as a substitute for", leftX, lineOneY + lineOffsetY * 8, 0x000000, false);
		gui.getFontRenderer().drawString("now.", leftX, lineOneY + lineOffsetY * 9, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 10, 0x000000, false);
		gui.getFontRenderer().drawString("This is very tedious to", leftX, lineOneY + lineOffsetY * 11, 0x000000, false);
		gui.getFontRenderer().drawString("create, and I should", leftX, lineOneY + lineOffsetY * 12, 0x000000, false);
		gui.getFontRenderer().drawString("find a way of", leftX, lineOneY + lineOffsetY * 13, 0x000000, false);

		gui.getFontRenderer().drawString("automating production", rightX, lineOneY, 0x000000, false);
		gui.getFontRenderer().drawString("before I bore myself", rightX, lineOneY + lineOffsetY, 0x000000, false);
		gui.getFontRenderer().drawString("to death.", rightX, lineOneY + lineOffsetY * 2, 0x000000, false);
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

		gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(ModItems.ARCANIUM_POWDER), centerX + 63, centerY);
		gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(ModItems.ARCANIUM_POWDER), centerX + 45, centerY + 18);
		gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.DYE, 1, 4), centerX + 63, centerY + 18);
		gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(ModItems.ARCANIUM_POWDER), centerX + 81, centerY + 18);
		gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.BOWL), centerX + 45, centerY + 36);
		gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(ModItems.ARCANIUM_POWDER), centerX + 63, centerY + 36);
		gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.FLINT), centerX + 81, centerY + 36);

		GlStateManager.pushMatrix();
		{
			GlStateManager.enableAlpha();
			gui.mc.getTextureManager().bindTexture(texture_1);
			gui.drawTexturedModalRect(centerX + 45, centerY, 60, 190, 52, 52);
		}
		GlStateManager.popMatrix();

	}

	@Override
	public void actionPerformedPage(GuiButton button)
	{

	}

}

package com.charzard.arcania.client.gui.arcaniumbook.pages;

import java.io.IOException;

import com.charzard.arcania.capabilities.bookentry.BookEntryProvider;
import com.charzard.arcania.client.gui.arcaniumbook.ArcaniumBookGUI;
import com.charzard.arcania.items.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ArcaniumPowder extends Page {

	public ArcaniumPowder(ArcaniumBookGUI gui)
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
		gui.drawCenteredString("Arcanium Powder", leftTitleX - 4, titleY, 0x000000);
		gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(ModItems.ARCANIUM_POWDER), leftTitleX + 40, titleY - 4);

		gui.getFontRenderer().drawString("It seems that Arcanium", leftX, lineOneY, 0x000000, false);
		gui.getFontRenderer().drawString("Ore is very brittle,", leftX, lineOneY + lineOffsetY, 0x000000, false);
		gui.getFontRenderer().drawString("making it hard to", leftX, lineOneY + lineOffsetY * 2, 0x000000, false);
		gui.getFontRenderer().drawString("gather in its raw form", leftX, lineOneY + lineOffsetY * 3, 0x000000, false);
		gui.getFontRenderer().drawString("through normal means.", leftX, lineOneY + lineOffsetY * 4, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 5, 0x000000, false);
		gui.getFontRenderer().drawString("When broken, the ore", leftX, lineOneY + lineOffsetY * 6, 0x000000, false);
		gui.getFontRenderer().drawString("drops this powder, a", leftX, lineOneY + lineOffsetY * 7, 0x000000, false);
		gui.getFontRenderer().drawString("mix of stone with small", leftX, lineOneY + lineOffsetY * 8, 0x000000, false);
		gui.getFontRenderer().drawString("traces of Arcanium.", leftX, lineOneY + lineOffsetY * 9, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 10, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 11, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 12, 0x000000, false);
		gui.getFontRenderer().drawString("", leftX, lineOneY + lineOffsetY * 13, 0x000000, false);

//		if (Minecraft.getMinecraft().player.getCapability(BookEntryProvider.ENTRIES, null).getEntryStage("arcanium_powder") == 1)
//		{
			gui.getFontRenderer().drawString("Perhaps another", rightX, lineOneY, 0x000000, false);
			gui.getFontRenderer().drawString("material could be used", rightX, lineOneY + lineOffsetY, 0x000000, false);
			gui.getFontRenderer().drawString("to displace the raw", rightX, lineOneY + lineOffsetY * 2, 0x000000, false);
			gui.getFontRenderer().drawString("Arcanium?", rightX, lineOneY + lineOffsetY * 3, 0x000000, false);
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
//		} else if (Minecraft.getMinecraft().player.getCapability(BookEntryProvider.ENTRIES, null).getEntryStage("arcanium_powder") == 2)
//		{
//			gui.getFontRenderer().drawString("Through smashing Lapis", rightX, lineOneY, 0x000000, false);
//			gui.getFontRenderer().drawString("in a bowl with the", rightX, lineOneY + lineOffsetY, 0x000000, false);
//			gui.getFontRenderer().drawString("powder, I have succeded", rightX, lineOneY + lineOffsetY * 2, 0x000000, false);
//			gui.getFontRenderer().drawString("in creating a more pure", rightX, lineOneY + lineOffsetY * 3, 0x000000, false);
//			gui.getFontRenderer().drawString("substance.", rightX, lineOneY + lineOffsetY * 4, 0x000000, false);
//			gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY * 5, 0x000000, false);
//			gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY * 6, 0x000000, false);
//			gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY * 7, 0x000000, false);
//			gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY * 8, 0x000000, false);
//			gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY * 9, 0x000000, false);
//			gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY * 10, 0x000000, false);
//			gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY * 11, 0x000000, false);
//			gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY * 12, 0x000000, false);
//			gui.getFontRenderer().drawString("", rightX, lineOneY + lineOffsetY * 13, 0x000000, false);
//
//			gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(ModItems.ARCANIUM_POWDER), centerX + 63, centerY);
//			gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(ModItems.ARCANIUM_POWDER), centerX + 45, centerY + 18);
//			gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.DYE, 1, 4), centerX + 63, centerY + 18);
//			gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(ModItems.ARCANIUM_POWDER), centerX + 81, centerY + 18);
//			gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.BOWL), centerX + 45, centerY + 36);
//			gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(ModItems.ARCANIUM_POWDER), centerX + 63, centerY + 36);
//			gui.mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(Items.FLINT), centerX + 81, centerY + 36);
//
//			GlStateManager.pushMatrix();
//			{
//				GlStateManager.enableAlpha();
//				gui.mc.getTextureManager().bindTexture(texture_1);
//				gui.drawTexturedModalRect(centerX + 45, centerY, 60, 190, 52, 52);
//			}
//			GlStateManager.popMatrix();
//		} else
//		{
//			gui.getFontRenderer().drawString("Stage is " + Minecraft.getMinecraft().player.getCapability(BookEntryProvider.ENTRIES, null).getEntryStage("arcanium_powder"), rightX, lineOneY, 0x000000, false);
//			gui.getFontRenderer().drawString("and it shouldn't be!", rightX, lineOneY + lineOffsetY, 0x000000, false);
//		}

	}

	@Override
	public void actionPerformedPage(GuiButton button)
	{

	}

}

package com.charzard.arcania.client.gui.arcaniumbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.charzard.arcania.client.gui.arcaniumbook.pages.Index;
import com.charzard.arcania.network.PacketGetEntries;
import com.charzard.arcania.network.PacketHandler;
import com.charzard.arcania.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class ArcaniumBookGUI extends GuiScreen {

	final ResourceLocation texture_1 = new ResourceLocation(Reference.MOD_ID, "textures/gui/arcanium_book_1.png");
	final ResourceLocation texture_2 = new ResourceLocation(Reference.MOD_ID, "textures/gui/arcanium_book_2.png");

	/** Width and height of the left page */
	int lwidth = 146, lheight = 180;
	/** Width and height of the right page */
	int rwidth = 145, rheight = 180;

	public Page page = new Index(this);

	public ArcaniumBookGUI()
	{
		PacketHandler.INSTANCE.sendToServer(new PacketGetEntries());
	}

	@Override
	public void initGui()
	{
		buttonList.clear();
		page.initGui();
		super.initGui();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		int centerX = width / 2, centerY = height / 2;

		mc.renderEngine.bindTexture(texture_2);
		drawTexturedModalRect(centerX, centerY - (rheight / 2), 0, 0, rwidth, rheight);
		mc.renderEngine.bindTexture(texture_1);
		drawTexturedModalRect(centerX - lwidth, centerY - (lheight / 2), 0, 0, lwidth, lheight);

		page.drawScreen(mouseX, mouseY, partialTicks);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void updateScreen()
	{
		super.updateScreen();
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		page.actionPerformed(button);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException
	{
		if (keyCode == 18)
			Minecraft.getMinecraft().displayGuiScreen(null);
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}

	@Override
	public void onGuiClosed()
	{
		super.onGuiClosed();
	}

	public FontRenderer getFontRenderer()
	{
		return fontRenderer;
	}

	public List<GuiButton> getButtonList()
	{
		return buttonList;
	}

	public void drawCenteredString(String text, int x, int y, int color)
	{
		fontRenderer.drawString(text, (float) (x - fontRenderer.getStringWidth(text) / 2), y, color, false);
	}

	public void setPage(Page page)
	{
		this.page = page;
		page.initGui();
	}

	public static ArrayList<String> getAllEntries()
	{
		ArrayList<String> entries = new ArrayList<String>();
		entries.add("arcanium_book");
		entries.add("arcanium_powder");
		return entries;
	}
}

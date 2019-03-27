package com.charzard.arcania.client.gui.researchtable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.charzard.arcania.client.gui.arcaniumbook.pages.Index;
import com.charzard.arcania.client.gui.arcaniumbook.pages.Page;
import com.charzard.arcania.network.PacketGetEntries;
import com.charzard.arcania.network.PacketHandler;
import com.charzard.arcania.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class ResearchTableGUI extends GuiScreen {
	// TODO fix RT left planks texture, or the right, also top of table isn't top
	// log texture
	String				woodType;
	ResourceLocation	logTexture, plankTexture;
	int					tableWidth	= 512, tableHeight = 256;

	/**
	 * Open a Research Table GUI
	 * 
	 * @param woodType
	 *            The filename suffix for the wood type of the table
	 */
	public ResearchTableGUI(String woodType)
	{
		this.woodType = woodType;
		logTexture = new ResourceLocation("minecraft", "textures/blocks/log_" + woodType + "_top.png");
		plankTexture = new ResourceLocation("minecraft", "textures/blocks/planks_" + woodType + ".png");
	}

	@Override
	public void initGui()
	{
		buttonList.clear();
		super.initGui();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		int centerX = width / 2, centerY = height / 2;
		/*
		 * 640x339 width height 427x240
		 */
		// System.out.println(width);
		// System.out.println(height);
		GlStateManager.pushMatrix();
		{
			float scaleX = 0.5f * ((float) width / 427);
			float scaleY = 0.5f * ((float) height / 240);
			GlStateManager.scale(scaleX, scaleY, 1);
			mc.renderEngine.bindTexture(logTexture);
			// drawTexturedModalRect((centerX) - ((tableWidth) / 2), (centerY) -
			// (tableHeight / 2), 0, 0, tableWidth, tableHeight);
			drawModalRectWithCustomSizedTexture((int) (centerX / scaleX) - (tableWidth / 2), (int) (centerY / scaleY) - (tableHeight / 2), 0, 0, 512, 256, 256, 256);
			mc.renderEngine.bindTexture(plankTexture);
			drawModalRectWithCustomSizedTexture((int) (centerX / scaleX) - ((tableWidth - 32) / 2), (int) (centerY / scaleY) - ((tableHeight - 32) / 2), 16, 16, tableWidth - 32, tableHeight - 32, 256,
					256);
			// drawModalRectWithCustomSizedTexture(centerX - ((tableWidth - 32) / 2),
			// centerY - ((tableHeight - 32) / 2), 16, 16, 480, 224, 256, 256); // tile is
			// 3x as large in fullscreen
		}
		GlStateManager.popMatrix();
		// x, y, u, v, ux, vy,
		// drawScaledCustomSizeModalRect(centerX - (tableWidth / 2), centerY -
		// (tableHeight / 2), 0, 0, 16, 16, 100, 100, 100, 100);
		// mc.renderEngine.bindTexture(texture_1);
		// drawTexturedModalRect(centerX - lwidth, centerY - (lheight / 2), 0, 0,
		// lwidth, lheight);

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
}

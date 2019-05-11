package com.charzard.arcania.client.gui.researchtable;

import java.io.IOException;
import java.util.List;

import com.charzard.arcania.blocks.blocks.researchtable.ResearchTableContainer;
import com.charzard.arcania.blocks.blocks.researchtable.TileEntityResearchTableLeft;
import com.charzard.arcania.util.Reference;
import com.charzard.arcania.util.handelers.CapabilityHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class ResearchTableGUI extends GuiContainer {
	// TODO fix RT left planks texture, or the right, also top of table isn't top
	// log texture
	String				woodType;
	ResourceLocation	logTexture, plankTexture, icons;
	int					tableWidth	= 384, tableHeight = 192;

	private TileEntityResearchTableLeft	te;
	private IInventory					playerInv;

	/**
	 * Open a Research Table GUI
	 * 
	 * @param woodType
	 *            The filename suffix for the wood type of the table
	 */
	public ResearchTableGUI(String woodType, IInventory playerInv, TileEntityResearchTableLeft te)
	{
		super(new ResearchTableContainer(playerInv, te));

		this.te = te;
		this.playerInv = playerInv;

		setGuiSize(512, 256);

		this.woodType = woodType;
		logTexture = new ResourceLocation("minecraft", "textures/blocks/log_" + woodType + "_top.png");
		plankTexture = new ResourceLocation("minecraft", "textures/blocks/planks_" + woodType + ".png");
		icons = new ResourceLocation(Reference.NAME, "textures/gui/researchtable/icons.png");
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

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
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
			// GlStateManager.scale(scaleX, scaleY, 1);
			mc.renderEngine.bindTexture(logTexture);
			// drawTexturedModalRect((centerX) - ((tableWidth) / 2), (centerY) -
			// (tableHeight / 2), 0, 0, tableWidth, tableHeight);
			drawModalRectWithCustomSizedTexture((int) (centerX /* / scaleX */) - (tableWidth / 2), (int) (centerY /* / scaleY */) - (tableHeight / 2), 0, 0, tableWidth, tableHeight, tableWidth / 2,
					tableHeight);
			mc.renderEngine.bindTexture(plankTexture);
			drawModalRectWithCustomSizedTexture((int) (centerX /* / scaleX */) - ((tableWidth - 24) / 2), (int) (centerY /* / scaleY */) - ((tableHeight - 24) / 2), 24, 24, tableWidth - 24,
					tableHeight - 24, tableWidth / 2, tableHeight);
			// drawModalRectWithCustomSizedTexture(centerX - ((tableWidth - 32) / 2),
			// centerY - ((tableHeight - 32) / 2), 16, 16, 480, 224, 256, 256); // tile is
			// 3x as large in fullscreen
		}
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		{
			mc.renderEngine.bindTexture(icons);
			if (te.inventory.getStackInSlot(0).isEmpty())
				drawModalRectWithCustomSizedTexture((int) centerX + 152, (int) centerY - 75, 0, 0, 16, 16, 128, 128);
		}
		GlStateManager.popMatrix();

		// x, y, u, v, ux, vy,
		// drawScaledCustomSizeModalRect(centerX - (tableWidth / 2), centerY -
		// (tableHeight / 2), 0, 0, 16, 16, 100, 100, 100, 100);
		// mc.renderEngine.bindTexture(texture_1);
		// drawTexturedModalRect(centerX - lwidth, centerY - (lheight / 2), 0, 0,
		// lwidth, lheight);
	}
}

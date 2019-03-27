package com.charzard.arcania.client.gui.arcaniumbook.pages;

import java.util.List;

import com.charzard.arcania.client.gui.arcaniumbook.ArcaniumBookGUI;
import com.charzard.arcania.util.Reference;
import com.charzard.arcania.util.buttons.GuiButtonTextured;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public abstract class Page {

	public ArcaniumBookGUI gui;
	public final ResourceLocation texture_1 = new ResourceLocation(Reference.MOD_ID, "textures/gui/arcaniumbook/arcanium_book_1.png");
	public final ResourceLocation texture_2 = new ResourceLocation(Reference.MOD_ID, "textures/gui/arcaniumbook/arcanium_book_2.png");
	public GuiButton forwards, backwards, home;
	public int leftX, rightX, leftTitleX, rightTitleX, titleY, lineOneY, lineOffsetY;

	public Page(ArcaniumBookGUI gui)
	{
		this.gui = gui;
	}

	/**
	 * Initialises default buttons and positions, then calls the initGuiPage() method
	 */
	public void initGui()
	{
		int centerX = gui.width / 2, centerY = gui.height / 2;

		gui.getButtonList().clear();

		home = new GuiButtonTextured(100, centerX + 102, centerY + 65, new ResourceLocation(Reference.MOD_ID, "textures/gui/arcaniumbook/arcanium_book_1.png"), 3,
				220, 18, 10, 26, 220);
		forwards = new GuiButtonTextured(101, centerX + 102, centerY + 65, new ResourceLocation(Reference.MOD_ID, "textures/gui/arcaniumbook/arcanium_book_1.png"),
				3, 194, 18, 10, 26, 194);
		backwards = new GuiButtonTextured(102, centerX - 121, centerY + 65,
				new ResourceLocation(Reference.MOD_ID, "textures/gui/arcaniumbook/arcanium_book_1.png"), 3, 207, 18, 10, 26, 207);

		leftX = centerX - 130;
		rightX = centerX + 15;
		leftTitleX = centerX - 73;
		rightTitleX = centerX + 73;
		titleY = centerY - 75;
		lineOneY = centerY - 59;
		lineOffsetY = 9;

		initGuiPage();
	}

	public abstract void initGuiPage();

	public void actionPerformed(GuiButton button)
	{
		if (button.id == 100)
			gui.setPage(new Index(gui));

		actionPerformedPage(button);
	}

	public abstract void actionPerformedPage(GuiButton button);

	/**
	 * Draws the Page
	 * 
	 * @param mouseX		The mouse's current X
	 * @param mouseY		The mouse's current Y
	 * @param partialTicks
	 */
	public abstract void drawScreen(int mouseX, int mouseY, float partialTicks);

	
	/**
	 * Draws a tooltip at specified location
	 * 
	 * @param lines			The tooltip's text
	 * @param mouseX		The mouse's current X
	 * @param mouseY		The mouse's current Y
	 * @param posX			The tooltip's X
	 * @param posY			The tooltip's Y
	 * @param width			The tooltip's width
	 * @param height		The tooltip's height
	 */
	public void drawTooltip(List<String> lines, int mouseX, int mouseY, int posX, int posY, int width, int height)
	{
		if (mouseX >= posX && mouseX <= posX + width && mouseY >= posY && mouseY <= posY + height)
		{
			gui.drawHoveringText(lines, mouseX, mouseY);
		}
	}
}

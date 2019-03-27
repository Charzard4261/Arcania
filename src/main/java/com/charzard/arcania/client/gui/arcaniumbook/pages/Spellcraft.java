package com.charzard.arcania.client.gui.arcaniumbook.pages;

import com.charzard.arcania.client.gui.arcaniumbook.ArcaniumBookGUI;
import com.charzard.arcania.util.buttons.GuiButtonItemRender;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class Spellcraft extends Page {

	GuiButtonItemRender book, powder, dust;

	public Spellcraft(ArcaniumBookGUI gui)
	{
		super(gui);
	}

	@Override
	public void initGuiPage()
	{
		
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		int centerX = gui.width / 2, centerY = gui.height / 2;
		gui.drawCenteredString("Spellcraft", leftTitleX, titleY, 0x000000);

	}

	@Override
	public void actionPerformedPage(GuiButton button)
	{
		if (button.id == book.id)
			gui.setPage(new ArcaniumBook(gui));
		if (button.id == powder.id)
			gui.setPage(new ArcaniumPowder(gui));
		if (button.id == dust.id)
			gui.setPage(new ArcaniumDust(gui));
	}

}

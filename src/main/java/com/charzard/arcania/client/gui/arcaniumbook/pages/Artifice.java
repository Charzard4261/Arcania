package com.charzard.arcania.client.gui.arcaniumbook.pages;

import com.charzard.arcania.client.gui.arcaniumbook.ArcaniumBookGUI;
import com.charzard.arcania.util.buttons.GuiButtonItemRender;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class Artifice extends Page {

	GuiButtonItemRender book, powder, dust;

	public Artifice(ArcaniumBookGUI gui)
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
		gui.drawCenteredString("Artifice", leftTitleX, titleY, 0x000000);

		GlStateManager.pushMatrix();
		{
			GlStateManager.enableAlpha();
			GlStateManager.enableBlend();
			GlStateManager.color(1, 1, 1, 1);
			gui.mc.getTextureManager().bindTexture(texture_1);
			gui.drawTexturedModalRect(centerX + 45, centerY, 130, 190, 50, 4);
			gui.drawTexturedModalRect(rightTitleX - 3, lineOneY, 205, 190, 4, 20);
		}
		GlStateManager.popMatrix();

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

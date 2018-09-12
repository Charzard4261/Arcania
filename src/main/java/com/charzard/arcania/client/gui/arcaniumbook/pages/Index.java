package com.charzard.arcania.client.gui.arcaniumbook.pages;

import java.util.ArrayList;
import java.util.List;

import com.charzard.arcania.capabilities.bookentry.BookEntryProvider;
import com.charzard.arcania.client.gui.arcaniumbook.ArcaniumBookGUI;
import com.charzard.arcania.client.gui.arcaniumbook.Page;
import com.charzard.arcania.items.ModItems;
import com.charzard.arcania.util.buttons.GuiButtonItemRender;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class Index extends Page {

	GuiButtonItemRender book, powder, dust;

	public Index(ArcaniumBookGUI gui)
	{
		super(gui);
	}

	@Override
	public void initGuiPage()
	{
		List<GuiButton> buttons = new ArrayList<GuiButton>();

		book = new GuiButtonItemRender(0, leftX - 2, 0, ModItems.ARCANIUM_BOOK, "Arcanium Book");
		book.playClickSound(false);

		powder = new GuiButtonItemRender(1, leftX - 2, 0, ModItems.ARCANIUM_POWDER, "Arcanium Powder");
		powder.playClickSound(false);

		dust = new GuiButtonItemRender(2, leftX - 2, 0, ModItems.ARCANIUM_DUST, "Arcanium Dust");
		dust.playClickSound(false);

		if (Minecraft.getMinecraft().player.hasCapability(BookEntryProvider.ENTRIES, null))
		{
			buttons.add(book);
			if (Minecraft.getMinecraft().player.getCapability(BookEntryProvider.ENTRIES, null).hasEntry("arcanium_powder"))
				buttons.add(powder);
			buttons.add(dust);
		}

		for (GuiButton button : buttons)
		{
			button.y = (lineOneY - 5) + (14 * buttons.indexOf(button));
		}

		gui.getButtonList().addAll(buttons);

	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		int centerX = gui.width / 2, centerY = gui.height / 2;
		gui.drawCenteredString("Index", leftTitleX, titleY, 0x000000);

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

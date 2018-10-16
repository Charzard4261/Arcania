package com.charzard.arcania.capabilities;

import com.charzard.arcania.capabilities.arcana.IArcana;
import com.charzard.arcania.capabilities.arcana.Arcana;
import com.charzard.arcania.capabilities.arcana.ArcanaStorage;
import com.charzard.arcania.capabilities.bookentry.BookEntry;
import com.charzard.arcania.capabilities.bookentry.BookEntryStorage;
import com.charzard.arcania.capabilities.bookentry.IBookEntry;

import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModCapabilities {

	public static void registerCapabilities()
	{
		CapabilityManager.INSTANCE.register(IBookEntry.class, new BookEntryStorage(), BookEntry.class);
        CapabilityManager.INSTANCE.register(IArcana.class, new ArcanaStorage(), Arcana.class);
	}

}

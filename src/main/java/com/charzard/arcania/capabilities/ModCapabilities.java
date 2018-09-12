package com.charzard.arcania.capabilities;

import com.charzard.arcania.capabilities.bookentry.BookEntry;
import com.charzard.arcania.capabilities.bookentry.BookEntryStorage;
import com.charzard.arcania.capabilities.bookentry.IBookEntry;

import net.minecraftforge.common.capabilities.CapabilityManager;

public class ModCapabilities {

	public static void registerCapabilities()
	{
		CapabilityManager.INSTANCE.register(IBookEntry.class, new BookEntryStorage(), BookEntry.class);
        CapabilityManager.INSTANCE.register(IMana.class, new ManaStorage(), Mana.class);
	}

}

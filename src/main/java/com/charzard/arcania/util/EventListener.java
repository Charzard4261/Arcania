package com.charzard.arcania.util;

import com.charzard.arcania.capabilities.arcana.IArcana;
import com.charzard.arcania.capabilities.arcana.ArcanaProvider;
import com.charzard.arcania.capabilities.bookentry.BookEntryProvider;
import com.charzard.arcania.capabilities.bookentry.IBookEntry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemPickupEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

@EventBusSubscriber
public class EventListener {

	@SubscribeEvent
	public void onPickup(ItemPickupEvent event)
	{
		if (event.player.hasCapability(BookEntryProvider.ENTRIES, null))
		{
			if (event.getStack().getItem() instanceof ItemBlock)
				event.player.getCapability(BookEntryProvider.ENTRIES, null).unlockEntry(event.getStack().getUnlocalizedName().substring(5));
			else
				event.player.getCapability(BookEntryProvider.ENTRIES, null).unlockEntry(event.getStack().getUnlocalizedName().substring(5));
		}
	}

	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event)
	{
		if (event.isWasDeath())
		{
			IBookEntry entries = event.getEntityPlayer().getCapability(BookEntryProvider.ENTRIES, null);
			IBookEntry oldEntries = event.getOriginal().getCapability(BookEntryProvider.ENTRIES, null);
			entries.load(oldEntries.save());

			EntityPlayer player = event.getEntityPlayer();
			IArcana arcana = player.getCapability(ArcanaProvider.ARCANA_CAP, null);
			IArcana oldArcana = event.getOriginal().getCapability(ArcanaProvider.ARCANA_CAP, null);

			arcana.load(oldArcana.save());
		}
	}

	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent event)
	{
		EntityPlayer player = event.player;
		IArcana arcana = player.getCapability(ArcanaProvider.ARCANA_CAP, null);

		String message = String.format("Hello there, you have §7%d§r mana left.", (int) arcana.getArcana());
		player.sendMessage(new TextComponentString(message));
	}
}

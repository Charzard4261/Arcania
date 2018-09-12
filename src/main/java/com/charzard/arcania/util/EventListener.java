package com.charzard.arcania.util;

import com.charzard.arcania.capabilities.IMana;
import com.charzard.arcania.capabilities.ManaProvider;
import com.charzard.arcania.capabilities.bookentry.BookEntryProvider;
import com.charzard.arcania.capabilities.bookentry.IBookEntry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
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
			IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);
			IMana oldMana = event.getOriginal().getCapability(ManaProvider.MANA_CAP, null);

			mana.set(oldMana.getMana());
		}
	}

	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent event)
	{
		EntityPlayer player = event.player;
		IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);

		String message = String.format("Hello there, you have §7%d§r mana left.", (int) mana.getMana());
		player.sendMessage(new TextComponentString(message));
	}

	@SubscribeEvent
	public void onPlayerSleep(PlayerSleepInBedEvent event)
	{
		EntityPlayer player = event.getEntityPlayer();

		if (player.world.isRemote)
			return;

		IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);

		mana.fill(50);

		String message = String.format("You refreshed yourself in the bed. You received 50 mana, you have §7%d§r mana left.", (int) mana.getMana());
		player.sendMessage(new TextComponentString(message));
	}

	@SubscribeEvent
	public void onPlayerFalls(LivingFallEvent event)
	{
		Entity entity = event.getEntity();

		if (entity.world.isRemote || !(entity instanceof EntityPlayerMP) || event.getDistance() < 3)
			return;

		EntityPlayer player = (EntityPlayer) entity;
		IMana mana = player.getCapability(ManaProvider.MANA_CAP, null);

		float points = mana.getMana();
		float cost = event.getDistance() * 3;

		if (points > cost)
		{
			mana.consume(cost);

			String message = String.format("You absorbed fall damage. It costed §7%d§r mana, you have §7%d§r mana left.", (int) cost,
					(int) mana.getMana());
			player.sendMessage(new TextComponentString(message));

			event.setCanceled(true);
		}
	}
}

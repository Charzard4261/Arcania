package com.charzard.arcania.util.handelers;

import com.charzard.arcania.capabilities.arcana.ArcanaProvider;
import com.charzard.arcania.capabilities.bookentry.BookEntryProvider;
import com.charzard.arcania.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class CapabilityHandler {
	public static final ResourceLocation ENTRIES = new ResourceLocation(Reference.MOD_ID, "IBookEntry");
    public static final ResourceLocation ARCANA_CAP = new ResourceLocation(Reference.MOD_ID, "IArcana");

	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent<Entity> event)
	{
		if (!(event.getObject() instanceof EntityPlayer))
			return;
        event.addCapability(ARCANA_CAP, new ArcanaProvider());
		event.addCapability(ENTRIES, new BookEntryProvider());
	}
}

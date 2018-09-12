package com.charzard.arcania.proxy;

import com.charzard.arcania.capabilities.ModCapabilities;
import com.charzard.arcania.network.PacketHandler;
import com.charzard.arcania.util.EventListener;
import com.charzard.arcania.util.Reference;
import com.charzard.arcania.util.handelers.CapabilityHandler;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {

	public void registerItemRenderer(Item item, int meta, String id)
	{

	}
	
	public void registerItemRenderer(Item item, int meta, String id, String filename)
	{

	}

	public void preInit()
	{
		PacketHandler.registerMessages(Reference.MOD_ID);
	}

	public void init()
	{
		ModCapabilities.registerCapabilities();

		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
		MinecraftForge.EVENT_BUS.register(new EventListener());
	}
	
	public void postInit()
	{
		
	}

}

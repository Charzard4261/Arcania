package com.charzard.arcania;

import com.charzard.arcania.blocks.blocks.pedestal.TileEntityPedestal;
import com.charzard.arcania.blocks.blocks.researchtable.TileEntityResearchTableLeft;
import com.charzard.arcania.blocks.blocks.researchtable.TileEntityResearchTableRight;
import com.charzard.arcania.proxy.CommonProxy;
import com.charzard.arcania.util.Reference;
import com.charzard.arcania.world.ModWorldGen;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS)
public class Main {

	@Instance
	public static Main instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		// if (Minecraft.IS_RUNNING_ON_MAC) TODO disable because I don't like Macs
		GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
		GameRegistry.registerTileEntity(TileEntityPedestal.class, "arcania:pedestal");
		GameRegistry.registerTileEntity(TileEntityResearchTableLeft.class, "arcania:researchtable_left");
		GameRegistry.registerTileEntity(TileEntityResearchTableRight.class, "arcania:researchtable_right");

		proxy.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}

}

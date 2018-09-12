package com.charzard.arcania.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

	public static SimpleNetworkWrapper INSTANCE;
	
	private static int ID = 0;

	private static int nextID()
	{
		return ID++;
	}

	public static void registerMessages(String channelName)
	{
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
		
		INSTANCE.registerMessage(PacketGetEntries.Handler.class, PacketGetEntries.class, nextID(), Side.SERVER);
		
		INSTANCE.registerMessage(PacketReturnEntries.Handler.class, PacketReturnEntries.class, nextID(), Side.CLIENT);
	}

}

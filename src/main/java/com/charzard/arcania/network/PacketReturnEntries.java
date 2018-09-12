package com.charzard.arcania.network;

import java.util.HashMap;

import com.charzard.arcania.capabilities.bookentry.BookEntryProvider;
import com.charzard.arcania.client.gui.arcaniumbook.ArcaniumBookGUI;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketReturnEntries implements IMessage {

	private boolean messageValid;

	private HashMap<String, Integer> entries;

	public PacketReturnEntries()
	{
		messageValid = false;
	}

	public PacketReturnEntries(HashMap<String, Integer> entries)
	{
		this.entries = entries;
		messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		try
		{
			entries = new HashMap<String, Integer>();
			for (String name : ArcaniumBookGUI.getAllEntries()) {
				entries.put(name, buf.readInt());
			}
		} catch (IndexOutOfBoundsException iobe)
		{
			System.out.println(iobe.getStackTrace());
			return;
		}

		messageValid = true;
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		if (!this.messageValid)
			return;

		for (String name : ArcaniumBookGUI.getAllEntries())
			buf.writeInt(entries.get(name));

	}

	public static class Handler implements IMessageHandler<PacketReturnEntries, IMessage> {

		@Override
		public IMessage onMessage(PacketReturnEntries message, MessageContext ctx)
		{
			if (!message.messageValid && ctx.side != Side.CLIENT)
				return null;
			Minecraft.getMinecraft().addScheduledTask(() -> processMessage(message));
			return null;
		}

		void processMessage(PacketReturnEntries message)
		{
			try
			{
				Minecraft.getMinecraft().player.getCapability(BookEntryProvider.ENTRIES, null).setEntries(message.entries);
			} catch (Exception e)
			{
				System.out.println(e.getStackTrace());
			}
		}
	}
}

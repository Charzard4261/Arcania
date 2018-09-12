package com.charzard.arcania.network;

import java.util.UUID;

import com.charzard.arcania.capabilities.bookentry.BookEntryProvider;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class PacketGetEntries implements IMessage {

	private boolean messageValid;

	public PacketGetEntries()
	{
		messageValid = true;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		messageValid = true;
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		if (!this.messageValid)
			return;
	}

	public static class Handler implements IMessageHandler<PacketGetEntries, IMessage> {

		@Override
		public IMessage onMessage(PacketGetEntries message, MessageContext ctx)
		{
			if (!message.messageValid && ctx.side != Side.SERVER)
			return null;
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> processMessage(message, ctx));
			return null;
		}

		void processMessage(PacketGetEntries message, MessageContext ctx)
		{
			if (!ctx.getServerHandler().player.hasCapability(BookEntryProvider.ENTRIES, null))
				return;
			PacketHandler.INSTANCE.sendTo(new PacketReturnEntries(ctx.getServerHandler().player.getCapability(BookEntryProvider.ENTRIES, null).getEntries()), ctx.getServerHandler().player);
			
		}
	}

}

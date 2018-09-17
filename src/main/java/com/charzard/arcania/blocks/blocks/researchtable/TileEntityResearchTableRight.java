package com.charzard.arcania.blocks.blocks.researchtable;

import com.charzard.arcania.blocks.blocks.researchtable.ResearchTableLeft.EnumType;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityResearchTableRight extends TileEntity {

	EnumFacing facing = EnumFacing.NORTH;
	boolean drop = true;

	public void setFacing(EnumFacing facing)
	{
		this.facing = facing;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		facing = EnumFacing.byName(compound.getString("facing"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setString("facing", facing.name());
		return super.writeToNBT(compound);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return super.getCapability(capability, facing);
	}

	@Override
	public NBTTagCompound getUpdateTag()
	{
		NBTTagCompound compound = super.getUpdateTag();

		compound.setString("facing", facing.name());

		return compound;
	}

	@Override
	public void handleUpdateTag(NBTTagCompound tag)
	{
		readFromNBT(tag);
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		return new SPacketUpdateTileEntity(this.getPos(), 0, this.writeToNBT(new NBTTagCompound()));
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		handleUpdateTag(pkt.getNbtCompound());
	}
}

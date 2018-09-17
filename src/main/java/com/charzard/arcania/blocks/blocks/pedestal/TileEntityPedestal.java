package com.charzard.arcania.blocks.blocks.pedestal;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityPedestal extends TileEntity {

	ItemStackHandler inventory = new ItemStackHandler(1);

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		inventory.deserializeNBT(compound.getCompoundTag("inventory"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		compound.setTag("inventory", inventory.serializeNBT());
		return super.writeToNBT(compound);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory : super.getCapability(capability, facing);
	}

	/**
	 * Don't render the gem if the player is too far away
	 * 
	 * @return the maximum distance squared at which the TESR should render
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public double getMaxRenderDistanceSquared()
	{
		final int MAXIMUM_DISTANCE_IN_BLOCKS = 32;
		return MAXIMUM_DISTANCE_IN_BLOCKS * MAXIMUM_DISTANCE_IN_BLOCKS;
	}

	/**
	 * Return an appropriate bounding box enclosing the TESR This method is used to
	 * control whether the TESR should be rendered or not, depending on where the
	 * player is looking. The default is the AABB for the parent block, which might
	 * be too small if the TESR renders outside the borders of the parent block. If
	 * you get the boundary too small, the TESR may disappear when you aren't
	 * looking directly at it.
	 * 
	 * @return an appropriately size AABB for the TileEntity
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		// if your render should always be performed regardless of where the player is
		// looking, use infinite
		// Your should also change TileEntitySpecialRenderer.isGlobalRenderer().
		AxisAlignedBB infiniteExample = INFINITE_EXTENT_AABB;

		// our gem will stay above the block, up to 1 block higher, so our bounding box
		// is from [x,y,z] to [x+1, y+2, z+1]
		AxisAlignedBB aabb = new AxisAlignedBB(getPos(), getPos().add(1, 2, 1));
		return aabb;
	}


	@Override
	public NBTTagCompound getUpdateTag()
	{
		NBTTagCompound compound = super.getUpdateTag();

		compound.setTag("inventory", inventory.serializeNBT());

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

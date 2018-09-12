package com.charzard.arcania.blocks.blocks.researchtable;

import com.charzard.arcania.blocks.blocks.researchtable.ResearchTableLeft.EnumType;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityResearchTableLeft extends TileEntity {

	EnumFacing facing = EnumFacing.NORTH;

	public void setFacing(EnumFacing facing)
	{
		this.facing = facing;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		// inventory.deserializeNBT(compound.getCompoundTag("inventory"));
		System.out.println("READING COMPOUND " + compound);
		facing = EnumFacing.byName(compound.getString("facing"));
		System.out.println("IS FACING " + facing);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		// compound.setTag("inventory", inventory.serializeNBT());

		compound.setString("facing", facing.name());
//		System.out.println("WRITING WITH DIRECTION " + facing.name());
		System.out.println("COMPOUND WRITTEN TO" + compound);
		return super.writeToNBT(compound);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return /* capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || */super.hasCapability(capability, facing);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return /*
				 * capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory :
				 */super.getCapability(capability, facing);
	}
}

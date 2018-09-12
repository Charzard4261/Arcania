package com.charzard.arcania.capabilities.bookentry;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class BookEntryStorage implements IStorage<IBookEntry> {

	@Override
	public NBTBase writeNBT(Capability<IBookEntry> capability, IBookEntry instance, EnumFacing side)
	{
		return instance.save();
	}

	@Override
	public void readNBT(Capability<IBookEntry> capability, IBookEntry instance, EnumFacing side, NBTBase nbt)
	{
		instance.load(((NBTTagCompound) nbt));
	}
}

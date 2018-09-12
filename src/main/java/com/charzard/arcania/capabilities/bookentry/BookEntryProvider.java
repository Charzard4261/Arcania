package com.charzard.arcania.capabilities.bookentry;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class BookEntryProvider implements ICapabilitySerializable<NBTBase> {
	
	@CapabilityInject(IBookEntry.class)
	public static final Capability<IBookEntry> ENTRIES = null;

	private IBookEntry instance = ENTRIES.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == ENTRIES;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		return capability == ENTRIES ? ENTRIES.<T>cast(this.instance) : null;
	}

	@Override
	public NBTBase serializeNBT()
	{
		return ENTRIES.getStorage().writeNBT(ENTRIES, this.instance, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt)
	{
		ENTRIES.getStorage().readNBT(ENTRIES, this.instance, null, nbt);
	}
}

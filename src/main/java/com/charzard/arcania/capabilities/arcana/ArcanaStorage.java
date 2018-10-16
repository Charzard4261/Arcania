package com.charzard.arcania.capabilities.arcana;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ArcanaStorage implements IStorage<IArcana> {
	@Override
	public NBTBase writeNBT(Capability<IArcana> capability, IArcana instance, EnumFacing side)
	{
		return instance.save();
	}

	@Override
	public void readNBT(Capability<IArcana> capability, IArcana instance, EnumFacing side, NBTBase nbt)
	{
		instance.load(((NBTTagCompound) nbt));
	}
}
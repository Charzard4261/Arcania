package com.charzard.arcania.capabilities.arcana;

import java.util.Map.Entry;

import net.minecraft.nbt.NBTTagCompound;

public class Arcana implements IArcana {

	private int arcana = 100;
	private int arcanaCap = 100;
	private int level = 0;

	@Override
	public boolean drain(int amount)
	{
		if (arcana - amount < 0)
			return false;

		arcana -= amount;
		return true;
	}

	@Override
	public void fill(int amount)
	{
		arcana += amount;
	}

	@Override
	public void set(int amount)
	{
		arcana = amount;
	}

	@Override
	public void setMax(int max)
	{
		arcanaCap = max;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	@Override
	public int getArcana()
	{
		return arcana;
	}

	@Override
	public int getArcanaMax()
	{
		return arcanaCap;
	}

	public int getLevel()
	{
		return level;
	}

	@Override
	public NBTTagCompound save()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("arcanaCount", arcana);
		nbt.setInteger("arcanaMax", arcanaCap);
		nbt.setInteger("arcanaLevel", level);
		return nbt;
	}

	@Override
	public void load(NBTTagCompound nbt)
	{
		arcana = nbt.getInteger("arcanaCount");
		arcanaCap = nbt.getInteger("arcanaMax");
		level = nbt.getInteger("arcanaLevel");
	}

}
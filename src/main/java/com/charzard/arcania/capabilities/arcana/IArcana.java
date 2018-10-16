package com.charzard.arcania.capabilities.arcana;

import net.minecraft.nbt.NBTTagCompound;

public interface IArcana {

	/**
	 * Drains the amount set from Player's arcana if possible
	 * 
	 * @return True if the amount could be drained
	 * 
	 * @param amount
	 *            The amount of arcana to drain
	 */
	public boolean drain(int amount);

	public void fill(int amount);

	public void set(int amount);

	public void setMax(int max);

	public void setLevel(int level);

	public int getArcana();

	public int getArcanaMax();

	public int getLevel();

	/**
	 * Saves data to a NBTTagCompound
	 * 
	 * @return Saved data
	 */
	NBTTagCompound save();

	/**
	 * Loads data from an NBTTagCompound
	 * 
	 * @param nbt
	 *            Data to be loaded
	 */
	void load(NBTTagCompound nbt);
}
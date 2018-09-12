package com.charzard.arcania.capabilities.bookentry;

import java.util.HashMap;

import net.minecraft.nbt.NBTTagCompound;

public interface IBookEntry {

//	HashMap<String, Integer> entries = new HashMap<String, Integer>();
	
	/**
	 * Checks if the user has an entry unlocked
	 * 
	 * @param name	The name of the entry (lower case, words separated by underscores)
	 * 
	 * @return	
	 */
	boolean hasEntry(String name);
	
	/**
	 * Unlocks an entry
	 * 
	 * @param name	The name of the entry (lower case, words separated by underscores)
	 */
	void unlockEntry(String name);
	
	HashMap<String, Integer> getEntries();
	
	void setEntries(HashMap<String, Integer> entries);
	
	/**
	 * Saves data to a NBTTagCompound
	 * 
	 * @return	Saved data
	 */
	NBTTagCompound save();

	/**
	 * Loads data from an NBTTagCompound
	 * 
	 * @param nbt	Data to be loaded
	 */
	void load(NBTTagCompound nbt);

	
}
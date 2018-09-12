package com.charzard.arcania.capabilities.bookentry;

import java.util.HashMap;
import java.util.Map.Entry;

import com.charzard.arcania.client.gui.arcaniumbook.ArcaniumBookGUI;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class BookEntry implements IBookEntry {

	private HashMap<String, Integer> entries = new HashMap<String, Integer>();

	public BookEntry()
	{
		for (String entry : ArcaniumBookGUI.getAllEntries())
			entries.put(entry, 0);
	}

	@Override
	public boolean hasEntry(String name)
	{
		if (!entries.containsKey(name))
			return false;

		if (entries.get(name) != 0)
			return true;
		return false;
	}

	@Override
	public void unlockEntry(String name)
	{
		if (!entries.containsKey(name))
			return;

		if (entries.get(name) == 0)
		{
			entries.replace(name, 1);
		}
	}

	@Override
	public HashMap<String, Integer> getEntries()
	{
		return entries;
	}

	@Override
	public void setEntries(HashMap<String, Integer> entries)
	{
		this.entries = entries;
	}

	@Override
	public NBTTagCompound save()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		for (Entry<String, Integer> entry : entries.entrySet())
			nbt.setInteger(entry.getKey(), entry.getValue());
		// System.out.println("SAVED TO " + nbt);
		return nbt;
	}

	@Override
	public void load(NBTTagCompound nbt)
	{
		// System.out.println("BOOK ENTRY LOADING " + nbt);
		for (String name : entries.keySet())
			entries.replace(name, nbt.getInteger(name));
	}

}

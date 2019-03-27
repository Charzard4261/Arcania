package com.charzard.arcania.spells.superconstructors;

import net.minecraft.item.Item;

public class Shape {

	private String	entryName;
	private Item[]	unlock;
	private Item[]	cost;

	public Shape(String entryName, Item[] unlock, Item[] cost)
	{
		this.entryName = entryName;
		this.unlock = unlock;
		this.cost = cost;
	}

}

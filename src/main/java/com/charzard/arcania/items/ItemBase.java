package com.charzard.arcania.items;

import com.charzard.arcania.Arcania;
import com.charzard.arcania.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {

	public ItemBase(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(ModItems.arcaniaTab);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels()
	{
		Arcania.proxy.registerItemRenderer(this, 0, "inventory");
	}

}

package com.charzard.arcania.items.tools;

import com.charzard.arcania.Arcania;
import com.charzard.arcania.items.ModItems;
import com.charzard.arcania.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;

public class ToolPickaxe extends ItemPickaxe implements IHasModel {

	public ToolPickaxe(String name, ToolMaterial material)
	{
		super(material);
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

package com.charzard.arcania.items;

import java.util.ArrayList;
import java.util.List;

import com.charzard.arcania.items.items.ArcaniumBook;
import com.charzard.arcania.items.tools.ToolPickaxe;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	public static final CreativeTabs arcaniaTab = (new CreativeTabs("arcaniatab") {

		@Override
		public ItemStack getTabIconItem()
		{
			return new ItemStack(ARCANIUM_BOOK);
		}
	});

	public static final ToolMaterial MATERIAL_ARCANIUM_INGOT = EnumHelper.addToolMaterial("", 2, 250, 6, 2, 30);

	public static final Item ARCANIUM_BOOK = new ArcaniumBook();
	public static final Item ARCANIUM_DUST = new ItemBase("arcanium_dust");
	public static final Item ARCANIUM_POWDER = new ItemBase("arcanium_powder");
	public static final Item ARCANIUM_PICKAXE = new ToolPickaxe("arcanium_ingot_pickaxe", MATERIAL_ARCANIUM_INGOT);

}

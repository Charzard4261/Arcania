package com.charzard.arcania.blocks;

import com.charzard.arcania.Main;
import com.charzard.arcania.items.ModItems;
import com.charzard.arcania.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel {

	private boolean noItem = false;

	public BlockBase(String name, Material material, boolean noItem)
	{
		super(material);
		
		this.noItem = noItem;

		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(ModItems.arcaniaTab);

		ModBlocks.BLOCKS.add(this);
		if (!noItem)
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels()
	{
		if (!noItem)
			Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

}

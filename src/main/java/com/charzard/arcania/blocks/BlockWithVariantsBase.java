package com.charzard.arcania.blocks;

import com.charzard.arcania.items.ModItems;
import com.charzard.arcania.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class BlockWithVariantsBase extends Block implements IHasModel {

	public BlockWithVariantsBase(String name, Material material)
	{
		super(material);

		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(ModItems.arcaniaTab);

		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlockWithVariants(this));
	}

	@Override
	public abstract void registerModels();

}

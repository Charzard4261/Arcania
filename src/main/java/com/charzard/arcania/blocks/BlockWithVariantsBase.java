package com.charzard.arcania.blocks;

import com.charzard.arcania.items.ModItems;
import com.charzard.arcania.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class BlockWithVariantsBase extends Block implements IHasModel {

	private boolean noItem = false;

	public BlockWithVariantsBase(String name, Material material, boolean noItem)
	{
		super(material);

		this.noItem = noItem;

		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(ModItems.arcaniaTab);

		ModBlocks.BLOCKS.add(this);
		if (!noItem)
			ModItems.ITEMS.add(new ItemBlockWithVariants(this));
	}

	@Override
	public abstract void registerModels();
	
}

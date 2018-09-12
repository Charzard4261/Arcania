package com.charzard.arcania.blocks;

import com.charzard.arcania.util.IMetaName;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockWithVariants extends ItemBlock {

	public ItemBlockWithVariants(Block block)
	{
		super(block);
		setRegistryName(block.getRegistryName());
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return super.getUnlocalizedName() + "_" + ((IMetaName) this.block).getSpecialName(stack);
	}

	@Override
	public int getMetadata(int damage)
	{
		return damage;
	}

}

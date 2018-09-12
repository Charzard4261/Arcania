package com.charzard.arcania.blocks.blocks;

import java.util.Random;

import com.charzard.arcania.blocks.BlockBase;
import com.charzard.arcania.items.ModItems;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class ArcaniumOre extends BlockBase {

	public ArcaniumOre()
	{
		super("arcanium_ore", Material.ROCK);
		setHardness(3);
		setResistance(15);
		setHarvestLevel("pickaxe", 2);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		if (harvesters.get() != null)
		{
			if (harvesters.get() instanceof EntityPlayer)
			{
				if (((EntityPlayer) harvesters.get()).getHeldItemMainhand() != null)
				{
					if (((EntityPlayer) harvesters.get()).getHeldItemMainhand().getUnlocalizedName().toString().equals("item.arcanium_ingot_pickaxe"))
					{
						return ModItems.ARCANIUM_DUST;
					}
				}
			}
			return ModItems.ARCANIUM_POWDER;
		} else
			return ModItems.ARCANIUM_POWDER;
	}

	@Override
	public int quantityDropped(Random rand)
	{
		int max = 4;
		int min = 2;
		return rand.nextInt(max) + min;
	}

}

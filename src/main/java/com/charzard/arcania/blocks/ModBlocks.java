package com.charzard.arcania.blocks;

import java.util.ArrayList;
import java.util.List;

import com.charzard.arcania.blocks.blocks.ArcaniumOre;
import com.charzard.arcania.blocks.blocks.pedestal.Pedestal;
import com.charzard.arcania.blocks.blocks.researchtable.ResearchTableLeft;

import net.minecraft.block.Block;

public class ModBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block ARCANIUM_ORE = new ArcaniumOre();
	public static final Block PEDESTAL = new Pedestal();
	public static final Block RESEARCHTABLE_LEFT = new ResearchTableLeft();
	
}

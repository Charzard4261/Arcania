package com.charzard.arcania.client.gui;

import com.charzard.arcania.blocks.blocks.researchtable.ResearchTableContainer;
import com.charzard.arcania.blocks.blocks.researchtable.TileEntityResearchTableLeft;
import com.charzard.arcania.client.gui.researchtable.ResearchTableGUI;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler {

	public static final int RESEARCH_TABLE_ACACIA = 0;
	public static final int RESEARCH_TABLE_BIRCH = 1;
	public static final int RESEARCH_TABLE_DARKOAK = 2;
	public static final int RESEARCH_TABLE_JUNGLE = 3;
	public static final int RESEARCH_TABLE_OAK = 4;
	public static final int RESEARCH_TABLE_SPRUCE = 5;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case RESEARCH_TABLE_ACACIA:
			case RESEARCH_TABLE_BIRCH:
			case RESEARCH_TABLE_DARKOAK:
			case RESEARCH_TABLE_JUNGLE:
			case RESEARCH_TABLE_OAK:
			case RESEARCH_TABLE_SPRUCE:
				return new ResearchTableContainer(player.inventory, (TileEntityResearchTableLeft) world.getTileEntity(new BlockPos(x,y,z)));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (ID)
		{
			case RESEARCH_TABLE_ACACIA:
				return new ResearchTableGUI("acacia",player.inventory, (TileEntityResearchTableLeft) world.getTileEntity(new BlockPos(x,y,z)));
			case RESEARCH_TABLE_BIRCH:
				return new ResearchTableGUI("birch",player.inventory, (TileEntityResearchTableLeft) world.getTileEntity(new BlockPos(x,y,z)));
			case RESEARCH_TABLE_DARKOAK:
				return new ResearchTableGUI("big_oak",player.inventory, (TileEntityResearchTableLeft) world.getTileEntity(new BlockPos(x,y,z)));
			case RESEARCH_TABLE_JUNGLE:
				return new ResearchTableGUI("jungle",player.inventory, (TileEntityResearchTableLeft) world.getTileEntity(new BlockPos(x,y,z)));
			case RESEARCH_TABLE_OAK:
				return new ResearchTableGUI("oak",player.inventory, (TileEntityResearchTableLeft) world.getTileEntity(new BlockPos(x,y,z)));
			case RESEARCH_TABLE_SPRUCE:
				return new ResearchTableGUI("spruce",player.inventory, (TileEntityResearchTableLeft) world.getTileEntity(new BlockPos(x,y,z)));
		}
		return null;
	}

}

package com.charzard.arcania.proxy;

import com.charzard.arcania.blocks.ModBlocks;
import com.charzard.arcania.blocks.blocks.pedestal.TileEntityPedestal;
import com.charzard.arcania.blocks.blocks.pedestal.TileEntitySpecialRendererPedestal;
import com.charzard.arcania.util.Reference;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	public void registerItemRenderer(Item item, int meta, String id)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}

	public void registerItemRenderer(Item item, int meta, String id, String filename)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, filename), id));
	}

	@Override
	public void preInit()
	{
		super.preInit();

		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.PEDESTAL), 0, new ModelResourceLocation("arcania:pedestal", "inventory"));
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ModBlocks.RESEARCHTABLE_LEFT), 0, new ModelResourceLocation("arcania:researchtable_left", "inventory"));
	}

	@Override
	public void init()
	{
		super.init();

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPedestal.class, new TileEntitySpecialRendererPedestal());
	}

	@Override
	public void postInit()
	{
		super.postInit();
	}

}

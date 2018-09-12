package com.charzard.arcania.items.items;

import com.charzard.arcania.client.gui.arcaniumbook.ArcaniumBookGUI;
import com.charzard.arcania.items.ItemBase;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ArcaniumBook extends ItemBase {

	public ArcaniumBook()
	{
		super("arcanium_book");
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand hand)
	{
		if (worldIn.isRemote)
		{
			Minecraft.getMinecraft().displayGuiScreen(new ArcaniumBookGUI());
		}
		return super.onItemRightClick(worldIn, player, hand);
	}

}

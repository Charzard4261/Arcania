package com.charzard.arcania.util;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class RestrictedSlot extends SlotItemHandler {

	Item it;

	public RestrictedSlot(IItemHandler itemHandler, Item it, int index, int xPosition, int yPosition)
	{
		super(itemHandler, index, xPosition, yPosition);

		this.it = it;
	}

	@Override
	public boolean isItemValid(ItemStack stack)
	{
		if (!super.isItemValid(stack))
			return false;

		return stack.getItem() == it;
	}

}

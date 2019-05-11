package com.charzard.arcania.blocks.blocks.researchtable;

import com.charzard.arcania.util.RestrictedSlot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ResearchTableContainer extends Container {

	private TileEntityResearchTableLeft	te;
	private IItemHandler				inv;

	int tableWidth = 512, tableHeight = 256;

	public ResearchTableContainer(IInventory playerInv, TileEntityResearchTableLeft te)
	{
		this.te = te;
		inv = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		int slot = 0, xpos = 240, ypos = 8;
		// this.addSlotToContainer(new SlotItemHandler(inv, slot, xpos, ypos));

		for (int y = 0; y < 3; ++y)
		{
			if (y == 0)
				this.addSlotToContainer(new RestrictedSlot(inv, Items.PAPER, y, xpos, ypos + y * 18));
			 else
			this.addSlotToContainer(new SlotItemHandler(inv, y, xpos, ypos + y * 18));
		}

		int xPos = 8;
		int yPos = 84;

		for (int y = 0; y < 3; ++y)
		{
			for (int x = 0; x < 9; ++x)
			{
				this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, xPos + x * 18, yPos + y * 18));
			}
		}

		for (int x = 0; x < 9; ++x)
		{
			this.addSlotToContainer(new Slot(playerInv, x, xPos + x * 18, yPos + 58));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot)
	{
		ItemStack previous = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(fromSlot);

		if (slot != null && slot.getHasStack())
		{
			ItemStack current = slot.getStack();
			previous = current.copy();

			if (fromSlot < this.inv.getSlots())
			{
				// From the container's inventory to player's inventory
				if (!this.mergeItemStack(current, inv.getSlots(), inv.getSlots() + 34, true))
					return ItemStack.EMPTY;
			}
			else
			{
				// From the player's inventory to container's inventory
				if (!this.mergeItemStack(current, 0, inv.getSlots(), false))
					return ItemStack.EMPTY;
			}

			if (current.getCount() == 0) // Use func_190916_E() instead of stackSize 1.11 only 1.11.2 use getCount()
				slot.putStack(ItemStack.EMPTY); // Use ItemStack.field_190927_a instead of (ItemStack)null for a blank item stack. In 1.11.2 use ItemStack.EMPTY
			else
				slot.onSlotChanged();

			if (current.getCount() == previous.getCount())
				return null;
			slot.onTake(playerIn, current);
		}
		return previous;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return !playerIn.isSpectator();
	}

}

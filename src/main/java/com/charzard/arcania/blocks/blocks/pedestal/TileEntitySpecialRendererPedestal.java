package com.charzard.arcania.blocks.blocks.pedestal;

import org.lwjgl.opengl.GL11;

import com.charzard.arcania.blocks.ModBlocks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntitySpecialRendererPedestal extends TileEntitySpecialRenderer<TileEntityPedestal> {

	@Override
	public void render(TileEntityPedestal te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
		super.render(te, x, y, z, partialTicks, destroyStage, alpha);

		if (te.inventory.getStackInSlot(0).getItem() == Items.AIR)
			return;

		EntityItem entityitem = new EntityItem(te.getWorld(), te.getPos().getX(), te.getPos().getY(), te.getPos().getZ(), te.inventory.getStackInSlot(0));

		GlStateManager.pushMatrix();
		{
			long j = te.getWorld().getTotalWorldTime();
			entityitem.hoverStart = 0;
			GlStateManager.translate((float) x + 0.5F, (float) y + 0.1 + (0.03 * Math.sin(0.1 * j)), (float) z + 0.5F);
			float speed = 2;
			GlStateManager.rotate(j * speed, 0, 1, 0);
			GlStateManager.translate(0, 1, 0);
			Minecraft.getMinecraft().getRenderManager().renderEntity(entityitem, 0.0D, -0.2D, 0.0D, 0.0F, 0.0F, true);
		}
		GlStateManager.popMatrix();
	}
}

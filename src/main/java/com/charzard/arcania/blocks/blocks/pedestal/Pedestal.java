package com.charzard.arcania.blocks.blocks.pedestal;

import com.charzard.arcania.Main;
import com.charzard.arcania.blocks.BlockWithVariantsBase;
import com.charzard.arcania.util.IMetaName;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;

public class Pedestal extends BlockWithVariantsBase implements IMetaName, ITileEntityProvider {

	public static final PropertyEnum<Pedestal.EnumType> VARIANT = PropertyEnum.<Pedestal.EnumType>create("variant", Pedestal.EnumType.class);

	public Pedestal()
	{
		super("pedestal", Material.WOOD, false);
		setHardness(2);
		setResistance(10);
		setHarvestLevel("axe", 0);
		setSoundType(SoundType.WOOD);

		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, Pedestal.EnumType.OAK));
	}

	@Override
	public void registerModels()
	{
		for (EnumType et : Pedestal.EnumType.values())
			Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), et.meta, "inventory", "pedestal_" + et.getName());
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return ((Pedestal.EnumType) state.getValue(VARIANT)).getMeta();
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	{
		for (Pedestal.EnumType pedestal$enumtype : Pedestal.EnumType.values())
		{
			items.add(new ItemStack(this, 1, pedestal$enumtype.getMeta()));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, Pedestal.EnumType.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((Pedestal.EnumType) state.getValue(VARIANT)).getMeta();
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, (int) (getMetaFromState(world.getBlockState(pos))));
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[]
		{ VARIANT });
	}

	@Override
	public String getSpecialName(ItemStack stack)
	{
		return Pedestal.EnumType.values()[stack.getItemDamage()].getName();
	}

	public static enum EnumType implements IStringSerializable {
		OAK(0, "oak"), SPRUCE(1, "spruce"), BIRCH(2, "birch"), JUNGLE(3, "jungle"), ACACIA(4, "acacia"), DARKOAK(5, "darkoak");

		private static final Pedestal.EnumType[] META_LOOKUP = new Pedestal.EnumType[values().length];
		private final int meta;
		private final String name, unlocalizedName;

		private EnumType(int meta, String name)
		{
			this(meta, name, name);
		}

		private EnumType(int meta, String name, String unlocalizedName)
		{
			this.meta = meta;
			this.name = name;
			this.unlocalizedName = unlocalizedName;
		}

		@Override
		public String getName()
		{
			return this.name;
		}

		public int getMeta()
		{
			return meta;
		}

		public String getUnlocalizedName()
		{
			return unlocalizedName;
		}

		@Override
		public String toString()
		{
			return this.name;
		}

		public static Pedestal.EnumType byMetadata(int meta)
		{
			return META_LOOKUP[meta];
		}

		static
		{
			for (Pedestal.EnumType pedestal$enumtype : values())
			{
				META_LOOKUP[pedestal$enumtype.getMeta()] = pedestal$enumtype;
			}
		}

	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityPedestal();
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side)
	{
		return side == EnumFacing.UP;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	{
		return face == EnumFacing.UP ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, net.minecraft.entity.EntityLiving.SpawnPlacementType type)
	{
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX,
			float hitY, float hitZ)
	{
		TileEntityPedestal te = (TileEntityPedestal) world.getTileEntity(pos);

		if (te != null && te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, facing))
		{
			if (te.inventory.getStackInSlot(0).getItem() == Items.AIR)
			{
				ItemStack stack = player.getHeldItemMainhand().copy();
				stack.setCount(1);
				te.inventory.setStackInSlot(0, stack);
				player.getHeldItemMainhand().shrink(1);
			} else
			{
				player.addItemStackToInventory(te.inventory.getStackInSlot(0));
				te.inventory.setStackInSlot(0, new ItemStack(Items.AIR));
			}

			return true;
		}

		world.destroyBlock(pos, true);
		return false;

	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		if (!world.isRemote)
		{
			TileEntityPedestal te = (TileEntityPedestal) world.getTileEntity(pos);

			if (te != null && te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null))
			{
				if (te.inventory.getStackInSlot(0).getItem() != Items.AIR)
					world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), te.inventory.getStackInSlot(0)));
			}
		}

		super.breakBlock(world, pos, state);
	}

}

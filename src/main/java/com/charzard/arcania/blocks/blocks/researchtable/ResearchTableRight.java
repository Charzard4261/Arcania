package com.charzard.arcania.blocks.blocks.researchtable;

import java.util.Random;

import com.charzard.arcania.Main;
import com.charzard.arcania.blocks.BlockWithVariantsBase;
import com.charzard.arcania.blocks.ModBlocks;
import com.charzard.arcania.blocks.blocks.pedestal.Pedestal;
import com.charzard.arcania.util.IMetaName;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ResearchTableRight extends BlockWithVariantsBase implements IMetaName, ITileEntityProvider {

	public static final PropertyEnum<ResearchTableRight.EnumType> VARIANT = PropertyEnum.<ResearchTableRight.EnumType>create("variant",
			ResearchTableRight.EnumType.class);
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public EnumFacing facing = EnumFacing.NORTH;

	public ResearchTableRight()
	{
		super("researchtable_right", Material.WOOD, true);
		setHardness(2);
		setResistance(10);
		setHarvestLevel("axe", 0);
		setSoundType(SoundType.WOOD);

		this.setDefaultState(
				this.blockState.getBaseState().withProperty(VARIANT, ResearchTableRight.EnumType.OAK).withProperty(FACING, EnumFacing.NORTH));
	}

	@Override
	public void registerModels()
	{
		for (EnumType et : ResearchTableRight.EnumType.values())
			Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), et.meta, "inventory", "researchtable_right_" + et.getName());
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return ((ResearchTableRight.EnumType) state.getValue(VARIANT)).getMeta();
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, ResearchTableRight.EnumType.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((ResearchTableRight.EnumType) state.getValue(VARIANT)).getMeta();
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		TileEntityResearchTableRight te = (TileEntityResearchTableRight) worldIn.getTileEntity(pos);

		if (te == null || te.facing == null)
			return state.withProperty(FACING, EnumFacing.NORTH);

		if (facing != te.facing)
			facing = te.facing;

		return state.withProperty(FACING, te.facing);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(ModBlocks.RESEARCHTABLE_LEFT), 1, (int) (getMetaFromState(world.getBlockState(pos))));
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[]
		{ VARIANT, FACING });
	}

	@Override
	public String getSpecialName(ItemStack stack)
	{
		return ResearchTableRight.EnumType.values()[stack.getItemDamage()].getName();
	}

	public static enum EnumType implements IStringSerializable {
		OAK(0, "oak"), SPRUCE(1, "spruce"), BIRCH(2, "birch"), JUNGLE(3, "jungle"), ACACIA(4, "acacia"), DARKOAK(5, "darkoak");

		private static final ResearchTableRight.EnumType[] META_LOOKUP = new ResearchTableRight.EnumType[values().length];
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

		public static ResearchTableRight.EnumType byMetadata(int meta)
		{
			return META_LOOKUP[meta];
		}

		static
		{
			for (ResearchTableRight.EnumType ResearchTableRight$enumtype : values())
			{
				META_LOOKUP[ResearchTableRight$enumtype.getMeta()] = ResearchTableRight$enumtype;
			}
		}

	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityResearchTableRight();
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
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
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, net.minecraft.entity.EntityLiving.SpawnPlacementType type)
	{
		return false;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		((TileEntityResearchTableRight) worldIn.getTileEntity(pos)).setFacing(facing);
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player)
	{
		TileEntityResearchTableRight te = (TileEntityResearchTableRight) world.getTileEntity(pos);

		if (player.isCreative())
			te.drop = false;

		super.onBlockHarvested(world, pos, state, player);
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		TileEntityResearchTableRight te = (TileEntityResearchTableRight) world.getTileEntity(pos);

		BlockPos leftpos = pos.add(facing.getDirectionVec());

		if (world.getBlockState(leftpos).getBlock() instanceof ResearchTableLeft)
			world.destroyBlock(leftpos, te.drop);

		super.breakBlock(world, pos, state);
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Items.AIR;
	}

}

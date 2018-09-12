package com.charzard.arcania.blocks.blocks.researchtable;

import com.charzard.arcania.Main;
import com.charzard.arcania.blocks.BlockWithVariantsBase;
import com.charzard.arcania.blocks.blocks.pedestal.Pedestal;
import com.charzard.arcania.util.IMetaName;

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

public class ResearchTableLeft extends BlockWithVariantsBase implements IMetaName, ITileEntityProvider {

	public static final PropertyEnum<ResearchTableLeft.EnumType> VARIANT = PropertyEnum.<ResearchTableLeft.EnumType>create("variant",
			ResearchTableLeft.EnumType.class);
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public EnumFacing facing = EnumFacing.NORTH;

	public ResearchTableLeft()
	{
		super("researchtable_left", Material.WOOD);
		setHardness(2);
		setResistance(10);
		setHarvestLevel("axe", 0);
		setSoundType(SoundType.WOOD);

		this.setDefaultState(
				this.blockState.getBaseState().withProperty(VARIANT, ResearchTableLeft.EnumType.OAK).withProperty(FACING, EnumFacing.NORTH));
	}

	@Override
	public void registerModels()
	{
		for (EnumType et : ResearchTableLeft.EnumType.values())
			Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), et.meta, "inventory", "researchtable_left_" + et.getName());
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		return ((ResearchTableLeft.EnumType) state.getValue(VARIANT)).getMeta();
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	{
		for (ResearchTableLeft.EnumType ResearchTableLeft$enumtype : ResearchTableLeft.EnumType.values())
		{
			items.add(new ItemStack(this, 1, ResearchTableLeft$enumtype.getMeta()));
		}
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(VARIANT, ResearchTableLeft.EnumType.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((ResearchTableLeft.EnumType) state.getValue(VARIANT)).getMeta();
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		TileEntityResearchTableLeft te = (TileEntityResearchTableLeft) worldIn.getTileEntity(pos);
		
		if (te.facing != facing)

//		 System.out.println("ACTUAL STATE FACING IS " + te.facing + " - " + facing);

		if (te.facing == null)
			return this.getDefaultState().withProperty(FACING, EnumFacing.NORTH);

		return this.getDefaultState().withProperty(FACING, te.facing);
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
		{ VARIANT, FACING });
	}

	@Override
	public String getSpecialName(ItemStack stack)
	{
		return ResearchTableLeft.EnumType.values()[stack.getItemDamage()].getName();
	}

	public static enum EnumType implements IStringSerializable {
		OAK(0, "oak");

		private static final ResearchTableLeft.EnumType[] META_LOOKUP = new ResearchTableLeft.EnumType[values().length];
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

		public static ResearchTableLeft.EnumType byMetadata(int meta)
		{
			return META_LOOKUP[meta];
		}

		static
		{
			for (ResearchTableLeft.EnumType ResearchTableLeft$enumtype : values())
			{
				META_LOOKUP[ResearchTableLeft$enumtype.getMeta()] = ResearchTableLeft$enumtype;
			}
		}

	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityResearchTableLeft();
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
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing blockFace, float hitX, float hitY, float hitZ, int meta,
			EntityLivingBase placer)
	{
		IBlockState state = getStateFromMeta(meta);

		switch (placer.getHorizontalFacing())
		{
		case UP:
		case DOWN:
			break;
		case NORTH:
			facing = EnumFacing.EAST;
			state.withProperty(FACING, EnumFacing.EAST);
			break;
		case SOUTH:
			facing = EnumFacing.WEST;
			state.withProperty(FACING, EnumFacing.WEST);
			break;
		case EAST:
			facing = EnumFacing.SOUTH;
			state.withProperty(FACING, EnumFacing.SOUTH);
			break;
		case WEST:
			facing = EnumFacing.NORTH;
			state.withProperty(FACING, EnumFacing.NORTH);
			break;
		default:
			break;
		}

		return state;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		((TileEntityResearchTableLeft) worldIn.getTileEntity(pos)).setFacing(facing);
	}

}

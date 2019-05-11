package com.charzard.arcania.blocks.blocks.researchtable;

import java.util.List;

import javax.annotation.Nullable;

import com.charzard.arcania.Arcania;
import com.charzard.arcania.blocks.BlockWithVariantsBase;
import com.charzard.arcania.blocks.ModBlocks;
import com.charzard.arcania.blocks.blocks.pedestal.TileEntityPedestal;
import com.charzard.arcania.client.gui.GUIHandler;
import com.charzard.arcania.client.gui.researchtable.ResearchTableGUI;
import com.charzard.arcania.util.IMetaName;
import com.google.common.collect.Lists;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;

public class ResearchTableLeft extends BlockWithVariantsBase implements IMetaName, ITileEntityProvider {

	public static final PropertyEnum<ResearchTableLeft.EnumType>	VARIANT	= PropertyEnum.<ResearchTableLeft.EnumType>create("variant", ResearchTableLeft.EnumType.class);
	public static final PropertyDirection							FACING	= PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	/**
	 * NORTH, EAST, SOUTH, WEST;
	 */
	// public static final AxisAlignedBB[][] RTBB = new AxisAlignedBB[][]
	// { new AxisAlignedBB[]
	// { new AxisAlignedBB(0.5, 0.0, 0.0, 0.1, 1, 1) } };
	public ResearchTableLeft()
	{
		super("researchtable_left", Material.WOOD, false);
		setHardness(2);
		setResistance(10);
		setHarvestLevel("axe", 0);
		setSoundType(SoundType.WOOD);

		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, ResearchTableLeft.EnumType.OAK).withProperty(FACING, EnumFacing.NORTH));
	}

	@Override
	public void registerModels()
	{
		for (EnumType et : ResearchTableLeft.EnumType.values())
			Arcania.proxy.registerItemRenderer(Item.getItemFromBlock(this), et.meta, "inventory", "researchtable_left_" + et.getName());
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
			items.add(new ItemStack(this, 1, ResearchTableLeft$enumtype.getMeta()));
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
		if (te == null)
			return state.withProperty(FACING, EnumFacing.NORTH);

		return state.withProperty(FACING, te.facing);
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
		OAK(0, "oak"), SPRUCE(1, "spruce"), BIRCH(2, "birch"), JUNGLE(3, "jungle"), ACACIA(4, "acacia"), DARKOAK(5, "big_oak");

		private static final ResearchTableLeft.EnumType[]	META_LOOKUP	= new ResearchTableLeft.EnumType[values().length];
		private final int									meta;
		private final String								name, unlocalizedName;

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
	public boolean hasTileEntity()
	{
		return true;
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		switch (state.getActualState(source, pos).getValue(FACING))
		{
			case EAST:
				return new AxisAlignedBB(0.0625, 0.5625, 0.0, 1, 1, 1);
			case NORTH:
				return new AxisAlignedBB(0.0, 0.5625, 0.0, 1, 1, 0.9375);
			case SOUTH:
				return new AxisAlignedBB(0.0, 0.5625, 0.0625, 1, 1, 1);
			case WEST:
				return new AxisAlignedBB(0.0, 0.5625, 0.0, 0.9375, 1, 1);
			default:
				break;
		}

		return new AxisAlignedBB(0.0, 0.0, 0.0, 1, 1, 1);
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean isActualState)
	{
		// AABB = xyz 1 pixel = 0.0625
		switch (getActualState(state, worldIn, pos).getValue(FACING))
		{
			case NORTH:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0, 0.75, 0.0, 1, 1, 0.9375)); // Top
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.125, 0.6875, 0.0, 0.875, 0.75, 0.75)); // T
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.1875, 0.625, 0.0, 0.8125, 0.6875, 0.75)); // M
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.25, 0.5625, 0.0, 0.75, 0.625, 0.6875)); // B
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.25, 0, 0.6875, 0.75, 0.75, 0.9375)); // Leg
				break;
			case EAST:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0625, 0.75, 0.0, 1, 1, 1));
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.25, 0.6875, 0.125, 1, 0.75, 0.875));
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.25, 0.625, 0.1875, 1, 0.6875, 0.8125));
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.3125, 0.5625, 0.25, 1, 0.625, 0.75));
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0625, 0, 0.25, 0.3125, 0.75, 0.75));
				break;
			case SOUTH:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0, 0.75, 0.0625, 1, 1, 1));
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.125, 0.6875, 0.25, 0.875, 0.75, 1));
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.1875, 0.625, 0.25, 0.8125, 0.6875, 1));
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.25, 0.5625, 0.3125, 0.75, 0.625, 1));
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.25, 0.0, 0.0625, 0.75, 1, 0.3125));
				break;
			case WEST:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0, 0.75, 0.0, 0.9375, 1, 1));
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0, 0.6875, 0.125, 0.75, 0.75, 0.875));
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0, 0.625, 0.1875, 0.75, 0.6875, 0.8125));
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0, 0.5625, 0.25, 0.6875, 0.625, 0.75));
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.6875, 0, 0.25, 0.9375, 0.75, 0.75));
				break;
			default:
				break;
		}
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
	{
		if (face == EnumFacing.UP)
			return BlockFaceShape.SOLID;

		return BlockFaceShape.UNDEFINED;
	}

	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, net.minecraft.entity.EntityLiving.SpawnPlacementType type)
	{
		return false;
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing blockFace, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		IBlockState state = getStateFromMeta(meta);
		EnumFacing facing = EnumFacing.NORTH;

		switch (placer.getHorizontalFacing())
		{
			case NORTH:
				facing = EnumFacing.EAST;
				break;
			case SOUTH:
				facing = EnumFacing.WEST;
				break;
			case EAST:
				facing = EnumFacing.SOUTH;
				break;
			case WEST:
				facing = EnumFacing.NORTH;
				break;
			default:
				break;
		}
		state = state.withProperty(FACING, facing);

		return state;
	}

	// @Override
	// public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
	// {
	// return false;
	// }

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		EnumFacing facing = state.getValue(FACING);
		((TileEntityResearchTableLeft) worldIn.getTileEntity(pos)).setFacing(facing);

		BlockPos rightpos = pos.add(facing.getDirectionVec());

		if (canPlaceBlockAt(worldIn, rightpos))
		{
			worldIn.destroyBlock(rightpos, true);
			worldIn.setBlockState(rightpos, ModBlocks.RESEARCHTABLE_RIGHT.getStateFromMeta(((ResearchTableLeft.EnumType) state.getValue(VARIANT)).getMeta()));
			((TileEntityResearchTableRight) worldIn.getTileEntity(rightpos)).setFacing(facing.getOpposite());
		}
		else if (placer instanceof EntityPlayer)
			worldIn.destroyBlock(pos, !((EntityPlayer)placer).isCreative());
		else
			worldIn.destroyBlock(pos, true);

	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		TileEntityResearchTableLeft te = (TileEntityResearchTableLeft) worldIn.getTileEntity(pos);

		if (!worldIn.isRemote)
		{

			if (te != null && te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null))
			{
				for (int slot = 0; slot < te.inventory.getSlots(); slot++)
					if (te.inventory.getStackInSlot(slot).getItem() != Items.AIR)
						worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), te.inventory.getStackInSlot(slot)));
			}
		}

		EnumFacing facing = te.facing;
		BlockPos rightpos = pos.add(facing.getDirectionVec());

		if (worldIn.getBlockState(rightpos).getBlock() instanceof ResearchTableRight)
			if (((TileEntityResearchTableRight) worldIn.getTileEntity(rightpos)).facing == facing.getOpposite())
				worldIn.destroyBlock(rightpos, false);

		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		TileEntityResearchTableLeft te = (TileEntityResearchTableLeft) worldIn.getTileEntity(pos);

		if (!worldIn.isRemote)
		{
			if (te != null)
			{
				// Minecraft.getMinecraft().displayGuiScreen(new ResearchTableGUI(state.getValue(VARIANT).name));
				switch (state.getValue(VARIANT))
				{
					case ACACIA:
						player.openGui(Arcania.instance, GUIHandler.RESEARCH_TABLE_ACACIA, worldIn, pos.getX(), pos.getY(), pos.getZ());
						break;
					case BIRCH:
						player.openGui(Arcania.instance, GUIHandler.RESEARCH_TABLE_BIRCH, worldIn, pos.getX(), pos.getY(), pos.getZ());
						break;
					case DARKOAK:
						player.openGui(Arcania.instance, GUIHandler.RESEARCH_TABLE_DARKOAK, worldIn, pos.getX(), pos.getY(), pos.getZ());
						break;
					case JUNGLE:
						player.openGui(Arcania.instance, GUIHandler.RESEARCH_TABLE_JUNGLE, worldIn, pos.getX(), pos.getY(), pos.getZ());
						break;
					case OAK:
						player.openGui(Arcania.instance, GUIHandler.RESEARCH_TABLE_OAK, worldIn, pos.getX(), pos.getY(), pos.getZ());
						break;
					case SPRUCE:
						player.openGui(Arcania.instance, GUIHandler.RESEARCH_TABLE_SPRUCE, worldIn, pos.getX(), pos.getY(), pos.getZ());
						break;
					default:
						break;

				}
				return true;
			}
		}
		else
			return true;

		return false;
	}

	
	
}

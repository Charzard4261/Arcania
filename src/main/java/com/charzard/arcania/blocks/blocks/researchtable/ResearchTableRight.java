package com.charzard.arcania.blocks.blocks.researchtable;

import java.util.List;
import java.util.Random;

import com.charzard.arcania.Main;
import com.charzard.arcania.blocks.BlockWithVariantsBase;
import com.charzard.arcania.blocks.ModBlocks;
import com.charzard.arcania.client.gui.researchtable.ResearchTableGUI;
import com.charzard.arcania.util.IMetaName;

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
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ResearchTableRight extends BlockWithVariantsBase implements IMetaName, ITileEntityProvider {

	public static final PropertyEnum<ResearchTableRight.EnumType>	VARIANT	= PropertyEnum.<ResearchTableRight.EnumType>create("variant", ResearchTableRight.EnumType.class);
	public static final PropertyDirection							FACING	= PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public ResearchTableRight()
	{
		super("researchtable_right", Material.WOOD, true);
		setHardness(2);
		setResistance(10);
		setHarvestLevel("axe", 0);
		setSoundType(SoundType.WOOD);

		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, ResearchTableRight.EnumType.OAK).withProperty(FACING, EnumFacing.NORTH));
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

		if (state.getValue(FACING) != te.facing)
			state = state.withProperty(FACING, te.facing);

		return state;
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
		OAK(0, "oak"), SPRUCE(1, "spruce"), BIRCH(2, "birch"), JUNGLE(3, "jungle"), ACACIA(4, "acacia"), DARKOAK(5, "big_oak");

		private static final ResearchTableRight.EnumType[]	META_LOOKUP	= new ResearchTableRight.EnumType[values().length];
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

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return new AxisAlignedBB(0.0, 0.5625, 0.0, 1, 1, 0.9375);
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean isActualState)
	{
		switch (state.getValue(FACING)) // FACING is actually the opposite
		{
			case NORTH:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0, 0.75, 0.0625, 1, 1, 1));
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.125, 0.6875, 0.25, 0.875, 0.75, 1));
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.1875, 0.625, 0.25, 0.8125, 0.6875, 1));
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.25, 0.5625, 0.3125, 0.75, 0.625, 1));
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.25, 0.0, 0.0625, 0.75, 1, 0.3125));
				break;
			case EAST:
				break;
			case SOUTH:
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.0, 0.75, 0.0, 1, 1, 0.9375)); // Top
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.125, 0.6875, 0.0, 0.875, 0.75, 0.75)); // T
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.1875, 0.625, 0.0, 0.8125, 0.6875, 0.75)); // M
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.25, 0.5625, 0.0, 0.75, 0.625, 0.6875)); // B
				addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(0.25, 0, 0.6875, 0.75, 0.75, 0.9375)); // Leg
				break;
			case WEST:
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
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		((TileEntityResearchTableRight) worldIn.getTileEntity(pos)).setFacing(state.getValue(FACING));
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		TileEntityResearchTableRight te = (TileEntityResearchTableRight) world.getTileEntity(pos);
		BlockPos leftpos = pos.add(te.facing.getDirectionVec());

		if (world.getBlockState(leftpos).getBlock() instanceof ResearchTableLeft)
			world.destroyBlock(leftpos, te.drop);

		super.breakBlock(world, pos, state);
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
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		// TileEntityResearchTableRight te = (TileEntityResearchTableRight)
		// world.getTileEntity(pos);
		//
		// if (te != null)
		// {
		// Minecraft.getMinecraft().displayGuiScreen(new
		// ResearchTableGUI(state.getValue(VARIANT).name));
		// return true;
		// }
		return false;
	}

	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Items.AIR;
	}

}

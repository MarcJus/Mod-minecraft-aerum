package fr.marcjus.mod.blocks;

import java.util.Random;

import akka.dispatch.sysmsg.Create;
import fr.marcjus.mod.MainMod;
import fr.marcjus.mod.blocks.JumpPad.EnumType;
import fr.marcjus.mod.init.BlocksMod;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlab.EnumBlockHalf;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class LandingPad extends BlockSlab{
	
	public static PropertyEnum<EnumType> VARIANT = PropertyEnum.<LandingPad.EnumType>create("variant", LandingPad.EnumType.class);

	public LandingPad(Material materialIn, String name) {
		super(materialIn);
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(MainMod.aerumTab);
		
		IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, EnumType.DEFAULT);
		
		if(!this.isDouble()){
			state.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
		}
		
		this.setDefaultState(state);
		this.useNeighborBrightness = !this.isDouble();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		if(!this.isDouble()){
			return new BlockStateContainer(this, new IProperty[]{VARIANT, HALF});
		}
		return new BlockStateContainer(this, new IProperty[]{VARIANT});
	}
	
	@Override
	public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance) {
		entity.fallDistance = 0f;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState blockstate = this.blockState.getBaseState().withProperty(VARIANT, EnumType.DEFAULT);
		
		if(!this.isDouble()) {
			blockstate = blockstate.withProperty(HALF, ((meta & 8) != 0)?EnumBlockHalf.TOP : EnumBlockHalf.BOTTOM);
		}
		
		return blockstate;
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		
		int meta = 0;
		
		if(!this.isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP) {
			meta |= 8;
		}
		
		return meta;
	}

	@Override
	public String getUnlocalizedName(int meta) {
		return super.getUnlocalizedName();
	}

	@Override
	public IProperty<?> getVariantProperty() {
		return VARIANT;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return EnumType.DEFAULT;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(BlocksMod.landing_slab);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(BlocksMod.landing_slab);
	}
	
	public static class Half extends LandingPad{

		public Half(Material materialIn, String name) {
			super(materialIn, name);
		}

		@Override
		public boolean isDouble() {
			return false;
		}
		
	}
	
	public static class Double extends LandingPad{

		public Double(Material materialIn, String name) {
			super(materialIn, name);
		}

		@Override
		public boolean isDouble() {
			return true;
		}
		
	}
	
	public static enum EnumType implements IStringSerializable{
		DEFAULT
		;
		
		@Override
		public String toString() {
			return "default";
		}

		@Override
		public String getName() {
			return "default";
		}
		
	}

}

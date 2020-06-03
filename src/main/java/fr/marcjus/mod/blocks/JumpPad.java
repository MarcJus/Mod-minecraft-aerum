package fr.marcjus.mod.blocks;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.google.common.primitives.UnsignedInteger;

import fr.marcjus.mod.MainMod;
import fr.marcjus.mod.init.BlocksMod;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSlime;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.internal.FMLNetworkHandler;

public abstract class JumpPad extends BlockSlab{
	
	public static PropertyEnum<EnumType> VARIANT = PropertyEnum.<JumpPad.EnumType>create("variant", JumpPad.EnumType.class);

	public JumpPad(String name, Material materialIn) {
		super(materialIn);
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(MainMod.aerumTab);
		setHardness(4f);
		setResistance(100f);
		
		IBlockState state = this.blockState.getBaseState().withProperty(VARIANT, EnumType.DEFAULT);
		
		if(!this.isDouble()){
			state.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
		}
		
		this.setDefaultState(state);
		this.useNeighborBrightness = !this.isDouble();
	}
	
	@Override
	public void onLanded(World worldIn, Entity entityIn) {
		super.onLanded(worldIn, entityIn);
		entityIn.fallDistance = 0f;
	}
	
	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity) {
		entity.fallDistance = 0;
		if(this instanceof JumpPad.Half){
			entity.motionX *= 2.5f;
			entity.motionY += 1.5;
			entity.motionZ *= 2.5;
		} else if (this instanceof JumpPad.Double){
			entity.motionY += 3;
		}
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
		return Item.getItemFromBlock(BlocksMod.jump_slab);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(BlocksMod.jump_slab);
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for(EnumType type : EnumType.values()){
			items.add(new ItemStack(this, 1, type.metadata));
		}
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(VARIANT).metadata;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		IBlockState blockstate = this.blockState.getBaseState().withProperty(VARIANT, EnumType.byMetadata(meta));
		
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
	protected BlockStateContainer createBlockState() {
		if(!this.isDouble()){
			return new BlockStateContainer(this, new IProperty[]{VARIANT, HALF});
		}
		return new BlockStateContainer(this, new IProperty[]{VARIANT});
	}
	
	public static class Double extends JumpPad{

		public Double(String name, Material materialIn) {
			super(name, materialIn);
		}

		@Override
		public boolean isDouble() {
			return true;
		}

	}
	
	public static class Half extends JumpPad{

		public Half(String name, Material materialIn) {
			super(name, materialIn);
		}

		@Override
		public boolean isDouble() {
			return false;
		}
		
	}
	
	public static enum EnumType implements IStringSerializable{
		DEFAULT(0, "default", "jump_pad"),
		;
		
		private static JumpPad.EnumType[] METADATA = new EnumType[values().length];
		private final String name;
		private final String unlocalizedName;
		private final int metadata;
		
		private EnumType(int metadata, String name, String unlocalizedName) {
			this.metadata = metadata;
			this.name = name;
			this.unlocalizedName = unlocalizedName;
		}
		
		static{
			for(EnumType type : values()){
				METADATA[type.metadata] = type;
			}
		}
		
		public static String[] getUnlocalizedNames(){
			String[] names = new String[values().length];
			for (int i = 0; i < METADATA.length; i++){
				names[i] = METADATA[i].unlocalizedName;
			}
			return names;
		}
		
		public int getMetadata(){
			return metadata;
		}
		
		public static EnumType byMetadata(int metadata){
			if(metadata < 0 || metadata >= METADATA.length){
				metadata = 0;
			}
			return METADATA[metadata];
		}

		@Override
		public String getName() {
			return name;
		}
		
		@Override
		public String toString() {
			return name;
		}
		
	}
}

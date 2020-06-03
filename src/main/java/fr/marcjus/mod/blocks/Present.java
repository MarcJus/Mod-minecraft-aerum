package fr.marcjus.mod.blocks;

import fr.marcjus.mod.MainMod;
import fr.marcjus.mod.init.BlocksMod;
import fr.marcjus.mod.utils.StringToComponent;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Present extends Block {
	
	public static final String NAME = "present";
	public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);

	public Present(Material materialIn) {
		super(materialIn);
		
		BlocksMod.setBlockName(this, NAME);
		setResistance(5f);
		setHardness(3f);
		setCreativeTab(MainMod.aerumTab);
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumType.NORMAL));
	}
	
	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity) {
		if(world.isRemote)return;
		if(!(entity instanceof EntityPlayer))return;
		IBlockState state = world.getBlockState(pos);
		world.setBlockToAir(pos);
		EntityItem item = new EntityItem(world, pos.getX(), pos.getY() + 0.8d, pos.getZ());
		double alea = Math.random() * 100;
		if(state == this.getDefaultState().withProperty(VARIANT, EnumType.LUCKIEST)){
			if(alea < 20){
				item.setItem(new ItemStack(BlocksMod.aerum_ore, 2));
			} else if (alea < 40){
				item.setItem(new ItemStack(Blocks.DIAMOND_ORE, 4));
			} else if (alea < 75){
				item.setItem(new ItemStack(Blocks.OBSIDIAN, 5));
			} else {
				item.setItem(new ItemStack(Items.ENDER_PEARL, 3));
			}
		} else {
			if(alea < 20){
				item.setItem(new ItemStack(Blocks.OBSIDIAN, 2));
			} else if (alea < 40){
				item.setItem(new ItemStack(Blocks.GOLD_ORE, 2));
			} else if (alea < 75){
				item.setItem(new ItemStack(Blocks.IRON_BLOCK));
			} else {
				item.setItem(new ItemStack(Items.COOKED_CHICKEN, 10));
			}
		}
		world.spawnEntity(item);
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state) {
		if(world.isRemote){
			Minecraft.getMinecraft().player.sendMessage(StringToComponent.convert("§cVous devez sauter sur le cadeau pour avoir les recompenses !"));
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(VARIANT).getMetadata();
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		for(EnumType type : EnumType.values()){
			items.add(new ItemStack(this, 1, type.getMetadata()));
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return ((EnumType) state.getValue(VARIANT)).getMetadata();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{VARIANT});
	}
	
	public static enum EnumType implements IStringSerializable{
		NORMAL(0, "normal", "present"),
		LUCKIEST(1, "luckiest", "present_1")
		;
		
		private static final EnumType[] META_LOOKUP = new EnumType[values().length];
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
				META_LOOKUP[type.metadata] = type;
			}
		}
		
		public static String[] getUnlocalizedNames(){
			String[] names = new String[values().length];
			for (int i = 0; i < META_LOOKUP.length; i++){
				names[i] = META_LOOKUP[i].unlocalizedName;
			}
			return names;
		}
		
		public int getMetadata(){
			return metadata;
		}
		
		public static EnumType byMetadata(int metadata){
			if(metadata < 0 || metadata >= META_LOOKUP.length){
				metadata = 0;
			}
			return META_LOOKUP[metadata];
		}
		
		@Override
		public String toString() {
			return name;
		}

		@Override
		public String getName() {
			return name;
		}
		
	}

}

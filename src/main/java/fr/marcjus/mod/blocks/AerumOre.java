package fr.marcjus.mod.blocks;

import java.util.Random;

import fr.marcjus.mod.MainMod;
import fr.marcjus.mod.init.BlocksMod;
import fr.marcjus.mod.init.ItemsMod;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class AerumOre extends BlockOre{
	
	private static boolean multipleQuantity = false;
	private static int minDrop;
	private static int maxDrop;
	
	public AerumOre(String name, int harvestLevel) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setHarvestLevel("pickaxe", 3);
		setCreativeTab(MainMod.aerumTab);
		setHardness(5f);
		setResistance(1f);
	}
	
	public AerumOre(String name, int harvestLevel, int minDrop, int maxDrop){
		setUnlocalizedName(name);
		setRegistryName(name);
		setHarvestLevel("pickaxe", harvestLevel);
		this.multipleQuantity = true;
		this.minDrop = minDrop;
		this.maxDrop = maxDrop;
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune){
		return Item.getItemFromBlock(BlocksMod.aerum_ore);
	}
	
	@Override
	public int quantityDropped(Random random) {
		return this.multipleQuantity ? this.minDrop + random.nextInt(maxDrop - minDrop) : 1;
	}
	
	@Override
	public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
		
		return 1;
	}

}

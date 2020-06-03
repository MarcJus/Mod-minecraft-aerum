package fr.marcjus.mod.blocks;

import fr.marcjus.mod.MainMod;
import fr.marcjus.mod.utils.StringToComponent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class KillerBlock extends Block {
	
	public KillerBlock() {
		super(Material.ROCK);
		setUnlocalizedName("killer_block");
		setRegistryName("killer_block");
		setCreativeTab(MainMod.aerumTab);
		setHardness(3.5f);
		setResistance(50);
	}
	
	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity) {
		if(entity instanceof EntityCreature){
			entity.setDead();
		}
	}
	
}

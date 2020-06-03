package fr.marcjus.mod.world;

import java.util.Random;

import fr.marcjus.mod.init.BlocksMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenStructure extends WorldGenerator{

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		
		double posX = pos.getX();
		double posY = pos.getY();
		double posZ = pos.getZ();
		
		Block state = world.getBlockState(pos).getBlock();
		Block block1 = world.getBlockState(new BlockPos(posX, posY-1, posZ)).getBlock();
		Block block2 = world.getBlockState(new BlockPos(posX, posY+1, posZ)).getBlock();
		
		if(block1 instanceof BlockAir && !(block2 instanceof BlockAir))return false;
		
		world.setBlockState(new BlockPos(posX, posY, posZ), BlocksMod.green_background.getDefaultState());
		
		return true;
	}
	
}

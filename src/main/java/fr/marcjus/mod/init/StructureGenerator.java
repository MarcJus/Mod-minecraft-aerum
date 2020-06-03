package fr.marcjus.mod.init;

import java.util.Random;

import fr.marcjus.mod.world.WorldGenStructure;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class StructureGenerator implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		
		switch (world.provider.getDimension()) {
		case -1:
			break;
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 1:
			break;
		default:
			break;
		}
	}
	
	private void generateSurface(World world, Random rand, int x, int z){
		
		int x1 = x + rand.nextInt(16);
		int y1 = rand.nextInt(128);
		int z1 = z + rand.nextInt(16);
		
		for(int i = 0; i<500; i++){
			new WorldGenStructure().generate(world, rand, new BlockPos(x1, y1, z1));
		}
		
	}

}

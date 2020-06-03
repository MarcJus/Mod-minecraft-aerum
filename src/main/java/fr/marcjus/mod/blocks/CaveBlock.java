package fr.marcjus.mod.blocks;

import fr.marcjus.mod.MainMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;

public class CaveBlock extends Block {

	public CaveBlock() {
		super(Material.ROCK);
		setUnlocalizedName("cave_block");
		setRegistryName("cave_block");
		setCreativeTab(MainMod.aerumTab);
		setHardness(1.5f);
		setResistance(3f);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}

}

package fr.marcjus.mod.blocks;

import fr.marcjus.mod.MainMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AerumBlock extends Block {

	public AerumBlock(String name, Material materialIn) {
		super(materialIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(MainMod.aerumTab);
		setHardness(4);
		setResistance(1.5f);
	}

}

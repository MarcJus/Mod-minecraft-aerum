package fr.marcjus.mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class AerumFurnace extends Block {

	public AerumFurnace(Material materialIn, String name) {
		super(materialIn);
		setUnlocalizedName(name);
		setRegistryName(name);
	}

}

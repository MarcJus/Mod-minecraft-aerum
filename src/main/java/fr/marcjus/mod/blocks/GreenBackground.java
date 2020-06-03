package fr.marcjus.mod.blocks;

import fr.marcjus.mod.MainMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GreenBackground extends Block {

	public GreenBackground(String name, Material material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(MainMod.aerumTab);
		setHardness(3.5f);
		setResistance(0.5f);
	}

}

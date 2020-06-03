package fr.marcjus.mod.items.aerum;

import fr.marcjus.mod.MainMod;
import net.minecraft.item.Item;

public class AerumIngot extends Item{
	
	public AerumIngot(String name){
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(MainMod.aerumTab);
	}

}

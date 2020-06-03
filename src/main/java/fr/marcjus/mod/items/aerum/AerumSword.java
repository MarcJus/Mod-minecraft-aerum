package fr.marcjus.mod.items.aerum;

import fr.marcjus.mod.MainMod;
import fr.marcjus.mod.init.ItemsMod;
import net.minecraft.item.ItemSword;

public class AerumSword extends ItemSword{

	public AerumSword(String name) {
		super(ItemsMod.aerum_material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(MainMod.aerumTab);
	}

}

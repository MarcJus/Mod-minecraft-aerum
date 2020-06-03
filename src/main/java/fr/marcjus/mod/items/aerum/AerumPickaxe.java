package fr.marcjus.mod.items.aerum;

import fr.marcjus.mod.MainMod;
import fr.marcjus.mod.init.ItemsMod;
import net.minecraft.item.ItemPickaxe;

public class AerumPickaxe extends ItemPickaxe {

	public AerumPickaxe(String name) {
		super(ItemsMod.aerum_material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(MainMod.aerumTab);
	}

}

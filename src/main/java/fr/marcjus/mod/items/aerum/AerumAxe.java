package fr.marcjus.mod.items.aerum;

import fr.marcjus.mod.MainMod;
import fr.marcjus.mod.init.ItemsMod;
import net.minecraft.item.ItemAxe;

public class AerumAxe extends ItemAxe{

	public AerumAxe(String name) {
		super(ItemsMod.aerum_material, 9, 1f);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(MainMod.aerumTab);
	}

}

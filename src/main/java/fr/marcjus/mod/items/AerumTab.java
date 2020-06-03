package fr.marcjus.mod.items;

import fr.marcjus.mod.init.BlocksMod;
import fr.marcjus.mod.init.ItemsMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AerumTab extends CreativeTabs {

	public AerumTab(String label) {
		super(label);
	}
	
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(BlocksMod.aerum_block);
	}
	
}

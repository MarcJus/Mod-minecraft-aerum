package fr.marcjus.mod.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockPresent extends ItemBlock {
	
	public String[] unlocalizedNames;
	
	public ItemBlockPresent(Block block, String[] unlocalizedNames) {
		super(block);
		this.unlocalizedNames = unlocalizedNames;
		setHasSubtypes(true);
		setMaxDamage(0);
	}
	
	@Override
	public int getMetadata(int damage) {
		return damage;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return unlocalizedNames[stack.getMetadata()];
	}
	
}

package fr.marcjus.mod.init;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipesMod {
	
	public static void init(){
		GameRegistry.addSmelting(new ItemStack(Blocks.STONE), new ItemStack(Blocks.BEDROCK), 3.0f);
		GameRegistry.addSmelting(new ItemStack(BlocksMod.aerum_ore), new ItemStack(ItemsMod.aerum_ingot), 3.0f);
	}

}

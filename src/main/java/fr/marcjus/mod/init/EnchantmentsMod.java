package fr.marcjus.mod.init;

import java.util.ArrayList;
import java.util.List;

import fr.marcjus.mod.enchantments.EnchantmentDoubleJump;
import fr.marcjus.mod.enchantments.EnchantmentInfiniteJump;
import fr.marcjus.mod.utils.References;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = References.MODID)
public class EnchantmentsMod {
	
	public static final List<Enchantment> ENCHANTMENTS = new ArrayList<>();
	
	public static final EnchantmentDoubleJump double_jump = new EnchantmentDoubleJump();
	public static final EnchantmentInfiniteJump infinite_jump = new EnchantmentInfiniteJump();
	
	@SubscribeEvent
	public static void registerEnchanments(RegistryEvent.Register<Enchantment> e){
		for(Enchantment ench : ENCHANTMENTS){
			e.getRegistry().register(ench);
		}
	}
}

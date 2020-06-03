package fr.marcjus.mod.enchantments;

import fr.marcjus.mod.init.EnchantmentsMod;
import fr.marcjus.mod.utils.References;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class EnchantmentInfiniteJump extends Enchantment{

	public EnchantmentInfiniteJump() {
		super(Rarity.VERY_RARE, EnumEnchantmentType.ARMOR_FEET, new EntityEquipmentSlot[]{EntityEquipmentSlot.FEET});
		this.setName("infinite_jump");
		this.setRegistryName(new ResourceLocation(References.MODID, "infinite_jump"));
		
		EnchantmentsMod.ENCHANTMENTS.add(this);
	}

}

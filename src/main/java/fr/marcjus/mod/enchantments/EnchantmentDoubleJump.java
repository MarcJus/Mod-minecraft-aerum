package fr.marcjus.mod.enchantments;

import fr.marcjus.mod.init.EnchantmentsMod;
import fr.marcjus.mod.utils.References;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class EnchantmentDoubleJump extends Enchantment{

	public EnchantmentDoubleJump() {
		super(Rarity.UNCOMMON, EnumEnchantmentType.ARMOR_FEET, new EntityEquipmentSlot[]{EntityEquipmentSlot.FEET});
		this.setName("double_jump");
		this.setRegistryName(new ResourceLocation(References.MODID, "double_jump"));
		
		EnchantmentsMod.ENCHANTMENTS.add(this);
	}
	
	@Override
	public int getMaxLevel() {
		return 3;
	}

}

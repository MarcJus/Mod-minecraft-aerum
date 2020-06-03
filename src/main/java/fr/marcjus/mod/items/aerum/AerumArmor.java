package fr.marcjus.mod.items.aerum;

import java.util.List;

import com.typesafe.config.ConfigFactory;

import fr.marcjus.mod.MainMod;
import fr.marcjus.mod.init.ItemsMod;
import fr.marcjus.mod.utils.References;
import net.minecraft.block.BlockAnvil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AerumArmor extends ItemArmor {

	public static ArmorMaterial aerum = EnumHelper.addArmorMaterial("aerum", References.MODID + ":aerum", 500,
			new int[] { 5, 8, 10, 5 }, 17, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f);

	static {
		aerum.setRepairItem(new ItemStack(ItemsMod.aerum_ingot));
	}

	public AerumArmor(EntityEquipmentSlot slot) {
		super(aerum, 0, slot);
		setCreativeTab(MainMod.aerumTab);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {

		if (slot.equals(EntityEquipmentSlot.LEGS)) {
			return References.MODID + ":textures/models/armor/aerum_layer_2.png";
		}

		return References.MODID + ":textures/models/armor/aerum_layer_1.png";
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {

		if (repair.getItem() == ItemsMod.aerum_ingot) {
			return true;
		}

		return false;
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack it) {

		PlayerCapabilities capabilities = player.capabilities;
		
		PotionEffect night_vision = new PotionEffect(Potion.getPotionById(16), 200, 0, false, false);
		
		PotionEffect strenght = new PotionEffect(Potion.getPotionById(5), 200, 0, false, false);
		
		PotionEffect haste = new PotionEffect(Potion.getPotionById(3), 200, 0, false, false);
		
		PotionEffect speed = new PotionEffect(Potion.getPotionById(1), 200, 0, false, false);
		
		switch (this.armorType) {

		case HEAD:
			player.addPotionEffect(night_vision);
			break;
		case CHEST:
			player.addPotionEffect(strenght);
			break;
		case LEGS:
			player.addPotionEffect(haste);
			break;
		case FEET:
			player.addPotionEffect(speed);
			break;

		default:
			break;

		}

		player.setHealth(20f);
		player.getFoodStats().setFoodLevel(20);

	}

	@Override
	public void addInformation(ItemStack stack, World world, List<String> info, ITooltipFlag flag) {

		Item item = stack.getItem();

		if (item.equals(ItemsMod.aerumHelmet)) {
			info.add("Donne un effet de vision de nuit");
		} else if (item.equals(ItemsMod.aerumChestplate)) {
			info.add("Donne un effet de force");
		} else if (item.equals(ItemsMod.aerumLeggings)) {
			info.add("Donne un effet de minage rapide");
		} else {
			info.add("Donne un effet de vitesse");
			info.add("Annule les degats de chute");
		}

		super.addInformation(stack, world, info, flag);
	}

}

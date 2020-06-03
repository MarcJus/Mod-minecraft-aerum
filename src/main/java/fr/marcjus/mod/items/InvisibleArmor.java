package fr.marcjus.mod.items;

import java.util.List;

import fr.marcjus.mod.init.ItemsMod;
import fr.marcjus.mod.utils.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.gui.MinecraftServerGui;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

public class InvisibleArmor extends ItemArmor {

	public static ArmorMaterial invisible = EnumHelper.addArmorMaterial("invisible", References.MODID + ":invisible",
			500, new int[] { 5, 8, 10, 5 }, 17, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0f);

	public InvisibleArmor(EntityEquipmentSlot equipmentSlotIn, String name) {
		super(invisible, 0, equipmentSlotIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MISC);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack it) {
		
		PlayerCapabilities capabilities = player.capabilities;
		
		PotionEffect night_vision = new PotionEffect(Potion.getPotionById(16), 200, 0, false, false);
		
		PotionEffect strenght = new PotionEffect(Potion.getPotionById(5), 200, 0, false, false);
		
		PotionEffect haste = new PotionEffect(Potion.getPotionById(3), 200, 0, false, false);
		
		PotionEffect speed = new PotionEffect(Potion.getPotionById(1), 200, 1, false, false);

		switch (this.armorType) {
		
		case HEAD:
			player.addPotionEffect(night_vision);
			break;
			
		case CHEST:
			player.addPotionEffect(strenght);
			player.capabilities.allowFlying = true;
			break;
			
		case LEGS:
			if (it.getItem() == ItemsMod.invisibleLeggings) {
				player.setInvisible(true);
				player.addPotionEffect(new PotionEffect(Potion.getPotionById(14), 200, 1, false, false));
			}
			player.addPotionEffect(haste);
			break;
			
		case FEET:
			player.addPotionEffect(speed);
			break;

		default:
			break;
		}

		if (!(player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == ItemsMod.invisibleLeggings)){
			player.setInvisible(false);
			player.removePotionEffect(Potion.getPotionById(14));
		}
		
		player.setHealth(20f);
		player.getFoodStats().setFoodLevel(20);
		
		if(!capabilities.isCreativeMode && player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() != ItemsMod.aerumChestplate){
			capabilities.allowFlying = false;
			capabilities.isFlying = false;
		}
		
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {

		EntityPlayer player = (EntityPlayer) entity;
		if (slot == EntityEquipmentSlot.LEGS) {
			return References.MODID + ":textures/models/armor/invisible_layer_2.png";
		}
		return References.MODID + ":textures/models/armor/invisible_layer_1.png";

	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> info, ITooltipFlag flagIn) {
		
		Item item = stack.getItem();

		if (item.equals(ItemsMod.invisibleHelmet)) {
			info.add("Donne un effet de vision de nuit");
		} else if (item.equals(ItemsMod.invisibleChestplate)) {
			info.add("Donne un effet de force");
			info.add("Permet de voler");
		} else if (item.equals(ItemsMod.invisibleLeggings)) {
			info.add("Donne un effet de minage rapide");
		} else {
			info.add("Donne un effet de vitesse");
			info.add("Annule les degats de chute");
		}
		
		super.addInformation(stack, worldIn, info, flagIn);
	}

}

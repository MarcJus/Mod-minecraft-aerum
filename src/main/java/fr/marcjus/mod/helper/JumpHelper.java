package fr.marcjus.mod.helper;

import java.util.HashMap;
import java.util.Map;

import fr.marcjus.mod.MainMod;
import fr.marcjus.mod.capabilities.antigravity.AntiGravityProvider;
import fr.marcjus.mod.capabilities.antigravity.AntiGravityStorage;
import fr.marcjus.mod.init.EnchantmentsMod;
import fr.marcjus.mod.utils.StringToComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class JumpHelper {
	
	private static HashMap<EntityPlayer, Integer> jumps = new HashMap<>();//le premier jump compte 
	
	public static void jump(EntityPlayer player){
		if(EnchantmentHelper.getEnchantments(player.getItemStackFromSlot(EntityEquipmentSlot.FEET)).containsKey(EnchantmentsMod.infinite_jump)){
			player.jump();
			return;
		}
		if(canJump(player)){
			player.jump();
			addJump(player);
		}
	}
	
	private static boolean canJump(EntityPlayer player){
		if(player.isSneaking() || player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem().equals(Items.ELYTRA) || player.isRiding())return false;
		ItemStack boots = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
		if(boots == null)return false;
		Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(boots);
		if(enchantments.get(EnchantmentsMod.double_jump) == null)return false;
		int playerJump = jumps.get(player);
		int level = enchantments.get(EnchantmentsMod.double_jump);
		if(playerJump - 2 > level){
			return false;
		}
		return true;
	}
	
	public static HashMap<EntityPlayer, Integer> getJumps() {
		return jumps;
	}
	
	public static void addJump(EntityPlayer player){
		int jump = jumps.get(player);
		jumps.remove(player);
		jumps.put(player, jump+1);
	}
	
}

package fr.marcjus.mod.items;

import fr.marcjus.mod.MainMod;
import fr.marcjus.mod.init.NetworkMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class TimeModifier extends Item {
	
	public TimeModifier(String name){
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(MainMod.aerumTab);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand handIn) {
		
		if(world.getWorldTime() <= 23459 && world.getWorldTime() >= 13000){
			world.setWorldTime(1000);//jour
		} else {
			world.setWorldTime(13805);//nuit
		}
		
		return super.onItemRightClick(world, player, handIn);
	}
	
}

package fr.marcjus.mod.items.aerum;

import fr.marcjus.mod.MainMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class AerumStick extends Item {
	
	public AerumStick(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(MainMod.aerumTab);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		player.openGui(MainMod.instance, 0, world,(int) player.posX,(int) player.posY,(int) player.posZ);
		return super.onItemRightClick(world, player, hand);
	}
	
}

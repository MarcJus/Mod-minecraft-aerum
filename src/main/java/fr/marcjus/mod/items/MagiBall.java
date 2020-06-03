package fr.marcjus.mod.items;

import fr.marcjus.mod.MainMod;
import fr.marcjus.mod.entities.EntityMagiBall;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

@Deprecated
public class MagiBall extends Item {
	
	public MagiBall(String name){
		setUnlocalizedName(name);
		setRegistryName(name);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		Vec3d look = player.getLookVec();
		EntityMagiBall magiball = new EntityMagiBall(world, 1.0d, 1.0d, 1.0d);
		magiball.setPosition(player.posX + look.x * 1.5d, player.posY + look.y * 1.5d, player.posZ + look.z * 1.5d);
		magiball.shoot(player, player.rotationYaw, player.rotationYaw, 0.0f, 1.5f, 0.5f);
		if(!world.isRemote){
			world.spawnEntity(magiball);
		}
		if(!player.capabilities.isCreativeMode){
			stack.shrink(1);
		}
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}

}

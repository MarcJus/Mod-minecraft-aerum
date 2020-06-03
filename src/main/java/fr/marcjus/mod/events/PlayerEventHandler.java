package fr.marcjus.mod.events;

import fr.marcjus.mod.helper.JumpHelper;
import fr.marcjus.mod.init.ItemsMod;
import fr.marcjus.mod.utils.References;
import fr.marcjus.mod.utils.StringToComponent;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

@EventBusSubscriber(modid = References.MODID)
public class PlayerEventHandler {


	@SubscribeEvent
	public static void onFall(LivingFallEvent e) {
		if (e.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) e.getEntity();
			ItemStack boots = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
			BlockPos playerPos = player.getPosition();
			BlockPos pos = new BlockPos(playerPos.getX(), playerPos.getY() - 1, playerPos.getZ());
			World world = player.world;
			IBlockState state = world.getBlockState(pos);
			Block block = state.getBlock();
			if (boots != null) {
				if (boots.getItem() == ItemsMod.invisibleBoots || boots.getItem() == ItemsMod.aerumBoots) {
					e.setDistance(0);
				}
			} 
		}
	}
	
	@SubscribeEvent
	public static void onJump(LivingJumpEvent e){
		if(e.getEntityLiving() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) e.getEntity();
			if(!JumpHelper.getJumps().containsKey(player)){
				JumpHelper.getJumps().put(player, 1);
			} else {
				JumpHelper.addJump(player);
			}
		}
	}
	
	@SubscribeEvent
	public static void onGround(LivingUpdateEvent e){
		if(e.getEntityLiving() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) e.getEntityLiving();
			if(player.onGround){
				JumpHelper.getJumps().remove(player);
				JumpHelper.getJumps().put(player, 0);
			}
		}
	}
	
}

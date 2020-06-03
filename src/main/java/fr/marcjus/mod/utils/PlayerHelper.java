package fr.marcjus.mod.utils;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;

public class PlayerHelper {
	
	public static void setPlayerSize(EntityPlayer player, float width, float height, float eyeHeight){
		AxisAlignedBB axisalignedbb = player.getEntityBoundingBox();
		player.width = width;
		player.height = height;
		
		player.setEntityBoundingBox(new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ,
                axisalignedbb.minX - (double) width, axisalignedbb.minY - (double) height,
                axisalignedbb.minZ - (double) width));
		player.getEntityBoundingBox().setMaxY(axisalignedbb.maxY);
		
		player.eyeHeight = eyeHeight;
	}
	
	public static void resetPlayerSize(EntityPlayer player){
		setPlayerSize(player, 0.6f, 1.8f, 1.62f);
	}

}

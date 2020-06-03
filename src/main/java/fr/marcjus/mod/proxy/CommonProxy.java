package fr.marcjus.mod.proxy;

import fr.marcjus.mod.items.aerum.AerumStick;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CommonProxy {
	
	public void registerRender() {
		System.out.println("methode cote serveur");
	}


}

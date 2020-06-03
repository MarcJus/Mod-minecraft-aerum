package fr.marcjus.mod.gui;

import fr.marcjus.mod.blocks.tile.TileEntityAerumChest;
import fr.marcjus.mod.gui.container.ContainerAerumChest;
import fr.marcjus.mod.utils.References;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == References.AERUM_CHEST)return new ContainerAerumChest(player.inventory, (TileEntityAerumChest) world.getTileEntity(new BlockPos(x, y, z)), player);
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == References.AERUM_CHEST)return new GuiAerumChest(player.inventory, (TileEntityAerumChest) world.getTileEntity(new BlockPos(x, y, z)), player);
		return null;
	}

}

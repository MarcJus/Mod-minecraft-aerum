package fr.marcjus.mod.blocks.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityElevator extends TileEntity {
	
	private int usage = 0;
	private String lastPlayer = null;
	
	public int getUsage() {
		return usage;
	}
	
	public void addUsage(){
		usage ++;
	}
	
	public String getLastPlayer() {
		if(lastPlayer == null){
			return "§aAucun Joueur";
		}
		return lastPlayer;
	}
	
	public void setLastPlayer(String lastPlayer) {
		this.lastPlayer = lastPlayer;
	}
	
	public void clearLastPlayer(){
		this.lastPlayer = null;
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("Usage", usage);
		compound.setString("LastPlayer", lastPlayer);
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.usage = compound.getInteger("Usage");
		this.lastPlayer = compound.getString("LastPlayer");
	}

}

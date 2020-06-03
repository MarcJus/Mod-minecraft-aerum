package fr.marcjus.mod.world;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class PollutionStorage implements IStorage<IPollution> {
	
	@CapabilityInject(IPollution.class)
	public static Capability<IPollution> POLLUTION_CAPABILITY = null;

	@Override
	public NBTBase writeNBT(Capability<IPollution> capability, IPollution instance, EnumFacing side) {
		NBTTagCompound nbt = new NBTTagCompound();
		return nbt;
	}

	@Override
	public void readNBT(Capability<IPollution> capability, IPollution instance, EnumFacing side, NBTBase base) {
		if(base instanceof NBTTagCompound){
			NBTTagCompound nbt = (NBTTagCompound) base;
			
		}
	}

}

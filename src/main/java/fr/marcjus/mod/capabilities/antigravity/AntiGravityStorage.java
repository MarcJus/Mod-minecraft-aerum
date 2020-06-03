package fr.marcjus.mod.capabilities.antigravity;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class AntiGravityStorage implements IStorage<IAntiGravity> {
	
	@CapabilityInject(IAntiGravity.class)
	public static final Capability<IAntiGravity> ANTI_GRAVITY_CAPABILITY = null;

	@Override
	public NBTBase writeNBT(Capability<IAntiGravity> capability, IAntiGravity instance, EnumFacing side) {
		NBTTagCompound tag = new NBTTagCompound();
		return tag;
	}

	@Override
	public void readNBT(Capability<IAntiGravity> capability, IAntiGravity instance, EnumFacing side, NBTBase nbt) {
		if(nbt instanceof NBTTagCompound){
			NBTTagCompound compound = (NBTTagCompound) nbt;
		}
	}

}

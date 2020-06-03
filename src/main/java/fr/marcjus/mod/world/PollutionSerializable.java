package fr.marcjus.mod.world;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class PollutionSerializable implements ICapabilitySerializable<NBTBase> {
	
	protected IPollution pollution;
	
	public PollutionSerializable() {
		this.pollution = new DefaultPollution();
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == PollutionStorage.POLLUTION_CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return hasCapability(capability, facing) ? PollutionStorage.POLLUTION_CAPABILITY.cast(this.pollution) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return PollutionStorage.POLLUTION_CAPABILITY.writeNBT(this.pollution, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		PollutionStorage.POLLUTION_CAPABILITY.readNBT(pollution, null, nbt);
	}

	
}

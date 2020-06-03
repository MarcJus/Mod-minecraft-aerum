package fr.marcjus.mod.capabilities.antigravity;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class AntiGravityProvider implements ICapabilitySerializable<NBTBase>{
	
	protected IAntiGravity antigravity;

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == AntiGravityStorage.ANTI_GRAVITY_CAPABILITY;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return this.hasCapability(capability, facing) ? AntiGravityStorage.ANTI_GRAVITY_CAPABILITY.cast(antigravity) : null;
	}

	@Override
	public NBTBase serializeNBT() {
		return AntiGravityStorage.ANTI_GRAVITY_CAPABILITY.writeNBT(antigravity, null);
	}

	@Override
	public void deserializeNBT(NBTBase nbt) {
		AntiGravityStorage.ANTI_GRAVITY_CAPABILITY.readNBT(antigravity, null, nbt);
	}

}

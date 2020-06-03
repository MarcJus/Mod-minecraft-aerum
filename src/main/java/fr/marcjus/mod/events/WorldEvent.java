package fr.marcjus.mod.events;

import fr.marcjus.mod.capabilities.antigravity.AntiGravityProvider;
import fr.marcjus.mod.utils.References;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = References.MODID)
public class WorldEvent {
	
	public static final ResourceLocation CAPABILITY_LOCATION = new ResourceLocation(References.MODID, "antigravity"); 
	
	@SubscribeEvent
	public static void onAttachCapability(AttachCapabilitiesEvent<World> e){
		e.addCapability(CAPABILITY_LOCATION, new AntiGravityProvider());
	}

}

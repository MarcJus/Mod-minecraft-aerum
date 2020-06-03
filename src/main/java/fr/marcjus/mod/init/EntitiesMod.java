package fr.marcjus.mod.init;

import fr.marcjus.mod.MainMod;
import fr.marcjus.mod.entities.EntityMagiBall;
import fr.marcjus.mod.utils.References;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntitiesMod {
	
	public static void init(){
		registerProjectile("magiball", References.MAGIBALL, EntityMagiBall.class, ItemsMod.magiball);
	}

	private static void registerProjectile(String name, int id, Class<? extends Entity > clazz, Item item) {
		EntityRegistry.registerModEntity(new ResourceLocation(name), clazz, name, id, MainMod.instance, 64, 10, true);
	}
	
}

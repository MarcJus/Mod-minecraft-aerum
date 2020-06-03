package fr.marcjus.mod.init;

import fr.marcjus.mod.blocks.Present;
import fr.marcjus.mod.items.InvisibleArmor;
import fr.marcjus.mod.items.ItemBlockPresent;
import fr.marcjus.mod.items.MagiBall;
import fr.marcjus.mod.items.TimeModifier;
import fr.marcjus.mod.items.aerum.AerumArmor;
import fr.marcjus.mod.items.aerum.AerumAxe;
import fr.marcjus.mod.items.aerum.AerumIngot;
import fr.marcjus.mod.items.aerum.AerumKiller;
import fr.marcjus.mod.items.aerum.AerumPickaxe;
import fr.marcjus.mod.items.aerum.AerumStick;
import fr.marcjus.mod.items.aerum.AerumSword;
import fr.marcjus.mod.utils.References;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlime;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = References.MODID)
public class ItemsMod {
	
	public static Item aerum_killer, aerumHelmet, aerumChestplate, aerumLeggings, aerumBoots, aerumSword, aerumPickaxe, aerumAxe, aerum_ingot, aerum_stick;
	public static Item invisibleHelmet, invisibleChestplate, invisibleLeggings, invisibleBoots;
	public static Item time_modifier;
	public static Item present_items;
	public static Item magiball;
	public static ToolMaterial aerum_material = EnumHelper.addToolMaterial("aerum", 3, 3000, 15.0f, 5f, 10);
	
	public static void init(){
		aerum_killer = new AerumKiller("aerum_killer");
		aerumHelmet = new AerumArmor(EntityEquipmentSlot.HEAD).setRegistryName("aerum_helmet").setUnlocalizedName("aerum_helmet");
		aerumChestplate = new AerumArmor(EntityEquipmentSlot.CHEST).setRegistryName("aerum_chestplate").setUnlocalizedName("aerum_chestplate");
		aerumLeggings = new AerumArmor(EntityEquipmentSlot.LEGS).setRegistryName("aerum_leggings").setUnlocalizedName("aerum_leggings");
		aerumBoots = new AerumArmor(EntityEquipmentSlot.FEET).setRegistryName("aerum_boots").setUnlocalizedName("aerum_boots");
		aerumSword = new AerumSword("aerum_sword");
		aerumPickaxe = new AerumPickaxe("aerum_pickaxe");
		aerumAxe = new AerumAxe("aerum_axe");
		aerum_ingot = new AerumIngot("aerum_ingot");
		invisibleHelmet = new InvisibleArmor(EntityEquipmentSlot.HEAD, "invisible_helmet");
		invisibleChestplate = new InvisibleArmor(EntityEquipmentSlot.CHEST, "invisible_chestplate");
		invisibleLeggings = new InvisibleArmor(EntityEquipmentSlot.LEGS, "invisible_leggings");
		invisibleBoots = new InvisibleArmor(EntityEquipmentSlot.FEET, "invisible_boots");
		aerum_stick = new AerumStick("aerum_stick");
		time_modifier = new TimeModifier("time_modifier");
		magiball = new MagiBall("magiball");
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> e){
		e.getRegistry().registerAll(aerum_killer, aerumHelmet, aerumChestplate, aerumLeggings, aerumBoots, aerumAxe, aerumSword, aerumPickaxe, aerum_ingot, aerum_stick);
		e.getRegistry().registerAll(invisibleHelmet, invisibleChestplate, invisibleLeggings, invisibleBoots);
		e.getRegistry().registerAll(time_modifier, magiball);
	}
	
	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event){
		registerRender(aerum_killer);
		registerRender(aerumHelmet);
		registerRender(aerumChestplate);
		registerRender(aerumLeggings);
		registerRender(aerumBoots);
		registerRender(aerumAxe);
		registerRender(aerumSword);
		registerRender(aerumPickaxe);
		registerRender(aerum_ingot);
		registerRender(invisibleHelmet);
		registerRender(invisibleChestplate);
		registerRender(invisibleLeggings);
		registerRender(invisibleBoots);
		registerRender(aerum_stick);
		registerRender(time_modifier);
		registerRender(magiball);
		
	}
	
	private static void registerRender(Item item){
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	private static void registerRender(Item item, int metadata){
		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
}

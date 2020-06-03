package fr.marcjus.mod.proxy;

import org.lwjgl.input.Keyboard;

import fr.marcjus.mod.MainMod;
import fr.marcjus.mod.entities.EntityMagiBall;
import fr.marcjus.mod.events.client.ClientEvents;
import fr.marcjus.mod.init.BlocksMod;
import fr.marcjus.mod.init.ItemsMod;
import fr.marcjus.mod.network.PacketPlayerDownElevator;
import fr.marcjus.mod.network.PacketPlayerUpElevator;
import fr.marcjus.mod.utils.References;
import fr.marcjus.mod.utils.StringToComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy{
	
	public static KeyBinding key_up_elevator;
	public static KeyBinding key_down_elevator;
	private String aerum_category = "key.categories.aerum";
	
	public ClientProxy() {
		key_up_elevator = new KeyBinding("modmarcjus.up_elevator", Keyboard.KEY_UP, aerum_category);
		key_down_elevator = new KeyBinding("modmarcjus.down_elevator", Keyboard.KEY_DOWN, aerum_category);
		ModelBakery.registerItemVariants(Item.getItemFromBlock(BlocksMod.present), new ResourceLocation(References.MODID, "present_normal"), new ResourceLocation(References.MODID, "present_luckiest"));
		ClientRegistry.registerKeyBinding(key_up_elevator);
		ClientRegistry.registerKeyBinding(key_down_elevator);
		MinecraftForge.EVENT_BUS.register(new ClientEvents());
		RenderingRegistry.registerEntityRenderingHandler(EntityMagiBall.class, new RenderSnowball<>(Minecraft.getMinecraft().getRenderManager(), ItemsMod.magiball, Minecraft.getMinecraft().getRenderItem()));
	}
	
	@Override
	public void registerRender() {
		System.out.println("methode cote client");
	}
	
}

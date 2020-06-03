package fr.marcjus.mod.events.client;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.lwjgl.input.Keyboard;

import fr.marcjus.mod.MainMod;
import fr.marcjus.mod.gui.GuiCustomAccessDenied;
import fr.marcjus.mod.gui.GuiCustomMainMenu;
import fr.marcjus.mod.init.NetworkMod;
import fr.marcjus.mod.network.PacketPlayerJump;
import fr.marcjus.mod.network.PacketPlayerDownElevator;
import fr.marcjus.mod.network.PacketPlayerUpElevator;
import fr.marcjus.mod.proxy.ClientProxy;
import fr.marcjus.mod.utils.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiErrorScreen;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.main.Main;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.client.GuiAccessDenied;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber(modid = References.MODID)
public class ClientEvents {
	
	public static int jumps = 0;
	
	@SubscribeEvent
	public static void onKey(KeyInputEvent e){
		if(ClientProxy.key_up_elevator.isPressed()){
			NetworkMod.getNetwork().sendToServer(new PacketPlayerUpElevator());
		}
		if(ClientProxy.key_down_elevator.isPressed()){
			NetworkMod.getNetwork().sendToServer(new PacketPlayerDownElevator());
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			NetworkMod.getNetwork().sendToServer(new PacketPlayerJump());
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onOpenMainMenu(GuiOpenEvent event){
		GuiScreen gui = event.getGui();
		if(gui instanceof GuiMainMenu){
			event.setGui(new GuiCustomMainMenu());
		} else if (gui instanceof GuiAccessDenied){
			GuiAccessDenied denied = (GuiAccessDenied) gui;
			Class clazzGuiAccessDenied = denied.getClass();
			Class clazzGuiCustomAccessDenied = GuiCustomAccessDenied.class;
			Class clazzGuiOpenEvent = GuiOpenEvent.class;
			try{
				Field serverdatafield = clazzGuiAccessDenied.getDeclaredField("data");
				serverdatafield.setAccessible(true);
				Object serverdata = serverdatafield.get(denied);
				Constructor constructor = clazzGuiCustomAccessDenied.getDeclaredConstructor(ServerData.class);
				Object instance = constructor.newInstance(serverdata);
				
				Method setGui = clazzGuiOpenEvent.getMethod("setGui", GuiScreen.class);
				setGui.invoke(instance, instance);
				serverdatafield.setAccessible(false);
			} catch(Exception e){
				e.printStackTrace();
			}
		} 
	}
	
}

package fr.marcjus.mod;

import fr.marcjus.mod.blocks.tile.TileEntityAerumChest;
import fr.marcjus.mod.blocks.tile.TileEntityElevator;
import fr.marcjus.mod.capabilities.antigravity.AntiGravityStorage;
import fr.marcjus.mod.capabilities.antigravity.DefaultAntiGravity;
import fr.marcjus.mod.capabilities.antigravity.IAntiGravity;
import fr.marcjus.mod.commands.CommandGod;
import fr.marcjus.mod.commands.CommandTop;
import fr.marcjus.mod.commands.DayCommand;
import fr.marcjus.mod.commands.NightCommand;
import fr.marcjus.mod.enchantments.EnchantmentDoubleJump;
import fr.marcjus.mod.events.EntityEventHandler;
import fr.marcjus.mod.events.PlayerEventHandler;
import fr.marcjus.mod.gui.GuiHandler;
import fr.marcjus.mod.init.*;
import fr.marcjus.mod.items.AerumTab;
import fr.marcjus.mod.network.PacketPlayerDownElevator;
import fr.marcjus.mod.network.PacketPlayerUpElevator;
import fr.marcjus.mod.proxy.CommonProxy;
import fr.marcjus.mod.utils.References;
import fr.marcjus.mod.world.DefaultPollution;
import fr.marcjus.mod.world.IPollution;
import fr.marcjus.mod.world.PollutionStorage;
import fr.marcjus.mod.world.biome.BiomeList;
import net.minecraft.block.BlockFurnace;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.tileentity.TileEntityEndGatewayRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.item.Item;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.tileentity.TileEntityEndGateway;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.world.ChunkEvent.Load;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION, acceptedMinecraftVersions = References.MINECRAFT_VERSION, guiFactory = References.GUI_CONFIG)
public class MainMod {
	
	public static CreativeTabs aerumTab = new AerumTab("modmarcjus");
	public static boolean aerum_boots_particles = true;
	
	public WorldGeneration generation = new WorldGeneration();
	public StructureGenerator structure = new StructureGenerator();
	
	@SidedProxy(clientSide = References.CLIENT_PROXY, serverSide = References.COMMON_PROXY)
	public static CommonProxy proxy;
	
	@Mod.Instance
	public static MainMod instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e){
		BlocksMod.init();
		ItemsMod.init();
		RecipesMod.init();
		NetworkMod.init();
		EntitiesMod.init();
		GameRegistry.registerWorldGenerator(generation, 0);
		GameRegistry.registerWorldGenerator(structure, 0);
		GameRegistry.registerTileEntity(TileEntityElevator.class, References.MODID+"TileEntityElevator");
		GameRegistry.registerTileEntity(TileEntityAerumChest.class, References.MODID+"TileEntityAerumChest");
		CapabilityManager.INSTANCE.register(IAntiGravity.class, new AntiGravityStorage(), DefaultAntiGravity::new);
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e){
		proxy.registerRender();
		MinecraftForge.EVENT_BUS.register(new EntityEventHandler());
		MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
		NetworkRegistry.INSTANCE.registerGuiHandler(this.instance, new GuiHandler());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e){
		
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent e){
		CommandsMod.init(e);
	}
	
}

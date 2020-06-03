package fr.marcjus.mod.init;

import java.util.function.Function;

import fr.marcjus.mod.blocks.AerumBlock;
import fr.marcjus.mod.blocks.AerumFurnace;
import fr.marcjus.mod.blocks.AerumOre;
import fr.marcjus.mod.blocks.ElevatorBlock;
import fr.marcjus.mod.blocks.GreenBackground;
import fr.marcjus.mod.blocks.JumpPad;
import fr.marcjus.mod.blocks.CaveBlock;
import fr.marcjus.mod.blocks.KillerBlock;
import fr.marcjus.mod.blocks.LandingPad;
import fr.marcjus.mod.blocks.Present;
import fr.marcjus.mod.items.ItemBlockPresent;
import fr.marcjus.mod.utils.References;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBarrier;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemSlab;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = References.MODID)
public class BlocksMod {

	public static Block green_background;
	public static Block elevator;
	public static Block aerum_ore;
	public static Block aerum_block;
	public static Block killer_block;
	public static Block cave_block;
	public static Block present;
	public static Block jump_slab;
	public static Block jump_slab_double;
	public static Block landing_slab;
	public static Block landing_slab_double;

	public static void init() {
		green_background = new GreenBackground("test_block", Material.ROCK);
		elevator = new ElevatorBlock("elevator", Material.ROCK);
		aerum_ore = new AerumOre("aerum_ore", 2);
		aerum_block = new AerumBlock("aerum_block", Material.ROCK);
		killer_block = new KillerBlock();
		cave_block = new CaveBlock();
		present = new Present(Material.ROCK);
		jump_slab = new JumpPad.Half("jump_pad", Material.ROCK);
		jump_slab_double = new JumpPad.Double("jump_pad_double", Material.ROCK);
		landing_slab = new LandingPad.Half(Material.ROCK, "landing_pad");
		landing_slab_double = new LandingPad.Double(Material.ROCK, "landing_pad_double");

		Blocks.BARRIER.setCreativeTab(CreativeTabs.DECORATIONS);
		Blocks.COMMAND_BLOCK.setCreativeTab(CreativeTabs.DECORATIONS);
		Blocks.CONCRETE.slipperiness = 0.6f;
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(green_background, elevator, aerum_ore, aerum_block, killer_block, cave_block,
				present, jump_slab, jump_slab_double, landing_slab, landing_slab_double);
	}

	@SubscribeEvent
	public static void registerItemBlock(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(
				new ItemBlock(green_background).setRegistryName(green_background.getRegistryName()),
				new ItemBlock(elevator).setRegistryName(elevator.getRegistryName()),
				new ItemBlock(aerum_ore).setRegistryName(aerum_ore.getRegistryName()),
				new ItemBlock(aerum_block).setRegistryName(aerum_block.getRegistryName()),
				new ItemBlock(killer_block).setRegistryName(killer_block.getRegistryName()),
				new ItemBlock(cave_block).setRegistryName(cave_block.getRegistryName()),
				new ItemBlockPresent(present, new String[] { "present", "present_1" })
						.setRegistryName(present.getRegistryName()),
				new ItemSlab(jump_slab, (JumpPad) jump_slab, (JumpPad) jump_slab_double)
						.setRegistryName(jump_slab.getRegistryName()),
				new ItemSlab(landing_slab, (LandingPad) landing_slab, (LandingPad) landing_slab_double)
						.setRegistryName(landing_slab.getRegistryName()));
	}

	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {
		registerRender(getItemFromBlock(green_background));
		registerRender(getItemFromBlock(elevator));
		registerRender(getItemFromBlock(aerum_ore));
		registerRender(getItemFromBlock(aerum_block));
		registerRender(getItemFromBlock(killer_block));
		registerRender(getItemFromBlock(cave_block));
		for (Present.EnumType type : Present.EnumType.values()) {
			registerRender(getItemFromBlock(present), type.getMetadata(), "present_" + type.getName());
		}
		registerRender(getItemFromBlock(jump_slab));
		registerRender(getItemFromBlock(jump_slab_double));
		registerRender(getItemFromBlock(landing_slab));
		registerRender(getItemFromBlock(landing_slab_double));

	}

	private static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0,
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	private static void registerRender(Block block) {
		ModelLoader.setCustomModelResourceLocation(getItemFromBlock(block), 0,
				new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
	
	private static void registerRender(Block block, int metadata) {
		ModelLoader.setCustomModelResourceLocation(getItemFromBlock(block), metadata,
				new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}

	private static void registerRender(Item item, int metadata, String fileName) {
		ModelLoader.setCustomModelResourceLocation(item, metadata,
				new ModelResourceLocation(new ResourceLocation(References.MODID, fileName), "inventory"));
	}

	public static Item getItemFromBlock(Block block) {
		Function<Block, Item> function = Item::getItemFromBlock;
		return function.apply(block);
	}

	public static void setBlockName(Block block, String name) {
		block.setRegistryName(References.MODID, name).setUnlocalizedName(name);
	}

}

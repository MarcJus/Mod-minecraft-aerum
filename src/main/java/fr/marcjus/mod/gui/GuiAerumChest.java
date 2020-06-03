package fr.marcjus.mod.gui;

import fr.marcjus.mod.blocks.tile.TileEntityAerumChest;
import fr.marcjus.mod.gui.container.ContainerAerumChest;
import fr.marcjus.mod.utils.References;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.util.ResourceLocation;

public class GuiAerumChest extends GuiContainer{
	
	private static final ResourceLocation GUI_CHEST = new ResourceLocation(References.MODID+":textures/gui/aerum_chest.png");
	private final InventoryPlayer playerInventory;
	private	final TileEntityAerumChest te;
	private final int inventoryRows;

	public GuiAerumChest(InventoryPlayer playerInv, TileEntityAerumChest chestInv, EntityPlayer player) {
		super(new ContainerAerumChest(playerInv, chestInv, player));
		this.playerInventory = playerInv;
		this.te = chestInv;
		
		this.xSize = 176;
		//this.ySize = 240;
		inventoryRows = chestInv.getSizeInventory() / 9;
		this.ySize = 114 + inventoryRows * 18;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRenderer.drawString(this.te.getDisplayName().getFormattedText(), 8, 6, 4210752);
		this.fontRenderer.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(GUI_CHEST);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	

}

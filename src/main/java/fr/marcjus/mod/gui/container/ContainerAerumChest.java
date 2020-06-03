package fr.marcjus.mod.gui.container;

import fr.marcjus.mod.blocks.tile.TileEntityAerumChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerAerumChest extends Container{
	
	private final int numRows;
	private final TileEntityAerumChest chestInventory;
	
	public ContainerAerumChest(InventoryPlayer playerInv, TileEntityAerumChest chestInventory, EntityPlayer player) {
		this.chestInventory = chestInventory;
		this.numRows = chestInventory.getSizeInventory() / 9;
		int i = (this.numRows - 4) * 18;
		for(int j = 0; j<numRows; j++){
			
			for(int k = 0; k<9; k++){
				
				this.addSlotToContainer(new Slot(chestInventory, k + j*9, 8 + k*18, 18 + j*18));
				
			}
			
		}
		
		for(int l = 0; l < 3; l++){
			for(int j1 = 0; j1 < 9; j1 ++){
			  //this.addSlotToContainer(new Slot(playerInv, j1 + l*9 + 9, 8 + j1*18, 175 + l*18));
				this.addSlotToContainer(new Slot(playerInv, j1 + l * 9 + 9, 8 + j1 * 18, 104 + l  * 18 + i));
			}
		}
		
		for(int i1 = 0; i1 < 9; i1++){
		  //this.addSlotToContainer(new Slot(playerInv, i1, 8 + i1 * 18, 233));
			this.addSlotToContainer(new Slot(playerInv, i1, 8 + i1 * 18, 162 + i));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.chestInventory.isUsableByPlayer(playerIn);
	}
	
	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);
		chestInventory.closeInventory(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if(slot != null && slot.getHasStack()){
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			if(index < this.numRows * 9){
				if(!this.mergeItemStack(stack1, this.numRows * 9, this.inventorySlots.size(), true)){
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(stack1, 0, this.numRows * 9, false)){
				return ItemStack.EMPTY;
			}
			if(stack1.isEmpty()){
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}
		return stack;
	}
	
	public TileEntityAerumChest getChestInventory() {
		return chestInventory;
	}

}

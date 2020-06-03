package fr.marcjus.mod.blocks.tile;

import fr.marcjus.mod.gui.container.ContainerAerumChest;
import fr.marcjus.mod.utils.References;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityAerumChest extends TileEntityLockableLoot implements ITickable{
	
	private NonNullList<ItemStack> chestContents = NonNullList.<ItemStack>withSize(63, ItemStack.EMPTY);
	public int numPlayersUsing, ticksSinceSync;
	public float lidAngle, prevLidAngle;
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.chestContents = NonNullList.<ItemStack>withSize(getSizeInventory(), ItemStack.EMPTY);
		
		if(!this.checkLootAndRead(compound))ItemStackHelper.loadAllItems(compound, chestContents);
		if(compound.hasKey("CustomName", 8))this.customName = compound.getString("CustomName");
	}
	
	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player){
		return new ContainerAerumChest(playerInventory, this, player);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		
		if(!this.checkLootAndWrite(compound))ItemStackHelper.saveAllItems(compound, chestContents);
		if(compound.hasKey("CustomName", 8))compound.setString("CustomName", customName);
		return compound;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	@Override
	public int getSizeInventory() {
		return 63;
	}

	@Override
	public boolean isEmpty() {
		for(ItemStack stack : chestContents){
			return !stack.isEmpty();
		}
		return true;
	}
	
	@Override
	public boolean hasCustomName() {
		return super.hasCustomName();
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	@Override
	public void openInventory(EntityPlayer player) {
		this.numPlayersUsing ++;
		this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numPlayersUsing);
		this.world.notifyNeighborsOfStateChange(pos, blockType, false);
	}
	
	@Override
	public void closeInventory(EntityPlayer player) {
		this.numPlayersUsing --;
		this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numPlayersUsing);
		this.world.notifyNeighborsOfStateChange(pos, blockType, false);
	}
	
	@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container.aerum_chest";
	}

	@Override
	public String getGuiID() {
		return References.MODID+":aerum_chest";
	}
	
	@Override
	public void update() {
		if (!this.world.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + pos.getX() + pos.getY() + pos.getZ()) % 200 == 0)
        {
            this.numPlayersUsing = 0;
            float f = 5.0F;
 
            for (EntityPlayer entityplayer : this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB((double)((float)pos.getX() - 5.0F), (double)((float)pos.getY() - 5.0F), (double)((float)pos.getZ() - 5.0F), (double)((float)(pos.getX() + 1) + 5.0F), (double)((float)(pos.getY() + 1) + 5.0F), (double)((float)(pos.getZ() + 1) + 5.0F))))
            {
                if (entityplayer.openContainer instanceof ContainerAerumChest)
                {
                    if (((ContainerAerumChest)entityplayer.openContainer).getChestInventory() == this)
                    {
                        this.numPlayersUsing ++;
                    }
                }
            }
        }
       
        this.prevLidAngle = this.lidAngle;
        float f1 = 0.1F;
 
        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F)
        {
            double d1 = (double)pos.getX() + 0.5D;
            double d2 = (double)pos.getZ() + 0.5D;
            this.world.playSound((EntityPlayer)null, d1, (double)pos.getY() + 0.5D, d2, SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
        }
 
        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F)
        {
            float f2 = this.lidAngle;
 
            if (this.numPlayersUsing > 0)
            {
                this.lidAngle += 0.1F;
            }
            else
            {
                this.lidAngle -= 0.1F;
            }
 
            if (this.lidAngle > 1.0F)
            {
                this.lidAngle = 1.0F;
            }
 
            float f3 = 0.5F;
 
            if (this.lidAngle < 0.5F && f2 >= 0.5F)
            {
                double d3 = (double)pos.getX() + 0.5D;
                double d0 = (double)pos.getZ() + 0.5D;
                this.world.playSound((EntityPlayer)null, d3, (double)pos.getY() + 0.5D, d0, SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
            }
 
            if (this.lidAngle < 0.0F)
            {
                this.lidAngle = 0.0F;
            }
        }
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.chestContents;
	}
	
}

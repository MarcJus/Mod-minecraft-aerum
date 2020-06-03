package fr.marcjus.mod.items.aerum;

import fr.marcjus.mod.MainMod;
import fr.marcjus.mod.init.BlocksMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AerumKiller extends Item{
	
	public AerumKiller(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(MainMod.aerumTab);
		setFull3D();
		setMaxStackSize(1);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if(!world.isRemote){
			Block block = world.getBlockState(pos).getBlock();
			if(block == BlocksMod.green_background){
				world.destroyBlock(pos, false);
				
				EntityItem item = new EntityItem(world);
				item.setPosition(pos.getX(), pos.getY()+0.6d, pos.getZ());
				item.setItem(new ItemStack(BlocksMod.green_background));
				world.spawnEntity(item);
			}
		}
		
		return super.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		
		if(!(entity instanceof EntityPlayer)){
			entity.setDead();
		}
		
		return super.onLeftClickEntity(stack, player, entity);
	}
	
	
}

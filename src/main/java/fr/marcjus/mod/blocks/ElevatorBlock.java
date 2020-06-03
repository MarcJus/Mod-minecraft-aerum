package fr.marcjus.mod.blocks;

import fr.marcjus.mod.MainMod;
import fr.marcjus.mod.blocks.tile.TileEntityElevator;
import fr.marcjus.mod.utils.StringToComponent;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ElevatorBlock extends Block implements ITileEntityProvider{

	public ElevatorBlock(String name, Material materialIn) {
		super(materialIn);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(MainMod.aerumTab);
		setHardness(3.0f);
		setResistance(0.5f);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!world.isRemote){
			TileEntity tile = world.getTileEntity(pos);
			if(tile instanceof TileEntityElevator){
				TileEntityElevator elevator = (TileEntityElevator) tile;
				player.sendStatusMessage(StringToComponent.convert("Test"), true);
				player.sendMessage(StringToComponent.convert("Dernier joueur : "+elevator.getLastPlayer()+", nombre d'utilisation : "+elevator.getUsage()));
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityElevator();
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
}

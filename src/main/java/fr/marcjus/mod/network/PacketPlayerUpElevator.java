package fr.marcjus.mod.network;

import fr.marcjus.mod.MainMod;
import fr.marcjus.mod.blocks.ElevatorBlock;
import fr.marcjus.mod.blocks.tile.TileEntityElevator;
import fr.marcjus.mod.utils.StringToComponent;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketPlayerUpElevator implements IMessage {

	public PacketPlayerUpElevator() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	public static class Handler implements IMessageHandler<PacketPlayerUpElevator, IMessage> {

		@Override
		public IMessage onMessage(PacketPlayerUpElevator message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().player;
			WorldServer world = player.getServerWorld();

			int x = MathHelper.floor(player.posX);
			int y = MathHelper.floor(player.posY - 1);
			int z = MathHelper.floor(player.posZ);

			BlockPos pos = new BlockPos(x, y, z);
			Block blockUnder = player.world.getBlockState(pos).getBlock();

			double highestPosY = y;
			boolean isSneaking = player.isSneaking();
			boolean move = false;

			if (blockUnder instanceof ElevatorBlock) {
				for (int i = 1; i < 30; i++) {
					BlockPos blockpos = new BlockPos(x, y + i, z);
					Block block = world.getBlockState(blockpos).getBlock();
					if (block instanceof ElevatorBlock) {
						if (world.getBlockState(new BlockPos(x, y + i + 1, z))
								.getBlock() instanceof BlockAir) {
							if (player.isSneaking()) {
								highestPosY = y + i;
								continue;
							} else {
								player.setPositionAndUpdate(x + 0.5, y + 1 + i, z + 0.5);
								player.sendMessage(StringToComponent.convert("§bPoof !"));
								move = true;
								TileEntity tile = world.getTileEntity(new BlockPos(x, y + i, z));
								if(tile instanceof TileEntityElevator){
									TileEntityElevator elevator = (TileEntityElevator) tile;
									elevator.setLastPlayer(player.getName());
								}
								TileEntity tile2 = world.getTileEntity(pos);
								if(tile2 instanceof TileEntityElevator){
									TileEntityElevator elevator = (TileEntityElevator) tile2;
									elevator.addUsage();
								}
								break;
							}

						}

					}
				}
				if (isSneaking) {
					player.setPositionAndUpdate(x + 0.5, highestPosY + 1, z + 0.5);
					move = true;
				}
				
			}
			if(!move){
				player.sendMessage(StringToComponent.convert("§cAucun elevateur au dessus ! "));
			}
			return null;
		}
	}

}

package fr.marcjus.mod.network;

import fr.marcjus.mod.blocks.ElevatorBlock;
import fr.marcjus.mod.blocks.tile.TileEntityElevator;
import fr.marcjus.mod.utils.StringToComponent;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockSlime;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketPlayerListHeaderFooter;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketPlayerDownElevator implements IMessage {

	public PacketPlayerDownElevator() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

	public static class Handler implements IMessageHandler<PacketPlayerDownElevator, IMessage> {

		@Override
		public IMessage onMessage(PacketPlayerDownElevator message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().player;
			WorldServer world = player.getServerWorld();

			int blockX = MathHelper.floor(player.posX);
			int blockY = MathHelper.floor(player.posY - 1);
			int blockZ = MathHelper.floor(player.posZ);

			BlockPos pos = new BlockPos(blockX, blockY, blockZ);
			Block blockUnder = world.getBlockState(pos).getBlock();
			boolean move = false;

			if (blockUnder instanceof ElevatorBlock) {
				for (int i = 1; i < 30; i++) {
					BlockPos blockpos = new BlockPos(blockX, blockY - i, blockZ);
					Block block = world.getBlockState(blockpos).getBlock();

					if (block instanceof ElevatorBlock
							&& world.getBlockState(new BlockPos(blockX, blockY - i + 1, blockZ))
									.getBlock() instanceof BlockAir) {
						player.setPositionAndUpdate(blockX + 0.5, blockY + 1 - i, blockZ + 0.5);
						player.sendMessage(StringToComponent.convert("§bPoof !"));
						move = true;
						TileEntity tile = world.getTileEntity(blockpos);
						if(tile instanceof TileEntityElevator){
							((TileEntityElevator) tile).setLastPlayer(player.getName());
						}
						TileEntity tile2 = world.getTileEntity(pos);
						if(tile2 instanceof TileEntityElevator){
							((TileEntityElevator) tile2).addUsage();
						}
						break;
					}
				}
			}
			if (!move) {
				player.sendMessage(StringToComponent.convert("§cAucun elevateur en dessous !"));
			}
			
			return null;
		}

	}

}

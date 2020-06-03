package fr.marcjus.mod.commands;

import fr.marcjus.mod.utils.StringToComponent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class CommandTop extends CommandBase {

	@Override
	public String getName() {
		return "top";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/top <level>";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (sender instanceof EntityPlayer) {
			EntityPlayerMP player = (EntityPlayerMP) sender;
			World world = player.world;

			double posX = MathHelper.floor(player.posX);
			double posY = MathHelper.floor(player.posY);
			double posZ = MathHelper.floor(player.posZ);

			for (int i = 0; i < 1000; i++) {
				BlockPos pos = new BlockPos(posX, posY + i + 1, posZ);
				Block block = world.getBlockState(pos).getBlock();
				if (!(block instanceof BlockAir)) {
					if (world.getBlockState(new BlockPos(posX, posY + i + 2, posZ)).getBlock() instanceof BlockAir) {
						if (world.getBlockState(new BlockPos(posX, posY + i + 3, posZ))
								.getBlock() instanceof BlockAir) {
							player.setPositionAndUpdate(posX + 0.5, posY + i + 1, posZ + 0.5);
							break;
						}

					}
				}
			}
		} else {
			sender.sendMessage(StringToComponent.convert("§cVous n'etes pas un joueur !"));
		}
	}

}

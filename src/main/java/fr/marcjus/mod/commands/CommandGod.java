package fr.marcjus.mod.commands;

import java.util.List;

import fr.marcjus.mod.events.PlayerEventHandler;
import fr.marcjus.mod.events.PlayerEventHandler;
import fr.marcjus.mod.utils.StringToComponent;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandGod extends CommandBase {

	@Override
	public String getName() {
		return "god";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/god <player>";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 0) {
			if (!(sender instanceof EntityPlayer)) {
				sender.sendMessage(StringToComponent.convert("§cUsage : /god <player>"));
				return;
			}
			EntityPlayerMP player = getCommandSenderAsPlayer(sender);
			PlayerCapabilities capabilities = player.capabilities;
			if(capabilities.disableDamage){
				capabilities.disableDamage = false;
				player.sendMessage(StringToComponent.convert("§cVous n'etes plus invincible !"));
			} else {
				capabilities.disableDamage = true;
				player.sendMessage(StringToComponent.convert("§2Vous etes invincible !"));
			}

		} else {
			String args1 = args[0];
			if(server.getPlayerList().getPlayerByUsername(args1) == null){
				sender.sendMessage(StringToComponent.convert("§cJoueur introuvable"));
				return;
			}
			EntityPlayerMP player = server.getPlayerList().getPlayerByUsername(args1);
			PlayerCapabilities capabilities = player.capabilities;
			if (capabilities.disableDamage) {
				capabilities.disableDamage = false;
				sender.sendMessage(StringToComponent.convert("§2"+args1+"§c n'est plus invincible !"));
				player.sendMessage(StringToComponent.convert("§cVous n'etes plus invincible"));
			} else {
				capabilities.disableDamage = true;
				sender.sendMessage(StringToComponent.convert("§2"+args1+" est invincible !"));
				player.sendMessage(StringToComponent.convert("§2Vous etes invincible"));
			}
		}
	}

}

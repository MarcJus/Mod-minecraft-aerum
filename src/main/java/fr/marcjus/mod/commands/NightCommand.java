package fr.marcjus.mod.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderTooltipEvent.Color;

public class NightCommand extends CommandBase {

	@Override
	public String getName() {
		return "night";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/night";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		server.getWorld(0).setWorldTime(13000);
		sender.sendMessage(new TextComponentString("\2472Il fait nuit dans ton monde !"));
	}

	

}

package fr.marcjus.mod.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public abstract class Command extends CommandBase {
	
	protected String name;
	protected String usage;
	
	public Command(String name, String usage) {
		this.name = name;
		this.usage = usage;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return usage;
	}

}

package fr.marcjus.mod.utils;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public abstract class Command extends CommandBase {
	
	protected final String name;
	
	public Command(String name){
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}

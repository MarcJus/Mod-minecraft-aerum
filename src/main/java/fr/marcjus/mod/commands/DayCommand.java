package fr.marcjus.mod.commands;

import java.util.List;
import java.util.function.Function;

import fr.marcjus.mod.utils.StringToComponent;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.BossInfo.Color;

public class DayCommand extends CommandBase {

	@Override
	public String getName() {
		return "day";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "/day";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		server.getWorld(0).setWorldTime(1000);
		sender.sendMessage(StringToComponent.convert("§2Il fait jour dans ce monde !"));
	}
	
}

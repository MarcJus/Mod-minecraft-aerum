package fr.marcjus.mod.init;

import fr.marcjus.mod.commands.CommandGod;
import fr.marcjus.mod.commands.CommandGravity;
import fr.marcjus.mod.commands.CommandTop;
import fr.marcjus.mod.commands.DayCommand;
import fr.marcjus.mod.commands.NightCommand;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommandsMod {
	
	public static void init(FMLServerStartingEvent e){
		e.registerServerCommand(new DayCommand());
		e.registerServerCommand(new NightCommand());
		e.registerServerCommand(new CommandTop());
		e.registerServerCommand(new CommandGod());
		e.registerServerCommand(new CommandGravity("gravity", "gravity [on:off]"));
	}

}

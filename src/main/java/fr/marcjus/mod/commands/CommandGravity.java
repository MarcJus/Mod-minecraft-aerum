package fr.marcjus.mod.commands;

import fr.marcjus.mod.capabilities.antigravity.AntiGravityStorage;
import fr.marcjus.mod.capabilities.antigravity.IAntiGravity;
import fr.marcjus.mod.utils.StringToComponent;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class CommandGravity extends Command{

	public CommandGravity(String name, String usage) {
		super(name, usage);
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(sender instanceof EntityPlayerMP){
			EntityPlayerMP player = (EntityPlayerMP) sender;
			World world = player.world;
			if(args.length == 0){
				player.sendMessage(StringToComponent.convert(world.getCapability(AntiGravityStorage.ANTI_GRAVITY_CAPABILITY, null).hasGravity() ? "Gravitee activee" : "Gravitee desactivee"));
			} else {
				String args1 = args[0];
				IAntiGravity antigravity = world.getCapability(AntiGravityStorage.ANTI_GRAVITY_CAPABILITY, null);
				if(args1.equalsIgnoreCase("on")){
					if(antigravity.hasGravity())
						player.sendMessage(StringToComponent.convert("La gravitee est deja activee"));
					else{
						antigravity.setGravity(true);
						player.sendMessage(StringToComponent.convert("Gravitee activee"));
					}
				} else if (args1.equalsIgnoreCase("off")){
					if(!antigravity.hasGravity()){
						player.sendMessage(StringToComponent.convert("La gravitee est deja desactivee"));
					} else {
						antigravity.setGravity(false);
						player.sendMessage(StringToComponent.convert("Gravitee activee"));
					}
				}
			}
		}
	}

}

package fr.marcjus.mod.init;

import fr.marcjus.mod.network.ClientMessage;
import fr.marcjus.mod.network.PacketPlayerJump;
import fr.marcjus.mod.network.PacketPlayerDownElevator;
import fr.marcjus.mod.network.PacketPlayerUpElevator;
import fr.marcjus.mod.network.ServerMessage;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public final class NetworkMod {

	private static SimpleNetworkWrapper network;
	private static int discriminator = 0;

	public static void init() {
		network = NetworkRegistry.INSTANCE.newSimpleChannel("AerumChannel");
		network.registerMessage(PacketPlayerUpElevator.Handler.class, PacketPlayerUpElevator.class, 0, Side.SERVER);
		network.registerMessage(PacketPlayerDownElevator.Handler.class, PacketPlayerDownElevator.class, 1, Side.SERVER);
		network.registerMessage(PacketPlayerJump.Handler.class, PacketPlayerJump.class, 2, Side.SERVER);

	}

	public static SimpleNetworkWrapper getNetwork() {
		return network;
	}

	public static int nextDiscriminator() {
		return discriminator++;
	}

	public static void sendToServer(IMessage message) {
		network.sendToServer(message);
	}

	public static void sendToPlayer(IMessage message, EntityPlayerMP player) {
		network.sendTo(message, player);
	}

	public static void sendToDimension(IMessage message, int dimension) {
		network.sendToDimension(message, dimension);
	}

}

package fr.marcjus.mod.network;

import fr.marcjus.mod.utils.StringToComponent;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.BlockBed;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketPlayerActionBarMessage extends ClientMessage<PacketPlayerActionBarMessage>{
	
	private String message;
	
	public PacketPlayerActionBarMessage(String message) {
		this.message = message;
	}
	
	public PacketPlayerActionBarMessage() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.message = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, message);
	}

	@Override
	public void client(PacketPlayerActionBarMessage message, MessageContext ctx) {
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		player.sendStatusMessage(StringToComponent.convert("Test"), false);
	}
	

}

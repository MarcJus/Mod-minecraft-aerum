package fr.marcjus.mod.network;

import fr.marcjus.mod.helper.JumpHelper;
import fr.marcjus.mod.utils.StringToComponent;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketPlayerJump implements IMessage {
	
	public PacketPlayerJump() {
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}
	
	public static class Handler implements IMessageHandler<PacketPlayerJump, IMessage>{

		@Override
		public IMessage onMessage(PacketPlayerJump message, MessageContext ctx) {
			EntityPlayerMP player = ctx.getServerHandler().player;
			if(player.onGround)return null;
			JumpHelper.jump(player);
			player.velocityChanged = true;
			return null;
		}
		
	}

}

package fr.marcjus.mod.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public abstract class ClientMessage<R extends IMessage> implements IMessage, IMessageHandler<R, IMessage> {

	@Override
	public IMessage onMessage(R message, MessageContext ctx) {
		if(ctx.side == Side.CLIENT){
			client(message, ctx);
		}
		return null;
	}
	
	public abstract void client(R message, MessageContext ctx);

}

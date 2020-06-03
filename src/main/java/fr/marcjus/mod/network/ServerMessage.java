package fr.marcjus.mod.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public abstract class ServerMessage<R extends IMessage> implements IMessage, IMessageHandler<R, IMessage> {
	
	@Override
	public IMessage onMessage(R message, MessageContext ctx) {
		if(ctx.side == Side.SERVER){
			server(message);
		}
		return null;
	}
	
	public abstract void server(R message);

}

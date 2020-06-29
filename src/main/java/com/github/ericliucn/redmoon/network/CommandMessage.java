package com.github.ericliucn.redmoon.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CommandMessage implements IMessage {

    public String cmd;

    public CommandMessage() {
    }

    public CommandMessage(String command){
        this.cmd = command;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.cmd = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.cmd);
    }

    public static class CommandMessageHandler implements IMessageHandler<CommandMessage, IMessage>{

        @Override
        public IMessage onMessage(CommandMessage message, MessageContext ctx) {
            if (ctx.side.isServer()) {
                EntityPlayerMP playerMP = ctx.getServerHandler().player;
                playerMP.getServer().addScheduledTask(()->{
                    playerMP.getServer()
                            .commandManager
                            .executeCommand(playerMP, message.cmd);
                });
            }
            return null;
        }
    }
}

package com.github.ericliucn.redmoon.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;

public class MyMessage  implements IMessage {

    public String cmd;

    public MyMessage() {
    }

    public MyMessage(String command){
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

    public static class MyMessageHandler implements IMessageHandler<MyMessage, IMessage>{

        @Override
        public IMessage onMessage(MyMessage message, MessageContext ctx) {
            EntityPlayerMP playerMP = ctx.getServerHandler().player;
            playerMP.getServerWorld().addScheduledTask(()->{
                if (!playerMP.world.isRemote){
                    Sponge.getCommandManager().process((CommandSource) playerMP, message.cmd);
                }
            });
            return null;
        }
    }
}

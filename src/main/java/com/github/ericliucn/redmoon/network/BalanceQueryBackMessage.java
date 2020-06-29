package com.github.ericliucn.redmoon.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class BalanceQueryBackMessage implements IMessage {

    public Double bal;

    public BalanceQueryBackMessage(){

    }

    public BalanceQueryBackMessage(double value){
        this.bal = value;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        this.bal = buf.readDouble();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeDouble(bal);
    }

    public static class BalanceQueryBackMessageHandler implements IMessageHandler<BalanceQueryBackMessage, IMessage>{

        @Override
        public IMessage onMessage(BalanceQueryBackMessage message, MessageContext ctx) {
            if (ctx.side.isClient()){
                Minecraft.getMinecraft().ingameGUI.getChatGUI().addToSentMessages(String.valueOf(message.bal));
                System.out.println(message.bal);
            }

            return null;
        }
    }
}

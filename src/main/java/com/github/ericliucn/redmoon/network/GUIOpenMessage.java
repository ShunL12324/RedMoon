package com.github.ericliucn.redmoon.network;

import com.github.ericliucn.redmoon.Main;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class GUIOpenMessage implements IMessage {

    public int ID;

    public GUIOpenMessage(){

    }

    public GUIOpenMessage(int id){
        this.ID = id;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.ID = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(ID);
    }

    public static class GUIOpenMessageHandler implements IMessageHandler<GUIOpenMessage, IMessage>{

        @Override
        public IMessage onMessage(GUIOpenMessage message, MessageContext ctx) {
            if (ctx.side.isClient()){
                Minecraft.getMinecraft().addScheduledTask(()->{
                    Main.PROXY.openGUI(message.ID);
                });
            }
            return null;
        }
    }
}

package com.github.ericliucn.redmoon.network.bank;

import com.github.ericliucn.redmoon.client.guis.BankGUI;
import com.github.ericliucn.redmoon.utils.Ref;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ResultMessage implements IMessage {

    public short mainType;
    public short subType;
    public short resultCode;

    public ResultMessage(){

    }

    public ResultMessage(short mainType, short subtype, short resultCode){
        this.mainType = mainType;
        this.subType = subtype;
        this.resultCode = resultCode;
    }

    public ResultMessage(int mainType, int subtype, int resultCode) {
        this(((short) mainType), ((short) subtype), ((short) resultCode));
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        this.mainType = buf.readShort();
        this.subType = buf.readShort();
        this.resultCode = buf.readShort();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeShort(this.mainType);
        buf.writeShort(this.subType);
        buf.writeInt(this.resultCode);
    }

    public static class ResultMessageHandler implements IMessageHandler<ResultMessage, IMessage>{

        @Override
        public IMessage onMessage(ResultMessage message, MessageContext ctx) {
            if (ctx.side.isClient()){
                Minecraft.getMinecraft().addScheduledTask(()->{
                    if (message.mainType == Ref.TRANSACTION){
                        processTransaction(message, Minecraft.getMinecraft().player);
                    }
                });
            }
            return null;
        }
    }

    public static void processTransaction(ResultMessage  message, EntityPlayerSP playerSP){
        //get current screen
        GuiScreen screen = Minecraft.getMinecraft().currentScreen;
        if (!(screen instanceof BankGUI)) return;
        BankGUI gui = ((BankGUI) screen);
        assert gui != null;
        //result type: deposit
        switch (message.resultCode){
            //success: refresh balance
            case Ref.SUCCESS:
                gui.refreshBalance();
                break;
            //error: show notify
            case Ref.ERROR:
                //todo: show error image
                break;
            default:
                break;

        }
    }
}

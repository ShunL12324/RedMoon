package com.github.ericliucn.redmoon.network.bank;

import com.github.ericliucn.redmoon.client.guis.BankGUI;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
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
                GuiScreen screen = Minecraft.getMinecraft().currentScreen;
                if (screen instanceof BankGUI){
                    BankGUI bankGUI = ((BankGUI) screen);
                    assert bankGUI != null;
                    bankGUI.balance = message.bal;
                }
            }


            return null;
        }
    }
}

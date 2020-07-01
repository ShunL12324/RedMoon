package com.github.ericliucn.redmoon.network.bank;

import com.github.ericliucn.redmoon.client.guis.BankGUI;
import com.github.ericliucn.redmoon.utils.References;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class TransactionResultMessage implements IMessage {

    public static TransactionResultMessage INSUFFICIENT_BALANCE = new TransactionResultMessage(References.INSUFFICIENT_BALANCE, 0);
    public static TransactionResultMessage TRANSACTION_ERROR = new TransactionResultMessage(References.TRANSACTION_ERROR, 0);

    public int type;
    public int amount;

    public TransactionResultMessage(){

    }

    public TransactionResultMessage(int type, int amount){
        this.type = type;
        this.amount = amount;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.type = buf.readInt();
        this.amount = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.type);
        buf.writeInt(this.amount);
    }

    public static class TransactionResultHandler implements IMessageHandler<TransactionResultMessage, IMessage>{

        @Override
        public IMessage onMessage(TransactionResultMessage message, MessageContext ctx) {
            if (ctx.side.isClient()){
                Minecraft.getMinecraft().addScheduledTask(()->{
                    switch (message.type){
                        case References.INSUFFICIENT_BALANCE:
                            System.out.println("余额不足");
                            break;
                        case References.TRANSACTION_ERROR:
                            System.out.println("交易错误");
                            break;
                        case References.TRANSACTION_SUCCESS:
                            GuiScreen screen = Minecraft.getMinecraft().currentScreen;
                            if (screen instanceof BankGUI){
                                assert screen != null;
                                BankGUI gui = ((BankGUI) screen);
                                gui.refreshBalance();
                            }
                            break;
                        default:
                            break;

                    }
                });
            }
            return null;
        }
    }
}

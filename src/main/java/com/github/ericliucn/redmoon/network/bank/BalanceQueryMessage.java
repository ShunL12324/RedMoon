package com.github.ericliucn.redmoon.network.bank;

import com.github.ericliucn.redmoon.sponge.EcoUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.spongepowered.api.entity.living.player.Player;

public class BalanceQueryMessage implements IMessage {

    public String curName;

    public  BalanceQueryMessage(){

    }

    public BalanceQueryMessage(String cur){
        this.curName = cur;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.curName = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, curName);
    }

    public static class BalanceQueryMessageHandler implements IMessageHandler<BalanceQueryMessage, IMessage>{

        @Override
        public IMessage onMessage(BalanceQueryMessage message, MessageContext ctx) {

            if (ctx.side.isServer()){
                EntityPlayerMP playerMP = ctx.getServerHandler().player;
                double bal;
                if (!playerMP.world.isRemote){
                    /*
                    playerMP.getServerWorld().addScheduledTask(()->{
                        BigDecimal bal = EcoUtils.getPlayerBalance((Player) playerMP, message.curName);
                        BalanceQueryBackMessage message1 = new BalanceQueryBackMessage(bal.doubleValue());
                        Main.NETWORK_WRAPPER.sendTo(message1, playerMP);
                    });

                     */
                    bal = EcoUtils.getPlayerBalance((Player) playerMP, message.curName).doubleValue();
                    return new BalanceQueryBackMessage(bal);
                }
            }

            return null;
        }
    }
}

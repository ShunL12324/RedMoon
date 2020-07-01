package com.github.ericliucn.redmoon.network.bank;

import com.github.ericliucn.redmoon.items.ModItem;
import com.github.ericliucn.redmoon.sponge.EcoUtils;
import com.github.ericliucn.redmoon.utils.References;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.spongepowered.api.entity.living.player.Player;

public class TransactionMessage implements IMessage {

    public String type;
    public int amount;
    public String currency;

    public TransactionMessage(){

    }

    public TransactionMessage(String type, int amount, String currency){
        this.type = type;
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.type = ByteBufUtils.readUTF8String(buf);
        this.currency = ByteBufUtils.readUTF8String(buf);
        this.amount = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.type);
        ByteBufUtils.writeUTF8String(buf, this.currency);
        buf.writeInt(this.amount);
    }

    public static class TransactionHandler implements IMessageHandler<TransactionMessage, IMessage>{

        @Override
        public IMessage onMessage(TransactionMessage message, MessageContext ctx) {
            if (ctx.side.isServer()){
                EntityPlayerMP playerMP = ctx.getServerHandler().player;
                if (message.type.equals(References.TRANSACTION_WITHDRAW)) {
                    int bal = EcoUtils.getPlayerBalance((Player) playerMP, message.currency).intValue();
                    if (bal < message.amount || bal == 0){
                        return TransactionResultMessage.INSUFFICIENT_BALANCE;
                    }

                    if (!EcoUtils.withdraw((Player) playerMP, message.currency, message.amount)){
                        return TransactionResultMessage.TRANSACTION_ERROR;
                    }

                    addEnergyStoneToInv(message.amount, playerMP);

                    return new TransactionResultMessage(References.TRANSACTION_SUCCESS, message.amount);

                }
            }
            return null;
        }
    }

    public static void addEnergyStoneToInv(int amount, EntityPlayerMP playerMP){
        if (playerMP.world.isRemote) return;
        int freeSpace = 0;
        for (ItemStack itemStack:playerMP.inventory.mainInventory
             ) {
            if (itemStack.isEmpty()){
                freeSpace += 64;
            }else if (itemStack.isItemEqual(new ItemStack(ModItem.ITEM_ENERGY_STONE, 1, 0))){
                freeSpace += (64 - itemStack.getCount());
            }
        }

        if (freeSpace >= amount){
            ItemStack energyStone = new ItemStack(ModItem.ITEM_ENERGY_STONE, amount, 0);
            playerMP.inventory.addItemStackToInventory(energyStone);
        }else {
            ItemStack toInv = new ItemStack(ModItem.ITEM_ENERGY_STONE, freeSpace, 0);
            ItemStack toSpawn = new ItemStack(ModItem.ITEM_ENERGY_STONE, amount - freeSpace, 0);
            playerMP.inventory.addItemStackToInventory(toInv);
            EntityItem entityItem = new EntityItem(playerMP.world, playerMP.posX, playerMP.posY + 1, playerMP.posZ, toSpawn);
            playerMP.world.spawnEntity(entityItem);
        }
    }
}

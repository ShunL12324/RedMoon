package com.github.ericliucn.redmoon.network.bank;

import com.github.ericliucn.redmoon.Main;
import com.github.ericliucn.redmoon.items.ModItem;
import com.github.ericliucn.redmoon.sponge.EcoUtils;
import com.github.ericliucn.redmoon.utils.Ref;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.spongepowered.api.entity.living.player.Player;

public class TransactionMessage implements IMessage {

    public short type;
    public int amount;
    public String currency;

    public TransactionMessage(){

    }

    public TransactionMessage(short type, int amount, String currency){
        this.type = type;
        this.currency = currency;
        this.amount = amount;
    }

    public TransactionMessage(int type, int amount, String currency){
        this.type = (short) type;
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.type = buf.readShort();
        this.currency = ByteBufUtils.readUTF8String(buf);
        this.amount = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeShort(this.type);
        ByteBufUtils.writeUTF8String(buf, this.currency);
        buf.writeInt(this.amount);
    }

    public static class TransactionHandler implements IMessageHandler<TransactionMessage, IMessage>{

        @Override
        public IMessage onMessage(TransactionMessage message, MessageContext ctx) {
            // check side
            if (ctx.side.isServer()){
                EntityPlayerMP playerMP = ctx.getServerHandler().player;
                playerMP.getServerWorld().addScheduledTask(()->{

                    //check eco service present
                    if (!EcoUtils.ecoServicePresent()){
                        Main.NETWORK_WRAPPER.sendTo(new ResultMessage(1, 1, 2), playerMP);
                    }

                    if (message.type == Ref.DEPOSIT){
                        //deposit money
                        deposit(playerMP, message);
                    }else if (message.type == Ref.WITHDRAW) {
                        //withdraw money
                        withdraw(playerMP, message);
                    }
                });
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

    public static void deposit(EntityPlayerMP playerMP, TransactionMessage message){
        int reduce = playerMP.inventory.clearMatchingItems(ModItem.ITEM_ENERGY_STONE, 0, message.amount, null);
        if (reduce != message.amount){
            playerMP.sendMessage(
                    new TextComponentString("非法交易！请求交易数量与实际不符,将以实际扣除数量为准")
                            .setStyle(new Style().setColor(TextFormatting.DARK_RED)));
        }

        boolean result = EcoUtils.deposit(((Player) playerMP), message.currency, reduce);
        if (result){
            Main.NETWORK_WRAPPER.sendTo(new ResultMessage(Ref.TRANSACTION, Ref.DEPOSIT, Ref.SUCCESS), playerMP);
        }else {
            Main.NETWORK_WRAPPER.sendTo(new ResultMessage(Ref.TRANSACTION, Ref.DEPOSIT, Ref.ERROR), playerMP);
            playerMP.inventory.addItemStackToInventory(new ItemStack(ModItem.ITEM_ENERGY_STONE, reduce, 0));
        }
    }

    public static void withdraw(EntityPlayerMP playerMP, TransactionMessage message){
        int amount = message.amount;
        // get balance
        double balance = EcoUtils.getPlayerBalance(((Player) playerMP), message.currency).doubleValue();
        // insufficient balance, return
        if (balance < amount){
            Main.NETWORK_WRAPPER.sendTo(new ResultMessage(Ref.TRANSACTION, Ref.GENERAL, Ref.ERROR), playerMP);
            return;
        }

        // try transaction
        boolean transaction = EcoUtils.withdraw(((Player) playerMP), message.currency, amount);

        if (transaction) {
            // success
            Main.NETWORK_WRAPPER.sendTo(new ResultMessage(Ref.TRANSACTION, Ref.WITHDRAW, Ref.SUCCESS), playerMP);
            addEnergyStoneToInv(amount, playerMP);
        } else {
            Main.NETWORK_WRAPPER.sendTo(new ResultMessage(Ref.TRANSACTION, Ref.WITHDRAW, Ref.ERROR), playerMP);
        }
    }
}

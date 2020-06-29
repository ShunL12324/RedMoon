package com.github.ericliucn.redmoon.command;

import com.github.ericliucn.redmoon.Main;
import com.github.ericliucn.redmoon.network.BalanceQueryMessage;
import com.github.ericliucn.redmoon.network.CommandMessage;
import com.github.ericliucn.redmoon.network.NetUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.server.MinecraftServer;

public class TestCommand extends CommandBase {
    @Override
    public String getName() {
        return "test";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "test";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (sender instanceof EntityPlayerMP){
            EntityPlayerMP playerMP = ((EntityPlayerMP) sender);
            BalanceQueryMessage message = new BalanceQueryMessage("Coin");
            Main.NETWORK_WRAPPER.getPacketFrom(message);
        }
    }



}

package com.github.ericliucn.redmoon.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

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
            ItemStack itemStack = playerMP.getHeldItemMainhand();
            String message = itemStack.toString();
            playerMP.sendMessage(new TextComponentString(message));
        }
    }



}

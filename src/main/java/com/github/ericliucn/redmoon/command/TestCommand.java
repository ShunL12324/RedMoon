package com.github.ericliucn.redmoon.command;

import com.github.ericliucn.redmoon.Main;
import com.github.ericliucn.redmoon.network.GUIOpenMessage;
import com.github.ericliucn.redmoon.utils.Ref;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.RayTraceResult;

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
            RayTraceResult result = playerMP.rayTrace(10,1F);
            System.out.println(playerMP.world.getTileEntity(result.getBlockPos()));
        }
    }



}

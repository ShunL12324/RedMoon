package com.github.ericliucn.redmoon.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

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
            Player player = ((Player) playerMP);
            RayTraceResult traceResult = playerMP.rayTrace(10, 1F);
            BlockPos blockPos = traceResult.getBlockPos();
            TileEntity tileEntity = playerMP.getEntityWorld().getTileEntity(blockPos);
            if (tileEntity!=null) {
                player.sendMessage(Text.of(tileEntity.toString()));
            }


        }
    }



}

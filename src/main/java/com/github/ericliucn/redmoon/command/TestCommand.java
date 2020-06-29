package com.github.ericliucn.redmoon.command;

import com.github.ericliucn.redmoon.Main;
import com.github.ericliucn.redmoon.network.CommandMessage;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
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
            CommandMessage commandMessage = new CommandMessage("say hello");
            Main.NETWORK_WRAPPER.sendToServer(commandMessage);
            /*
            Chunk chunk = playerMP.world.getChunk(playerMP.getPosition());
            int ore = 0;
            int diamond = 0;
            for (int i = 0; i < 16 ; i++) {
                for (int j = 0; j < 16 ; j++) {
                    for (int k = 0; k < 255 ; k++) {
                        BlockPos blockPos = new BlockPos(i, k, j);
                        Block block = chunk.getBlockState(blockPos).getBlock();
                        if (block instanceof BlockEnergyStoneOre){
                            ore += 1;
                        }else if (block.equals(Blocks.DIAMOND_ORE)){
                            diamond += 1;
                        }
                    }
                }
            }
            System.out.println(ore + "/" + diamond);

             */
        }
    }



}

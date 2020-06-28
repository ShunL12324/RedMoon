package com.github.ericliucn.redmoon.command;

import com.github.ericliucn.redmoon.blocks.BlockEnergyStoneOre;
import com.github.ericliucn.redmoon.blocks.ModBlock;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;

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
            Biome biome = playerMP.world.getBiome(playerMP.getPosition());
            System.out.println(biome.getBiomeName());
            System.out.println(biome.equals(Biomes.DESERT));
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
                            /*
                            blockPos.add(chunk.x * 16, 0, chunk.z *16);
                            int x = blockPos.getX() + chunk.x * 16;
                            int z = blockPos.getZ() + chunk.z * 16;
                            System.out.println(x + "/" + blockPos.getY() + "/" + z);

                             */

                        }else if (block.equals(Blocks.DIAMOND_ORE)){
                            diamond += 1;
                        }
                    }
                }
            }
            System.out.println(ore + "/" + diamond);
        }
    }



}

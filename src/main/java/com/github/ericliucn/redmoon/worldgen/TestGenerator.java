package com.github.ericliucn.redmoon.worldgen;

import com.github.ericliucn.redmoon.blocks.ModBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class TestGenerator implements IWorldGenerator {

    private static final WorldGenMinable generator = new WorldGenMinable(ModBlock.BLOCK_ENERGY_STONE_ORE.getDefaultState(), 50);

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        BlockPos pos = new BlockPos(chunkX*16 , chunkZ*16, 60);
        generator.generate(world, random, pos);
    }
}

package com.github.ericliucn.redmoon.worldgen;

import com.github.ericliucn.redmoon.Main;
import com.github.ericliucn.redmoon.blocks.ModBlock;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class OreGenerator implements IWorldGenerator {

    private static final WorldGenMinable generator = new WorldGenMinable(ModBlock.BLOCK_ENERGY_STONE_ORE.getDefaultState(), 4);

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.getWorldType().equals(WorldType.DEFAULT)){
            int chance = 5;
            for (int i = 0; i < chance; i++) {
                int posX = chunkX * 16 + random.nextInt(16);
                int poxZ = chunkZ * 16 + random.nextInt(16);
                int posY = random.nextInt(30) + 1;
                BlockPos pos = new BlockPos(posX, posY, poxZ);
                generator.generate(world, random, pos);
            }
        }
    }
}

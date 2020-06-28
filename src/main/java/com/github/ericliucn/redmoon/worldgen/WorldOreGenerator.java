package com.github.ericliucn.redmoon.worldgen;

import com.github.ericliucn.redmoon.blocks.ModBlock;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldOreGenerator extends WorldGenerator {

    private static final WorldGenerator oreGenerator = new WorldGenMinable(ModBlock.BLOCK_ENERGY_STONE_ORE.getDefaultState(), 100);

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        if (TerrainGen.generateOre(worldIn, rand, this, position, OreGenEvent.GenerateMinable.EventType.CUSTOM)){
            for (int i = 0; i < 3 ; i++) {
                Biome biome = worldIn.getBiome(position);
                if ((biome.equals(Biomes.OCEAN) && rand.nextFloat() < 0.8F) || rand.nextFloat() < 0.4F){
                    oreGenerator.generate(worldIn, rand, position);
                }
            }
        }
        return true;
    }
}

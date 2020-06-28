package com.github.ericliucn.redmoon.worldgen;

import com.github.ericliucn.redmoon.blocks.ModBlock;
import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

public class OreGenHandler {

    private static final WorldGenerator oreGenerator = new WorldOreGenerator();

    private BlockPos blockPos;

    public OreGenHandler(){
        MinecraftForge.ORE_GEN_BUS.register(this);
    }

    @SubscribeEvent
    public void onOreGenPost(OreGenEvent.Post event){
        if (!event.getPos().equals(this.blockPos)){
            this.blockPos = event.getPos();
            oreGenerator.generate(event.getWorld(), event.getRand(), event.getPos());
        }
    }


}

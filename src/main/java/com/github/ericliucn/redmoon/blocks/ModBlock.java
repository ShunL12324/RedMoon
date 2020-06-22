package com.github.ericliucn.redmoon.blocks;

import net.minecraft.block.material.Material;

public class ModBlock {

    public static BlockGenerator BLOCK_GENERATOR;

    public static void preInit(){
        BLOCK_GENERATOR = new BlockGenerator(
                "block_generator",
                Material.IRON);
    }
}

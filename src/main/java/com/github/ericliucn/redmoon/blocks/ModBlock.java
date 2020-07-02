package com.github.ericliucn.redmoon.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlock {

    public static Block BLOCK_ENERGY_STONE_ORE;
    public static Block BLOCK_ATM;

    public static void preInit(){
        BLOCK_ENERGY_STONE_ORE = new BlockEnergyStoneOre("energy_stone_ore", Material.ROCK);
        BLOCK_ATM = new BlockATM("atm", Material.IRON);
    }

}

package com.github.ericliucn.redmoon.blocks;

import com.github.ericliucn.redmoon.blocks.tiles.TileEnergyStoneUpdater;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlock {

    public static Block BLOCK_ENERGY_STONE_ORE;
    public static Block BLOCK_ATM;
    public static Block BLOCK_ENERGY_STONE_UPDATER;

    public static void preInit(){
        BLOCK_ENERGY_STONE_ORE = new BlockEnergyStoneOre("energy_stone_ore", Material.ROCK);
        BLOCK_ATM = new BlockATM("atm", Material.IRON);
        BLOCK_ENERGY_STONE_UPDATER = new BlockEnergyUpdater("energy_stone_updater", Material.IRON);

        // register tile entity
        GameRegistry.registerTileEntity(TileEnergyStoneUpdater.class, new ResourceLocation("redmoon:energy_stone_updater"));
    }

}

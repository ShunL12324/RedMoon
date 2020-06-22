package com.github.ericliucn.redmoon.blocks.tiles;

import net.minecraft.tileentity.TileEntity;

public class ModTile {

    public static TileEntity TILE_GENERATOR;

    public static void preInit(){
        TILE_GENERATOR = new TileGenerator();
    }
}
